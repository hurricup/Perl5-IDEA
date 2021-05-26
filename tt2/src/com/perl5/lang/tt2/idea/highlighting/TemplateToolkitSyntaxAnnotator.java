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

package com.perl5.lang.tt2.idea.highlighting;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.perl5.lang.perl.idea.annotators.PerlBaseAnnotator;
import com.perl5.lang.tt2.elementTypes.TemplateToolkitElementTypes;
import com.perl5.lang.tt2.lexer.TemplateToolkitSyntaxElements;
import org.jetbrains.annotations.NotNull;


public class TemplateToolkitSyntaxAnnotator implements Annotator, TemplateToolkitElementTypes {
  @Override
  public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
    IElementType tokenType = element.getNode().getElementType();
    TextAttributesKey targetKey = null;
    if (tokenType == TT2_IDENTIFIER || tokenType == TT2_SIGIL_SCALAR) {
      targetKey = TemplateToolkitSyntaxHighlighter.TT2_IDENTIFIER_KEY;
    }
    else if (TemplateToolkitSyntaxElements.KEYWORDS_TOKENSET.contains(tokenType)) {
      targetKey = TemplateToolkitSyntaxHighlighter.TT2_KEYWORD_KEY;
    }
    else if (TemplateToolkitSyntaxElements.ALL_OPERATORS_TOKENSET.contains(tokenType)) {
      targetKey = TemplateToolkitSyntaxHighlighter.TT2_OPERATOR_KEY;
    }
    else if (tokenType == SQ_STRING_EXPR) {
      targetKey = TemplateToolkitSyntaxHighlighter.TT2_SQ_STRING_KEY;
    }
    else if (tokenType == DQ_STRING_EXPR) {
      targetKey = TemplateToolkitSyntaxHighlighter.TT2_DQ_STRING_KEY;
    }
    if (targetKey != null) {
      PerlBaseAnnotator.createInfoAnnotation(holder, element, null, targetKey);
    }
  }
}
