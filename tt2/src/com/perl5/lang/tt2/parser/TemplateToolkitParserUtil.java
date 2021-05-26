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

package com.perl5.lang.tt2.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.perl5.lang.tt2.TemplateToolkitBundle;
import com.perl5.lang.tt2.TemplateToolkitParserDefinition;
import com.perl5.lang.tt2.elementTypes.TemplateToolkitElementTypes;
import com.perl5.lang.tt2.lexer.TemplateToolkitSyntaxElements;


@SuppressWarnings("Duplicates")
public class TemplateToolkitParserUtil extends GeneratedParserUtilBase implements TemplateToolkitElementTypes {
  public static final TokenSet OPEN_QUOTES = TokenSet.create(
    TT2_DQ_OPEN,
    TT2_SQ_OPEN
  );
  public static final TokenSet CLOSE_QUOTES = TokenSet.create(
    TT2_DQ_CLOSE,
    TT2_SQ_CLOSE
  );

  public static final TokenSet BLOCK_CONTAINERS = TokenSet.create(
    IF_BLOCK,
    UNLESS_BLOCK,
    FOREACH_BLOCK,
    FILTER_BLOCK,
    PERL_BLOCK,
    RAWPERL_BLOCK,
    WHILE_BLOCK,
    SWITCH_BLOCK,
    TRY_CATCH_BLOCK,
    WRAPPER_BLOCK,
    NAMED_BLOCK,
    ANON_BLOCK
  );

  public static boolean parseIdentifier(PsiBuilder b, int l) {
    if (consumeToken(b, TT2_IDENTIFIER)) {
      return true;
    }
    else if (TemplateToolkitSyntaxElements.POSSIBLE_IDENTIFIERS.contains(b.getTokenType())) {
      PsiBuilder.Marker m = b.mark();
      b.advanceLexer();
      m.collapse(TT2_IDENTIFIER);
      return true;
    }
    return false;
  }

  public static boolean parseHashKey(PsiBuilder b, int l) {
    PsiBuilder.Marker m = b.mark();
    if (TemplateToolkitParser.keyword_or_identifier_term(b, l)) {
      m.collapse(TT2_STRING_CONTENT);
      m.precede().done(SQ_STRING_EXPR);
      return true;
    }

    return false;
  }

  private static boolean isEndMarker(PsiBuilder b) {
    return isBlockEndMarker(b);
  }

  private static boolean isBlockEndMarker(PsiBuilder b) {
    IElementType tokenType = b.getTokenType();
    return tokenType == TT2_SEMI || tokenType == TT2_CLOSE_TAG;
  }

  public static boolean parseFileAsString(PsiBuilder b, int l) {
    if (b.eof()) {
      return false;
    }

    if (isEndMarker(b)) {
      return false;
    }

    boolean gotItem = false;
    PsiBuilder.Marker stringMarker = b.mark();
    while (!b.eof()) {
      if (isEndMarker(b)) {
        break;
      }

      boolean isLastToken = TemplateToolkitParserDefinition.WHITE_SPACES.contains(b.rawLookup(1));

      PsiBuilder.Marker m = b.mark();
      b.advanceLexer();
      m.collapse(TT2_STRING_CONTENT);

      gotItem = true;

      if (isLastToken) {
        break;
      }
    }

    if (gotItem) {
      stringMarker.done(SQ_STRING_EXPR);
    }
    else {
      stringMarker.drop();
    }
    return gotItem;
  }

  public static boolean parseTags(PsiBuilder b, int l) {
    PsiBuilder.Marker m = null;
    while (!b.eof() && !isEndMarker(b)) {
      if (m == null) {
        m = b.mark();
      }
      b.advanceLexer();
    }

    if (m != null) {
      m.collapse(TT2_STRING_CONTENT);
      m.precede().done(SQ_STRING_EXPR);
    }

    return true;
  }

  public static boolean parseKeywordFallback(PsiBuilder b, int l) {
    if (TemplateToolkitSyntaxElements.KEYWORDS_OR_TEXT_OPERATORS_TOKENSET.contains(b.getTokenType())) {
      PsiBuilder.Marker m = b.mark();
      b.advanceLexer();
      m.error(TemplateToolkitBundle.message("tt2.error.keyword.in.identifier"));
      return true;
    }

    return false;
  }

  public static boolean parseUnaryMinus(PsiBuilder b, int l) {
    if (b.getTokenType() == TT2_MINUS) {
      PsiBuilder.Marker m = b.mark();
      b.advanceLexer();
      m.collapse(TT2_MINUS_UNARY);
      return true;
    }
    return false;
  }
}
