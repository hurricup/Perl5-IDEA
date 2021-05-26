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

package com.perl5.lang.tt2.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.NotNullLazyValue;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiUtilCore;
import com.perl5.lang.perl.idea.formatter.PerlBaseFormattingContext;
import com.perl5.lang.perl.idea.formatter.PurePerlFormattingContext;
import com.perl5.lang.perl.idea.formatter.blocks.PerlAstBlock;
import com.perl5.lang.tt2.TemplateToolkitLanguage;
import com.perl5.lang.tt2.psi.impl.PsiExprImpl;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import static com.perl5.lang.tt2.elementTypes.TemplateToolkitElementTypes.TT2_PERL_CODE;
import static com.perl5.lang.tt2.elementTypes.TemplateToolkitElementTypes.TT2_RAWPERL_CODE;
import static com.perl5.lang.tt2.formatter.TemplateToolkitFormattingTokenSets.*;
import static com.perl5.lang.tt2.lexer.TemplateToolkitElementTypesGenerated.*;
import static com.perl5.lang.tt2.lexer.TemplateToolkitSyntaxElements.ALL_OPERATORS_TOKENSET;
import static com.perl5.lang.tt2.lexer.TemplateToolkitSyntaxElements.KEYWORDS_TOKENSET;

final class TemplateToolkitFormattingContext extends PerlBaseFormattingContext {
  private final Map<ASTNode, Alignment> myAssignAlignmentMap = new THashMap<>();
  private final NotNullLazyValue<PurePerlFormattingContext> myPurePerlContextProvider = NotNullLazyValue.createValue(
    () -> new PurePerlFormattingContext(getFormattingContext()) {
      @Override
      public @NotNull Indent getNodeIndent(@NotNull ASTNode node) {
        var parentType = PsiUtilCore.getElementType(node.getTreeParent());
        var perlIndent = super.getNodeIndent(node);
        if (PERL_BLOCKS.contains(parentType) && !Indent.getAbsoluteNoneIndent().equals(perlIndent)) {
          return Indent.getNormalIndent();
        }
        return perlIndent;
      }
    });

  public TemplateToolkitFormattingContext(@NotNull FormattingContext formattingContext) {
    super(formattingContext);
  }

  @Override
  protected @NotNull SpacingBuilder createSpacingBuilder() {
    return new SpacingBuilder(getCodeStyleSettings(), TemplateToolkitLanguage.INSTANCE)
      .around(TT2_PERIOD).spaces(0)
      .after(TT2_MINUS_UNARY).spaces(0)
      .after(TT2_OUTLINE_TAG).spaces(1)
      .after(TT2_OPEN_TAG).spaces(1)
      .before(TT2_SEMI).spaces(0)
      .after(TT2_SEMI).spaces(1)
      .afterInside(TT2_NOT, UNARY_EXPR).spaces(0)
      .before(TT2_COMMA).spaces(0)
      .after(TT2_COMMA).spaces(1)
      .before(TT2_CLOSE_TAG).spaces(1)
      .around(KEYWORDS_TOKENSET).spaces(1)
      .before(MACRO_CONTENT).spaces(1)
      .beforeInside(ASSIGN_EXPR, PROCESS_DIRECTIVE).spaces(1)
      .beforeInside(ASSIGN_EXPR, EXCEPTION_ARGS).spaces(1)
      .beforeInside(ASSIGN_EXPR, WRAPPER_DIRECTIVE).spaces(1)
      .before(PAIR_EXPR).spaces(1)
      .between(ASSIGN_EXPR, ASSIGN_EXPR).spaces(1)
      .between(PAIR_EXPR, PAIR_EXPR).spaces(1)
      .around(EXCEPTION_TYPE).spaces(1)
      .around(EXCEPTION_MESSAGE).spaces(1)
      .after(TT2_LEFT_BRACE).spaces(0)
      .after(TT2_LEFT_BRACKET).spaces(0)
      .after(TT2_LEFT_PAREN).spaces(0)
      .before(TT2_RIGHT_BRACE).spaces(0)
      .before(TT2_RIGHT_BRACKET).spaces(0)
      .before(TT2_RIGHT_PAREN).spaces(0)
      .around(ALL_OPERATORS_TOKENSET).spaces(1)
      ;
  }

  @Override
  public @NotNull Indent getNodeIndent(@NotNull ASTNode node) {
    IElementType elementType = node.getElementType();

    if (elementType == TT2_OUTLINE_TAG) {
      return Indent.getAbsoluteNoneIndent();
    }

    boolean isFirst = isFirst(node);
    ASTNode parentNode = node.getTreeParent();
    IElementType parentNodeType = PsiUtilCore.getElementType(parentNode);

    if (!isFirst && NORMAL_INDENTED_CONTAINERS.contains(parentNodeType)) // branhces and so on
    {
      return Indent.getNormalIndent();
    }
    else if (CONTINUOUS_INDENTED_CONTAINERS.contains(parentNodeType)) // default, set, etc
    {
      return Indent.getContinuationWithoutFirstIndent();
    }
    else if (CONTINUOUS_INDENTED_CONTAINERS_WITH_CLOSE_TAG.contains(parentNodeType)) // array, hash
    {
      // fixme this one incorrectly detects indent inside DataWrapper; we could enforce to parent, but may be ugly
      return isLast(node) ? Indent.getNormalIndent() : Indent.getContinuationWithoutFirstIndent();
    }
    else if (!isFirst && NORMAL_INDENTED_CONTAINERS_WITH_CLOSE_TAG.contains(parentNodeType)) // blocks
    {
      return isLast(node) ? Indent.getNoneIndent() : Indent.getNormalIndent();
    }
    else if (parentNode != null && parentNode.getPsi() instanceof PsiExprImpl) // expression, too broad to unit with default and set
    {
      return Indent.getContinuationWithoutFirstIndent();
    }
    else if (parentNodeType == TT2_PERL_CODE || parentNodeType == TT2_RAWPERL_CODE) {
      return Indent.getNormalIndent();
    }

    return Indent.getNoneIndent();
  }

  /**
   * Checks that current node is first, controlling optional SET and GET
   */
  private boolean isFirst(@NotNull ASTNode node) {
    boolean defaultValue = node.getTreePrev() == null;
    if (defaultValue) {
      IElementType parentNodeType = PsiUtilCore.getElementType(node.getTreeParent());
      if (parentNodeType == SET_DIRECTIVE) {
        return PsiUtilCore.getElementType(node) == TT2_SET;
      }
      else if (parentNodeType == GET_DIRECTIVE) {
        return PsiUtilCore.getElementType(node) == TT2_GET;
      }
    }
    return defaultValue;
  }


  @Override
  public @Nullable Indent getChildIndent(@NotNull PerlAstBlock block, int newChildIndex) {
    var nodeType = block.getElementType();
    if (NORMAL_CHILD_INDENTED_CONTAINERS.contains(nodeType)) {
      return Indent.getNormalIndent();
    }
    else if (CONTINUOUS_CHILD_INDENTED_CONTAINERS.contains(nodeType)) {
      return Indent.getContinuationWithoutFirstIndent();
    }
    return null;
  }

  @Override
  public @Nullable Wrap getWrap(@NotNull ASTNode childNode) {
    return null;
  }

  @Override
  public @Nullable Alignment getAlignment(@NotNull ASTNode childNode) {
    // we could use pattern here, but we need to find parent node, so this method is more effective i guess
    IElementType nodeType = childNode.getElementType();
    ASTNode parentNode = childNode.getTreeParent();
    IElementType parentNodeType = PsiUtilCore.getElementType(parentNode);
    ASTNode grandParentNode = parentNode == null ? null : parentNode.getTreeParent();
    IElementType grandParentNodeType = PsiUtilCore.getElementType(grandParentNode);

    if (nodeType == TT2_ASSIGN && (
      parentNodeType == ASSIGN_EXPR && ALIGNABLE_ASSIGN_EXPRESSIONS_CONTAINERS.contains(grandParentNodeType) ||   // assignments
      parentNodeType == PAIR_EXPR && ALIGNABLE_PAIR_EXPRESSIONS_CONTAINERS.contains(grandParentNodeType)    // pairs
    )) {
      return getAssignAlignment(grandParentNode);
    }
    return null;
  }

  private @NotNull Alignment getAssignAlignment(@NotNull ASTNode defaultDirectiveNode) {
    Alignment result = myAssignAlignmentMap.get(defaultDirectiveNode);
    if (result != null) {
      return result;
    }

    result = Alignment.createAlignment(true);
    myAssignAlignmentMap.put(defaultDirectiveNode, result);
    return result;
  }

  @Override
  public @Nullable Alignment getChildAlignment(@NotNull PerlAstBlock block, int newChildIndex) {
    return null;
  }

  /**
   * Checks if current node is a last node or last block tag in the current parent
   *
   * @return check result
   */
  protected boolean isLast(@NotNull ASTNode node) {
    if (node.getTreeNext() == null) {
      return true;
    }

    IElementType elementType = node.getElementType();
    if (elementType == END_DIRECTIVE) {
      return true;
    }

    if (elementType != TT2_OPEN_TAG) {
      return false;
    }

    ASTNode run = node.getTreeNext();
    while (true) {
      if (run == null) {
        return false;
      }

      if (run.getElementType() == TT2_CLOSE_TAG) {
        return run.getTreeNext() == null;
      }
      run = run.getTreeNext();
    }
  }

  public @NotNull PurePerlFormattingContext getPurePerlContext() {
    return myPurePerlContextProvider.getValue();
  }
}
