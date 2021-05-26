/*
 * Copyright 2015-2021 Alexandr Evstigneev
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

package com.perl5.lang.tt2.lexer;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.perl5.lang.tt2.elementTypes.TemplateToolkitElementTypes;

import java.util.HashMap;
import java.util.Map;


public class TemplateToolkitSyntaxElements implements TemplateToolkitElementTypes {
  public static final TokenSet OPEN_TAGS = TokenSet.create(
    TT2_OPEN_TAG,
    TT2_OUTLINE_TAG
  );

  public static final TokenSet CONSTRUCTION_PREFIX = TokenSet.orSet(
    OPEN_TAGS,
    TokenSet.create(
      TT2_SEMI,
      MACRO_DIRECTIVE    // macro consumes semicolon
    )
  );

  public static final TokenSet ATOMIC_EXPRESSIONS = TokenSet.create(
    FILTER_DIRECTIVE,
    WRAPPER_DIRECTIVE,
    DEBUG_DIRECTIVE,
    NEXT_DIRECTIVE,
    LAST_DIRECTIVE,
    RETURN_DIRECTIVE,
    STOP_DIRECTIVE,
    CLEAR_DIRECTIVE,
    DEFAULT_DIRECTIVE,
    INSERT_DIRECTIVE,
    INCLUDE_DIRECTIVE,
    PROCESS_DIRECTIVE,
    CALL_DIRECTIVE,
    THROW_DIRECTIVE,
    SET_DIRECTIVE,
    GET_DIRECTIVE
  );

  public static final Map<String, IElementType> KEYWORDS = new HashMap<>();
  public static final TokenSet KEYWORDS_TOKENSET;

  public static final Map<String, IElementType> TEXT_OPERATORS = new HashMap<>();
  public static final TokenSet TEXT_OPERATORS_TOKENSET;

  public static final TokenSet KEYWORDS_OR_TEXT_OPERATORS_TOKENSET;

  public static final TokenSet SYMBOLIC_OPERATORS_TOKENSET = TokenSet.create(
    TT2_ASSIGN,
    TT2_PERIOD,

    TT2_CONCAT,
    TT2_RANGE,
    TT2_EQUAL,
    TT2_NOT_EQUAL,
    TT2_LT,
    TT2_LE,
    TT2_GT,
    TT2_GE,
    TT2_AND,
    TT2_OR,
    TT2_NOT,
    TT2_QUESTION,
    TT2_COLON,
    TT2_PLUS,
    TT2_MINUS,
    TT2_MINUS_UNARY,
    TT2_MUL,
    TT2_DIV,
    TT2_MOD
  );

  public static final TokenSet ALL_OPERATORS_TOKENSET;

  public static final TokenSet POSSIBLE_IDENTIFIERS;

  static {
    KEYWORDS.put("GET", TT2_GET);
    KEYWORDS.put("SET", TT2_SET);
    KEYWORDS.put("CALL", TT2_CALL);
    KEYWORDS.put("DEFAULT", TT2_DEFAULT);
    KEYWORDS.put("INSERT", TT2_INSERT);
    KEYWORDS.put("INCLUDE", TT2_INCLUDE);
    KEYWORDS.put("PROCESS", TT2_PROCESS);
    KEYWORDS.put("WRAPPER", TT2_WRAPPER);
    KEYWORDS.put("BLOCK", TT2_BLOCK);
    KEYWORDS.put("END", TT2_END);
    KEYWORDS.put("IF", TT2_IF);
    KEYWORDS.put("UNLESS", TT2_UNLESS);
    KEYWORDS.put("ELSIF", TT2_ELSIF);
    KEYWORDS.put("ELSE", TT2_ELSE);
    KEYWORDS.put("SWITCH", TT2_SWITCH);
    KEYWORDS.put("CASE", TT2_CASE);
    KEYWORDS.put("FOREACH", TT2_FOREACH);
    KEYWORDS.put("FOR", TT2_FOREACH);
    KEYWORDS.put("WHILE", TT2_WHILE);
    KEYWORDS.put("FILTER", TT2_FILTER);
    KEYWORDS.put("USE", TT2_USE);
    KEYWORDS.put("MACRO", TT2_MACRO);
    KEYWORDS.put("PERL", TT2_PERL);
    KEYWORDS.put("RAWPERL", TT2_RAWPERL);
    KEYWORDS.put("TRY", TT2_TRY);
    KEYWORDS.put("IN", TT2_IN);
    KEYWORDS.put("THROW", TT2_THROW);
    KEYWORDS.put("CATCH", TT2_CATCH);
    KEYWORDS.put("FINAL", TT2_FINAL);
    KEYWORDS.put("NEXT", TT2_NEXT);
    KEYWORDS.put("LAST", TT2_LAST);
    KEYWORDS.put("BREAK", TT2_LAST);
    KEYWORDS.put("RETURN", TT2_RETURN);
    KEYWORDS.put("STOP", TT2_STOP);
    KEYWORDS.put("CLEAR", TT2_CLEAR);
    KEYWORDS.put("META", TT2_META);
    KEYWORDS.put("TAGS", TT2_TAGS);
    KEYWORDS.put("DEBUG", TT2_DEBUG);

    KEYWORDS.put("on", TT2_ON);
    KEYWORDS.put("off", TT2_OFF);
    KEYWORDS.put("format", TT2_FORMAT);

    KEYWORDS_TOKENSET = TokenSet.create(KEYWORDS.values().toArray(new IElementType[KEYWORDS.values().size()]));

    // see https://github.com/abw/Template2/blob/master/parser/Grammar.pm.skel
    TEXT_OPERATORS.put("div", TT2_DIV_TEXT);
    TEXT_OPERATORS.put("and", TT2_AND_TEXT);
    TEXT_OPERATORS.put("or", TT2_OR_TEXT);
    TEXT_OPERATORS.put("not", TT2_NOT_TEXT);
    TEXT_OPERATORS.put("mod", TT2_MOD_TEXT);

    TEXT_OPERATORS.put("DIV", TT2_DIV_TEXT);
    TEXT_OPERATORS.put("AND", TT2_AND_TEXT);
    TEXT_OPERATORS.put("OR", TT2_OR_TEXT);
    TEXT_OPERATORS.put("NOT", TT2_NOT_TEXT);
    TEXT_OPERATORS.put("MOD", TT2_MOD_TEXT);

    TEXT_OPERATORS_TOKENSET = TokenSet.create(TEXT_OPERATORS.values().toArray(new IElementType[TEXT_OPERATORS.values().size()]));

    KEYWORDS_OR_TEXT_OPERATORS_TOKENSET = TokenSet.orSet(
      KEYWORDS_TOKENSET, TEXT_OPERATORS_TOKENSET
    );

    ALL_OPERATORS_TOKENSET = TokenSet.orSet(TEXT_OPERATORS_TOKENSET, SYMBOLIC_OPERATORS_TOKENSET);

    POSSIBLE_IDENTIFIERS = TokenSet.create(
      TT2_ON,
      TT2_OFF,
      TT2_FORMAT
    );
  }
}
