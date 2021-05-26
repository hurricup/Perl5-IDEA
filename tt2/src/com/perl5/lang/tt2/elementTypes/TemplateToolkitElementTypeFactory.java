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

package com.perl5.lang.tt2.elementTypes;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.perl5.lang.pod.elementTypes.PodElementTypeFactory;
import com.perl5.lang.tt2.psi.impl.*;
import org.jetbrains.annotations.NotNull;


public class TemplateToolkitElementTypeFactory extends PodElementTypeFactory {
  public static IElementType getTokenType(String debugName) {
    if (debugName.equals("TT2_HTML")) {
      return new TemplateToolkitTemplateTokenType();
    }
    return new TemplateToolkitTokenType(debugName);
  }

  public static IElementType getElementType(String name) {
    if (name.equals("ADD_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiAddExprImpl(node);
        }
      };
    }

    if (name.equals("AND_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiAndExprImpl(node);
        }
      };
    }

    if (name.equals("ANON_BLOCK")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiAnonBlockImpl(node);
        }
      };
    }

    if (name.equals("ANON_BLOCK_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiAnonBlockDirectiveImpl(node);
        }
      };
    }

    if (name.equals("ARRAY_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiArrayExprImpl(node);
        }
      };
    }

    if (name.equals("ASSIGN_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiAssignExprImpl(node);
        }
      };
    }

    if (name.equals("BLOCK_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiBlockDirectiveImpl(node);
        }
      };
    }

    if (name.equals("BLOCK_NAME")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiBlockNameImpl(node);
        }
      };
    }

    if (name.equals("CALL_ARGUMENTS")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiCallArgumentsImpl(node);
        }
      };
    }

    if (name.equals("CALL_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiCallDirectiveImpl(node);
        }
      };
    }

    if (name.equals("CALL_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiCallExprImpl(node);
        }
      };
    }

    if (name.equals("CASE_BLOCK")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiCaseBlockImpl(node);
        }
      };
    }

    if (name.equals("CASE_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiCaseDirectiveImpl(node);
        }
      };
    }

    if (name.equals("CATCH_BRANCH")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiCatchBranchImpl(node);
        }
      };
    }

    if (name.equals("CATCH_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiCatchDirectiveImpl(node);
        }
      };
    }

    if (name.equals("CLEAR_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiClearDirectiveImpl(node);
        }
      };
    }

    if (name.equals("COMPARE_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiCompareExprImpl(node);
        }
      };
    }

    if (name.equals("DEBUG_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiDebugDirectiveImpl(node);
        }
      };
    }

    if (name.equals("DEBUG_FORMAT")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiDebugFormatImpl(node);
        }
      };
    }

    if (name.equals("DEFAULT_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiDefaultDirectiveImpl(node);
        }
      };
    }

    if (name.equals("DIRECTIVE_POSTFIX")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiDirectivePostfixImpl(node);
        }
      };
    }

    if (name.equals("DQ_STRING_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiDqStringExprImpl(node);
        }
      };
    }

    if (name.equals("ELSE_BRANCH")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiElseBranchImpl(node);
        }
      };
    }

    if (name.equals("ELSE_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiElseDirectiveImpl(node);
        }
      };
    }

    if (name.equals("ELSIF_BRANCH")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiElsifBranchImpl(node);
        }
      };
    }

    if (name.equals("ELSIF_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiElsifDirectiveImpl(node);
        }
      };
    }

    if (name.equals("END_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiEndDirectiveImpl(node);
        }
      };
    }

    if (name.equals("EQUAL_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiEqualExprImpl(node);
        }
      };
    }

    if (name.equals("EXCEPTION_ARGS")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiExceptionArgsImpl(node);
        }
      };
    }

    if (name.equals("EXCEPTION_MESSAGE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiExceptionMessageImpl(node);
        }
      };
    }

    if (name.equals("EXCEPTION_TYPE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiExceptionTypeImpl(node);
        }
      };
    }

    if (name.equals("EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          throw new RuntimeException("Instantiating " + node);
        }
      };
    }

    if (name.equals("FILTER_BLOCK")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiFilterBlockImpl(node);
        }
      };
    }

    if (name.equals("FILTER_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiFilterDirectiveImpl(node);
        }
      };
    }

    if (name.equals("FILTER_ELEMENT_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiFilterElementExprImpl(node);
        }
      };
    }

    if (name.equals("FINAL_BRANCH")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiFinalBranchImpl(node);
        }
      };
    }

    if (name.equals("FINAL_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiFinalDirectiveImpl(node);
        }
      };
    }

    if (name.equals("FOREACH_BLOCK")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiForeachBlockImpl(node);
        }
      };
    }

    if (name.equals("FOREACH_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiForeachDirectiveImpl(node);
        }
      };
    }

    if (name.equals("FOREACH_ITERABLE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiForeachIterableImpl(node);
        }
      };
    }

    if (name.equals("FOREACH_ITERATOR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiForeachIteratorImpl(node);
        }
      };
    }

    if (name.equals("GET_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiGetDirectiveImpl(node);
        }
      };
    }

    if (name.equals("HASH_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiHashExprImpl(node);
        }
      };
    }

    if (name.equals("IDENTIFIER_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiIdentifierExprImpl(node);
        }
      };
    }

    if (name.equals("IF_BLOCK")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiIfBlockImpl(node);
        }
      };
    }

    if (name.equals("IF_BRANCH")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiIfBranchImpl(node);
        }
      };
    }

    if (name.equals("IF_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiIfDirectiveImpl(node);
        }
      };
    }

    if (name.equals("INCLUDE_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiIncludeDirectiveImpl(node);
        }
      };
    }

    if (name.equals("INSERT_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiInsertDirectiveImpl(node);
        }
      };
    }

    if (name.equals("LAST_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiLastDirectiveImpl(node);
        }
      };
    }

    if (name.equals("MACRO_CONTENT")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiMacroContentImpl(node);
        }
      };
    }

    if (name.equals("MACRO_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiMacroDirectiveImpl(node);
        }
      };
    }

    if (name.equals("MACRO_NAME")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiMacroNameImpl(node);
        }
      };
    }

    if (name.equals("META_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiMetaDirectiveImpl(node);
        }
      };
    }

    if (name.equals("MODULE_NAME")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiModuleNameImpl(node);
        }
      };
    }

    if (name.equals("MODULE_PARAMS")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiModuleParamsImpl(node);
        }
      };
    }

    if (name.equals("MUL_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiMulExprImpl(node);
        }
      };
    }

    if (name.equals("NAMED_BLOCK")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiNamedBlockImpl(node);
        }
      };
    }

    if (name.equals("NEXT_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiNextDirectiveImpl(node);
        }
      };
    }

    if (name.equals("OR_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiOrExprImpl(node);
        }
      };
    }

    if (name.equals("PAIR_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiPairExprImpl(node);
        }
      };
    }

    if (name.equals("PARENTHESISED_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiParenthesisedExprImpl(node);
        }
      };
    }

    if (name.equals("PERL_BLOCK")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiPerlBlockImpl(node);
        }
      };
    }

    if (name.equals("PERL_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiPerlDirectiveImpl(node);
        }
      };
    }

    if (name.equals("PROCESS_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiProcessDirectiveImpl(node);
        }
      };
    }

    if (name.equals("RANGE_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiRangeExprImpl(node);
        }
      };
    }

    if (name.equals("RAWPERL_BLOCK")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiRawperlBlockImpl(node);
        }
      };
    }

    if (name.equals("RAWPERL_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiRawperlDirectiveImpl(node);
        }
      };
    }

    if (name.equals("RETURN_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiReturnDirectiveImpl(node);
        }
      };
    }

    if (name.equals("SET_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiSetDirectiveImpl(node);
        }
      };
    }

    if (name.equals("SQ_STRING_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiSqStringExprImpl(node);
        }
      };
    }

    if (name.equals("STOP_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiStopDirectiveImpl(node);
        }
      };
    }

    if (name.equals("SWITCH_BLOCK")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiSwitchBlockImpl(node);
        }
      };
    }

    if (name.equals("SWITCH_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiSwitchDirectiveImpl(node);
        }
      };
    }

    if (name.equals("TAGS_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiTagsDirectiveImpl(node);
        }
      };
    }

    if (name.equals("TERM_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiTermExprImpl(node);
        }
      };
    }

    if (name.equals("TERNAR_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiTernarExprImpl(node);
        }
      };
    }

    if (name.equals("THROW_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiThrowDirectiveImpl(node);
        }
      };
    }

    if (name.equals("TRY_BRANCH")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiTryBranchImpl(node);
        }
      };
    }

    if (name.equals("TRY_CATCH_BLOCK")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiTryCatchBlockImpl(node);
        }
      };
    }

    if (name.equals("TRY_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiTryDirectiveImpl(node);
        }
      };
    }

    if (name.equals("UNARY_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiUnaryExprImpl(node);
        }
      };
    }

    if (name.equals("UNLESS_BLOCK")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiUnlessBlockImpl(node);
        }
      };
    }

    if (name.equals("UNLESS_BRANCH")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiUnlessBranchImpl(node);
        }
      };
    }

    if (name.equals("UNLESS_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiUnlessDirectiveImpl(node);
        }
      };
    }

    if (name.equals("USE_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiUseDirectiveImpl(node);
        }
      };
    }

    if (name.equals("USE_INSTANCE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiUseInstanceImpl(node);
        }
      };
    }

    if (name.equals("VARIABLE_EXPR")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiVariableExprImpl(node);
        }
      };
    }

    if (name.equals("WHILE_BLOCK")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiWhileBlockImpl(node);
        }
      };
    }

    if (name.equals("WHILE_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiWhileDirectiveImpl(node);
        }
      };
    }

    if (name.equals("WRAPPER_BLOCK")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiWrapperBlockImpl(node);
        }
      };
    }

    if (name.equals("WRAPPER_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new PsiWrapperDirectiveImpl(node);
        }
      };
    }

    if (name.equals("EMPTY_DIRECTIVE")) {
      return new TemplateToolkitElementType(name) {
        @Override
        public @NotNull PsiElement getPsiElement(@NotNull ASTNode node) {
          return new TemplateToolkitCompositeElementImpl(node);
        }
      };
    }

    throw new RuntimeException("Missing element: " + name);
  }
}
