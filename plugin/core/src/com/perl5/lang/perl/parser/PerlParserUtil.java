/*
 * Copyright 2015-2020 Alexandr Evstigneev
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

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.lang.WhitespacesAndCommentsBinder;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.perl5.lang.perl.lexer.PerlElementTypes;
import com.perl5.lang.perl.lexer.PerlTokenSets;
import com.perl5.lang.perl.parser.builder.PerlBuilder;
import com.perl5.lang.perl.psi.stubs.PerlStubElementTypes;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

import static com.intellij.lang.WhitespacesBinders.GREEDY_LEFT_BINDER;
import static com.intellij.lang.WhitespacesBinders.GREEDY_RIGHT_BINDER;


public class PerlParserUtil extends GeneratedParserUtilBase implements PerlElementTypes {


  public static final TokenSet VERSION_TOKENS = TokenSet.create(
    NUMBER,
    NUMBER_VERSION
  );
  public static final Pattern IDENTIFIER_PATTERN = Pattern.compile("[_\\p{L}][_\\p{L}\\d]*");
  private static final String BASIC_IDENTIFIER_PATTERN_TEXT = "[_\\p{L}\\d][_\\p{L}\\d]*";
  // something strange in Java with unicode props; Added digits to opener for package Encode::KR::2022_KR;
  private static final String PACKAGE_SEPARATOR_PATTERN_TEXT =
    "(?:" +
    "(?:::)+'?" +
    "|" +
    "(?:::)*'" +
    ")";
  public static final Pattern AMBIGUOUS_PACKAGE_PATTERN = Pattern.compile(
    "(" +
    PACKAGE_SEPARATOR_PATTERN_TEXT + "?" +        // optional opening separator,
    "(?:" +
    BASIC_IDENTIFIER_PATTERN_TEXT +
    PACKAGE_SEPARATOR_PATTERN_TEXT +
    ")*" +
    ")" +
    "(" +
    BASIC_IDENTIFIER_PATTERN_TEXT +
    ")");
  private static final WhitespacesAndCommentsBinder NAMESPACE_RIGHT_BINDER = (tokens, atStreamEdge, getter) -> {
    int result = tokens.size();
    if (atStreamEdge || tokens.isEmpty()) {
      return result;
    }

    for (int i = tokens.size() - 1; i >= 0; i--) {
      IElementType currentToken = tokens.get(i);
      if (currentToken == COMMENT_ANNOTATION || currentToken == COMMENT_LINE) {
        result = i;
      }
      else if (currentToken != TokenType.WHITE_SPACE) {
        break;
      }
    }

    return result;
  };

  /**
   * Wrapper for Builder class in order to implement additional per parser information in PerlBuilder
   *
   * @param root        root element
   * @param builder     psibuilder
   * @param parser      psiparser
   * @param extendsSets extends sets
   * @return PerlBuilder
   */
  public static PsiBuilder adapt_builder_(IElementType root, PsiBuilder builder, PsiParser parser, TokenSet[] extendsSets) {
    ErrorState state = new ErrorState();
    ErrorState.initState(state, builder, root, extendsSets);
    //		builder.setDebugMode(true);
    return new PerlBuilder(builder, state, parser);
  }


  /**
   * Smart parser for ->, makes }->[ optional
   *
   * @param b PerlBuilder
   * @param l parsing level
   * @return parsing result
   */
  public static boolean parseArrowSmart(PsiBuilder b, @SuppressWarnings("unused") int l) {
    IElementType tokenType = b.getTokenType();
    if (b.getTokenType() == OPERATOR_DEREFERENCE) {
      return consumeToken(b, OPERATOR_DEREFERENCE);
    }
    else {
      assert b instanceof PerlBuilder;
      PerlTokenData prevToken = ((PerlBuilder)b).lookupToken(-1);
      IElementType prevTokenType = prevToken == null ? null : prevToken.getTokenType();

      // optional }->[ or ]->{
      return (prevTokenType == RIGHT_BRACE || prevTokenType == RIGHT_BRACKET)
             && (tokenType == LEFT_BRACE || tokenType == LEFT_BRACKET || tokenType == LEFT_PAREN);
    }
  }

  public static boolean parseExpressionLevel(PsiBuilder b, int l, int g) {
    return PerlParserImpl.expr(b, l, g);
  }


  /**
   * Smart semi checker decides if we need semi here
   *
   * @param b Perl builder
   * @param l Parsing level
   * @return checking result
   */
  public static boolean statementSemi(PsiBuilder b, int l) {
    return ((PerlBuilder)b).getPerlParser().parseStatementSemi(b, l);
  }


  protected static boolean isOperatorToken(PsiBuilder b, @SuppressWarnings("unused") int l) {
    return PerlTokenSets.OPERATORS_TOKENSET.contains(b.getTokenType());
  }


  /**
   * @return true iff next token should be consumed by parser while recovering after statement parsing
   */
  public static boolean recoverStatement(PsiBuilder b, @SuppressWarnings("unused") int l) {
    assert b instanceof PerlBuilder;
    return ((PerlBuilder)b).getPerlParser().getStatementRecoveryConsumableTokenSet().contains(b.getTokenType());
  }

  /**
   * Parses statement modifier and rolls back if it looks like compound
   *
   * @param b PerlBuilder
   * @param l parsing level
   * @return check result
   */
  @SuppressWarnings("UnusedReturnValue")
  public static boolean parseStatementModifier(PsiBuilder b, int l) {
    assert b instanceof PerlBuilder;
    return ((PerlBuilder)b).getPerlParser().parseStatementModifier(b, l);
  }

  /**
   * Checks for version token and convert if necessary
   *
   * @param b PerlBuilder
   * @param l parsing level
   * @return parsing result
   */
  public static boolean parsePerlVersion(PsiBuilder b, @SuppressWarnings("unused") int l) {
    if (VERSION_TOKENS.contains(b.getTokenType())) {
      PsiBuilder.Marker m = b.mark();
      b.advanceLexer();
      m.collapse(VERSION_ELEMENT);
      return true;
    }
    return false;
  }

  /**
   * Parses comma sequence with trailing comma support
   *
   * @param b PerlBuilder
   * @param l parsing level
   * @return parsing result
   */
  public static boolean parseCommaSequence(PsiBuilder b, int l) {
    boolean r = false;
    while (true) {
      if (consumeToken(b, COMMA) || consumeToken(b, FAT_COMMA))    // got comma
      {
        r = true;

        // consume sequential commas
        while (true) {
          if (!(consumeToken(b, COMMA) || consumeToken(b, FAT_COMMA))) {
            break;
          }
        }

        if (!PerlParserImpl.expr(b, l, 4))    // looks like an end
        {
          break;
        }
      }
      else {
        break;
      }
    }
    return r;
  }

  /**
   * Parses SQ string with optional conversion to the use_vars lp string
   *
   * @param b PerlBuilder
   * @param l parsing level
   * @return parsing result
   */
  public static boolean mapUseVars(PsiBuilder b, int l, Parser parser) {
    PsiBuilder.Marker m = b.mark();

    boolean r;
    r = parser.parse(b, l);

    // fixme prepend last done marker
    if (r) {
      m.setCustomEdgeTokenBinders(GREEDY_LEFT_BINDER, GREEDY_RIGHT_BINDER);
      m.collapse(PARSABLE_STRING_USE_VARS);
    }
    else {
      m.drop();
    }

    return r;
  }

  // fixme replace with looking to upper frames ?
  public static boolean isUseVars(PsiBuilder b, @SuppressWarnings("unused") int l) {
    return ((PerlBuilder)b).isUseVarsContent();
  }

  /**
   * Consuming unexpected token
   *
   * @param b perlbuilder
   * @param l parsing level
   * @return true
   **/
  public static boolean parseBadCharacters(PsiBuilder b, @SuppressWarnings("unused") int l) {
    IElementType tokenType = b.getTokenType();

    if (tokenType == null || ((PerlBuilder)b).getPerlParser().getBadCharacterForbiddenTokens().contains(tokenType)) {
      return false;
    }

    PsiBuilder.Marker m = b.mark();
    b.advanceLexer();

    if (tokenType == TokenType.BAD_CHARACTER) {
      while (b.getTokenType() == TokenType.BAD_CHARACTER) {
        b.advanceLexer();
      }
      m.error("Unexpected token");
    }
    else if (tokenType == RIGHT_PAREN) {
      m.error("Unopened closing parenthesis");
    }
    else if (tokenType == RIGHT_BRACKET) {
      m.error("Unopened closing bracket");
    }
    else {
      m.error("Unexpected token");
    }

    return true;
  }

  /**
   * Parses and wraps declaration of scalar variable; NB: special variable names suppressed
   *
   * @param b Perl builder
   * @param l parsing level
   * @return check result
   */
  public static boolean scalarDeclarationWrapper(PsiBuilder b, int l) {
    PsiBuilder.Marker m = b.mark();
    boolean r = false;
    assert b instanceof PerlBuilder;
    boolean flagBackup = ((PerlBuilder)b).setSpecialVariableNamesAllowed(false);

    if (PerlParserImpl.scalar_variable(b, l)) {
      m.done(VARIABLE_DECLARATION_ELEMENT);
      r = true;
    }
    else {
      m.drop();
    }
    ((PerlBuilder)b).setSpecialVariableNamesAllowed(flagBackup);
    return r;
  }

  /**
   * attempting to parse statement using parserExtensions
   *
   * @param b PerlBuilder
   * @param l parsing level
   * @return parsing result
   */
  public static boolean parseParserExtensionStatement(PsiBuilder b, int l) {
    assert b instanceof PerlBuilder;
    return ((PerlBuilder)b).getPerlParser().parseStatement(b, l);
  }

  /**
   * attempting to parse term using parserExtensions
   *
   * @param b PerlBuilder
   * @param l parsing level
   * @return parsing result
   */
  public static boolean parseParserExtensionTerm(PsiBuilder b, int l) {
    assert b instanceof PerlBuilder;
    return ((PerlBuilder)b).getPerlParser().parseTerm(b, l);
  }

  public static boolean parseFileContent(PsiBuilder b, int l) {
    assert b instanceof PerlBuilder;
    return ((PerlBuilder)b).getPerlParser().parseFileContents(b, l);
  }

  public static boolean parseNestedElementVariation(PsiBuilder b, int l) {
    assert b instanceof PerlBuilder;
    return ((PerlBuilder)b).getPerlParser().parseNestedElementVariation(b, l);
  }

  public static boolean checkSemicolon(PsiBuilder b, @SuppressWarnings("unused") int l) {
    return ((PerlBuilder)b).getPerlParser().getConsumableSemicolonTokens().contains(b.getTokenType());
  }

  public static boolean parseSemicolon(PsiBuilder b, int l) {
    if (checkSemicolon(b, l)) {
      b.advanceLexer();
      return true;
    }
    return false;
  }

  public static boolean parseNamespaceContent(PsiBuilder b, int l) {
    PsiBuilder.Marker m = b.mark();
    if (PerlParserGenerated.real_namespace_content(b, l)) {
      m.done(NAMESPACE_CONTENT);
      m.setCustomEdgeTokenBinders(GREEDY_LEFT_BINDER, NAMESPACE_RIGHT_BINDER);
      return true;
    }
    m.rollbackTo();
    return false;
  }

  public static boolean parseLabelDeclaration(PsiBuilder b, @SuppressWarnings("unused") int l) {
    if (b.lookAhead(1) == COLON && b.getTokenType() != RESERVED_SUB) {
      String tokenText = b.getTokenText();
      if (tokenText != null && IDENTIFIER_PATTERN.matcher(tokenText).matches()) {
        b.advanceLexer();
        b.advanceLexer();
        return true;
      }
    }
    return false;
  }

  /**
   * Parses leftward or rightward call with custom sub token
   *
   * @param b builder
   * @param l level
   * @return result
   */
  public static boolean parseCustomCallExpr(PsiBuilder b, int l, Parser tokenParser) {
    PsiBuilder.Marker m = b.mark();
    if (PerlParserImpl.leftward_call(b, l, tokenParser) || PerlParserImpl.rightward_call(b, l, tokenParser)) {
      m.done(SUB_CALL);
      return true;
    }
    m.rollbackTo();
    return false;
  }

  /**
   * Parses use parameters with package processor if it's possible. If not, uses default parsing logic.
   */
  public static boolean parseUseParameters(@NotNull PsiBuilder b, int l, @NotNull Parser defaultParser) {
    if (b.getTokenType() == PACKAGE) {
      String packageName = b.getTokenText();
      if (StringUtil.isEmpty(packageName)) {
        return false;
      }
      if ("vars".equals(packageName)) {
        assert b instanceof PerlBuilder;
        PerlParserUtil.passPackageAndVersion((PerlBuilder)b, l);
        ((PerlBuilder)b).setUseVarsContent(true);
        PerlParserImpl.expr(b, l, -1);
        ((PerlBuilder)b).setUseVarsContent(false);
        return true;
      }
    }
    return defaultParser.parse(b, l);
  }

  /**
   * Helper method to pass package [version] in use statement
   */
  public static boolean passPackageAndVersion(@NotNull PerlBuilder b, int l) {
    assert PerlParserUtil.consumeTokenFast(b, PACKAGE);
    PerlParserImpl.perl_version(b, l);
    return true;
  }

  public static boolean parseUse(@NotNull PsiBuilder b, int l) {
    if (b.getTokenType() != RESERVED_USE) {
      return false;
    }
    PsiBuilder.Marker mark = b.mark();
    if (PerlParserImpl.parse_use_statement(b, l)) {
      mark.done(PerlStubElementTypes.USE_STATEMENT);
      return true;
    }
    mark.rollbackTo();
    return false;
  }

  public static boolean parseNo(@NotNull PsiBuilder b, int l) {
    if (b.getTokenType() != RESERVED_NO) {
      return false;
    }
    PsiBuilder.Marker mark = b.mark();
    if (PerlParserImpl.parse_no_statement(b, l)) {
      mark.done(PerlStubElementTypes.NO_STATEMENT);
      return true;
    }
    mark.rollbackTo();
    return false;
  }

  public static boolean parseBareString(@NotNull PsiBuilder b, int l) {
    IElementType type = b.getTokenType();
    if (type != STRING_CONTENT && type != STRING_SPECIAL_ESCAPE_CHAR) {
      return false;
    }
    while (true) {
      IElementType nextRawTokenType = b.rawLookup(1);
      boolean shouldContinue = nextRawTokenType == STRING_CONTENT || nextRawTokenType == STRING_SPECIAL_ESCAPE_CHAR;
      b.advanceLexer();
      if (!shouldContinue) {
        return true;
      }
    }
  }
}
