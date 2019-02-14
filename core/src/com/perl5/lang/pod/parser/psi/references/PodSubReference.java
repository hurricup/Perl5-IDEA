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

package com.perl5.lang.pod.parser.psi.references;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.PsiFile;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import com.perl5.lang.perl.psi.PerlPolyNamedElement;
import com.perl5.lang.perl.psi.PerlSubDeclarationElement;
import com.perl5.lang.perl.psi.PerlSubDefinitionElement;
import com.perl5.lang.perl.psi.PerlSubElement;
import com.perl5.lang.perl.psi.light.PerlDelegatingLightNamedElement;
import com.perl5.lang.perl.psi.references.PerlCachingReference;
import com.perl5.lang.perl.util.PerlPackageUtil;
import com.perl5.lang.perl.util.PerlSubUtil;
import com.perl5.lang.pod.parser.psi.impl.PodIdentifierImpl;
import com.perl5.lang.pod.parser.psi.util.PodFileUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by hurricup on 05.04.2016.
 */
public class PodSubReference extends PerlCachingReference<PodIdentifierImpl> {
  public PodSubReference(PodIdentifierImpl element) {
    super(element, new TextRange(0, element.getTextLength()), true);
  }

  @Override
  public PsiElement handleElementRename(@NotNull String newElementName) throws IncorrectOperationException {
    return (PsiElement)myElement.replaceWithText(newElementName);
  }

  @Override
  public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
    return super.bindToElement(element);
  }

  @NotNull
  @Override
  protected ResolveResult[] resolveInner(boolean incompleteCode) {
    PsiElement element = getElement();
    final Project project = element.getProject();
    String subName = element.getText();
    if (StringUtil.isNotEmpty(subName)) {
      final PsiFile containingFile = element.getContainingFile();
      // this is a bit lame. we could try to detect from perl context. But unsure
      String packageName = PodFileUtil.getPackageName(containingFile);

      List<ResolveResult> results = new ArrayList<>();

      if (StringUtil.isNotEmpty(packageName)) {
        String canonicalName = packageName + PerlPackageUtil.PACKAGE_SEPARATOR + subName;
        for (PerlSubDefinitionElement target : PerlSubUtil.getSubDefinitions(project, canonicalName)) {
          results.add(new PsiElementResolveResult(target));
        }
        for (PerlSubDeclarationElement target : PerlSubUtil.getSubDeclarations(project, canonicalName)) {
          results.add(new PsiElementResolveResult(target));
        }
      }

      if (results.isEmpty()) {
        Consumer<? super PerlSubElement> subConsumer = it -> {
          String subBaseName = it.getName();
          if (subBaseName != null && StringUtil.equals(subBaseName, subName)) {
            results.add(new PsiElementResolveResult(it));
          }
        };

        PsiTreeUtil.processElements(containingFile.getViewProvider().getStubBindingRoot(), it -> {
          if (it instanceof PerlSubElement) {
            subConsumer.accept((PerlSubElement)it);
          }
          else if (it instanceof PerlPolyNamedElement) {
            for (PerlDelegatingLightNamedElement lightElement : ((PerlPolyNamedElement)it).getLightElements()) {
              if (lightElement instanceof PerlSubElement) {
                subConsumer.accept((PerlSubElement)lightElement);
              }
            }
          }
          return true;
        });
      }

      return results.toArray(ResolveResult.EMPTY_ARRAY);
    }

    return ResolveResult.EMPTY_ARRAY;
  }
}
