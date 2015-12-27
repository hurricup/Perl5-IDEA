/*
 * Copyright 2015 Alexandr Evstigneev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.perl5.lang.perl.parser;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.perl5.lang.perl.extensions.parser.PerlParserExtension;
import com.perl5.lang.perl.lexer.PerlElementTypes;
import com.perl5.lang.perl.parser.builder.PerlBuilder;
import com.perl5.lang.perl.parser.perlswitch.psi.*;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Created by hurricup on 15.12.2015.
 */
public class SwitchPerlParserExtensionImpl extends PerlParserExtension implements SwitchPerlParserExtension, PerlElementTypes
{
	protected static final THashMap<String, IElementType> TOKENS_MAP = new THashMap<String, IElementType>();
	protected static TokenSet TOKENS_SET;

	static
	{
		// in regular case, these tokens should be created in extension class
		TOKENS_MAP.put("case", RESERVED_CASE);
		TOKENS_MAP.put("switch", RESERVED_SWITCH);

		TOKENS_SET = TokenSet.create(TOKENS_MAP.values().toArray(new IElementType[TOKENS_MAP.values().size()]));
	}

	public static boolean parseSwitchStatement(PerlBuilder b, int l)
	{
		if (PerlParserUtil.consumeToken(b, RESERVED_SWITCH))
		{
			boolean r = parseSwitchCondition(b, l);
			r = r && PerlParser.block(b, l);
			return r;
		}
		return false;
	}

	public static boolean parseSwitchCondition(PerlBuilder b, int l)
	{
		PerlBuilder.Marker m = b.mark();
		boolean r = PerlParserUtil.consumeToken(b, LEFT_PAREN);
		r = r && PerlParser.scalar_expr(b, l);
		r = r && PerlParserUtil.consumeToken(b, RIGHT_PAREN);

		if (r)
		{
			m.done(SWITCH_CONDITION);
		}
		else
		{
			m.rollbackTo();
		}

		return r;
	}

	public static boolean parseCaseSequence(PerlBuilder b, int l)
	{
		int casesNumber = 0;
		while (b.getTokenType() == RESERVED_CASE)
		{
			PerlBuilder.Marker m = b.mark();
			if (parseCaseStatement(b, l))
			{
				m.done(CASE_COMPOUND);
				casesNumber++;
			}
			else
			{
				m.rollbackTo();
				break;
			}
		}

		if (casesNumber > 0)
		{
			PerlBuilder.Marker m = b.mark();
			if (PerlParser.if_compound_else(b, l))
			{
				m.done(CASE_DEFAULT);
			}
			else
			{
				m.rollbackTo();
			}
		}
		return casesNumber > 0;
	}

	public static boolean parseCaseStatement(PerlBuilder b, int l)
	{
		if (PerlParserUtil.consumeToken(b, RESERVED_CASE))
		{
			boolean r = parseCaseCondition(b, l);
			r = r && PerlParser.block(b, l);
			return r;
		}
		return false;
	}

	public static boolean parseCaseCondition(PerlBuilder b, int l)
	{
		PerlBuilder.Marker m = b.mark();

		boolean r = parseCaseConditionParenthesised(b, l);
		r = r || PerlParser.block(b, l);
		r = r || parseCaseConditionSimple(b, l);

		if (r)
		{
			m.done(CASE_CONDITION);
		}
		else
		{
			m.rollbackTo();
		}

		return r;
	}

	public static boolean parseCaseConditionParenthesised(PerlBuilder b, int l)
	{
		boolean r = PerlParserUtil.consumeToken(b, LEFT_PAREN);
		r = r && PerlParser.scalar_expr(b, l);
		r = r && PerlParserUtil.consumeToken(b, RIGHT_PAREN);
		return r;
	}

	public static boolean parseCaseConditionSimple(PerlBuilder b, int l)
	{
		return PerlParser.string(b, l) ||
				PerlParser.number_constant(b, l) ||
				PerlParser.anon_array(b, l) ||
				PerlParser.match_regex(b, l) ||
				PerlParser.compile_regex(b, l)
				;
	}

	@NotNull
	@Override
	public Map<String, IElementType> getReservedTokens()
	{
		return TOKENS_MAP;
	}

	@Nullable
	@Override
	public PsiElement createElement(@NotNull ASTNode node)
	{
		IElementType tokenType = node.getElementType();

		if (tokenType == SWITCH_COMPOUND)
		{
			return new PerlSwitchCompoundStatementImpl(node);
		}
		else if (tokenType == SWITCH_CONDITION)
		{
			return new PerlSwitchConditionImpl(node);
		}
		else if (tokenType == CASE_COMPOUND)
		{
			return new PerlCaseCompoundStatementImpl(node);
		}
		else if (tokenType == CASE_DEFAULT)
		{
			return new PerlCaseDefaultCompoundImpl(node);
		}
		else if (tokenType == CASE_CONDITION)
		{
			return new PerlCaseConditionImpl(node);
		}

		return super.createElement(node);
	}

	@Override
	public boolean parseStatement(PerlBuilder b, int l)
	{
		IElementType tokenType = b.getTokenType();

		if (tokenType == RESERVED_SWITCH)
		{
			PerlBuilder.Marker m = b.mark();
			if (parseSwitchStatement(b, l))
			{
				m.done(SWITCH_COMPOUND);
				return true;
			}
			m.rollbackTo();
		}
		else if (tokenType == RESERVED_CASE)
		{
			return parseCaseSequence(b, l);
		}

		return false;
	}


}
