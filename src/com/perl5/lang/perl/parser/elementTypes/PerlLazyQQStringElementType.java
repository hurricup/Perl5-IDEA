/*
 * Copyright 2015-2017 Alexandr Evstigneev
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

import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.perl5.lang.perl.lexer.adapters.PerlSubLexerAdapter;
import com.perl5.lang.perl.parser.PerlLazyQQStringContentParser;
import org.jetbrains.annotations.NotNull;

/**
 * Created by hurricup on 23.10.2016.
 */
public class PerlLazyQQStringElementType extends PerlLazyBlockElementType {
  public PerlLazyQQStringElementType(String name) {
    super(name);
  }

  public PerlLazyQQStringElementType(@NotNull String debugName, @NotNull Class<? extends PsiElement> clazz) {
    super(debugName, clazz);
  }

  @NotNull
  @Override
  protected Lexer getInnerLexer(@NotNull Project project) {
    return PerlSubLexerAdapter.forStringDQ(project);
  }

  @NotNull
  @Override
  protected PsiParser getParser() {
    return PerlLazyQQStringContentParser.INSTANCE;
  }
}
