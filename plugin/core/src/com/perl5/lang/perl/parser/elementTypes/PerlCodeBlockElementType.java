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

package com.perl5.lang.perl.parser.elementTypes;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiUtilCore;
import com.perl5.lang.perl.lexer.PerlLexer;
import com.perl5.lang.perl.psi.impl.PsiPerlBlockImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.perl5.lang.perl.lexer.PerlElementTypesGenerated.*;


public class PerlCodeBlockElementType extends PerlBracedBlockElementType {

  public PerlCodeBlockElementType() {
    super("BLOCK", PsiPerlBlockImpl.class);
  }

  @Override
  protected @NotNull IElementType getOpeningBraceType() {
    return LEFT_BRACE;
  }

  @Override
  protected boolean isLexerStateOk(int lexerState) {
    return lexerState == PerlLexer.AFTER_RIGHT_BRACE;
  }

  @Override
  protected boolean isNodeReparseable(@Nullable ASTNode parent) {
    if (parent == null) {
      return false;
    }

    IElementType parentType = PsiUtilCore.getElementType(parent);

    // block and file level block, may turn to the hash
    if (parentType == BLOCK_COMPOUND) {
      return false;
    }

    IElementType firstChildType = PsiUtilCore.getElementType(parent.getFirstChildNode());

    // moving up to call arguments
    if (parentType == COMMA_SEQUENCE_EXPR) {
      parent = parent.getTreeParent();
      parentType = PsiUtilCore.getElementType(parent);
    }

    return parentType != CALL_ARGUMENTS || firstChildType != this;
  }
}