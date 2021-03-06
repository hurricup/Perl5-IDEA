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

package com.perl5.lang.mojolicious.extensions;

import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.psi.util.PsiModificationTracker;
import com.perl5.lang.mojolicious.psi.impl.MojoHelperDefinition;
import com.perl5.lang.mojolicious.psi.impl.MojoliciousFile;
import com.perl5.lang.mojolicious.psi.impl.MojoliciousFileImpl;
import com.perl5.lang.perl.extensions.imports.PerlImportsProvider;
import com.perl5.lang.perl.extensions.packageprocessor.PerlExportDescriptor;
import com.perl5.lang.perl.psi.PerlNamespaceDefinitionElement;
import com.perl5.lang.perl.psi.stubs.subsdefinitions.PerlLightSubDefinitionsReverseIndex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.perl5.lang.mojolicious.psi.impl.MojoliciousFile.MOJO_CONTROLLER_NS;

public class MojoImplicitImportsProvider implements PerlImportsProvider {
  public static final List<PerlExportDescriptor> HARDCODED_DESCRIPTORS = Arrays.asList(

    PerlExportDescriptor.create(MojoliciousFile.MOJO_DEFAULT_HELPERS_NS, "csrf_token", "_csrf_token"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_DEFAULT_HELPERS_NS, "current_route", "_current_route"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_DEFAULT_HELPERS_NS, "delay", "_delay"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_DEFAULT_HELPERS_NS, "inactivity_timeout", "_inactivity_timeout"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_DEFAULT_HELPERS_NS, "is_fresh", "_is_fresh"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_DEFAULT_HELPERS_NS, "url_with", "_url_with"),

    PerlExportDescriptor.create(MOJO_CONTROLLER_NS, "extends", "stash"),
    PerlExportDescriptor.create(MOJO_CONTROLLER_NS, "layout", "stash"),
    PerlExportDescriptor.create(MOJO_CONTROLLER_NS, "title", "stash"),

    PerlExportDescriptor.create(MOJO_CONTROLLER_NS, "app", "app"),
    PerlExportDescriptor.create(MOJO_CONTROLLER_NS, "flash", "flash"),
    PerlExportDescriptor.create(MOJO_CONTROLLER_NS, "param", "param"),
    PerlExportDescriptor.create(MOJO_CONTROLLER_NS, "stash", "stash"),
    PerlExportDescriptor.create(MOJO_CONTROLLER_NS, "session", "session"),
    PerlExportDescriptor.create(MOJO_CONTROLLER_NS, "url_for", "url_for"),
    PerlExportDescriptor.create(MOJO_CONTROLLER_NS, "validation", "validation"),

    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "color_field", "_input"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "email_field", "_input"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "number_field", "_input"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "range_field", "_input"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "search_field", "_input"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "tel_field", "_input"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "text_field", "_input"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "url_field", "_input"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "date_field", "_input"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "month_field", "_input"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "time_field", "_input"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "week_field", "_input"),

    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "csrf_field", "_csrf_field"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "form_for", "_form_for"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "hidden_field", "_hidden_field"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "javascript", "_javascript"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "label_for", "_label_for"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "link_to", "_link_to"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "select_field", "_select_field"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "stylesheet", "_stylesheet"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "submit_button", "_submit_button"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "tag_with_error", "_tag_with_error"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "text_area", "_text_area"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "t", "_tag"),
    PerlExportDescriptor.create(MojoliciousFile.MOJO_TAG_HELPERS_NS, "tag", "_tag")
  );

  @Override
  public boolean isApplicable(@Nullable PerlNamespaceDefinitionElement namespaceDefinitionElement) {
    return namespaceDefinitionElement instanceof MojoliciousFileImpl;
  }

  @Override
  public @NotNull List<PerlExportDescriptor> getExportDescriptors(@NotNull PerlNamespaceDefinitionElement namespaceElement) {
    return CachedValuesManager.getCachedValue(namespaceElement, () -> {
      List<PerlExportDescriptor> result = new ArrayList<>(MojoImplicitImportsProvider.HARDCODED_DESCRIPTORS);
      PerlLightSubDefinitionsReverseIndex.getInstance().processLightElements(
        namespaceElement.getProject(), MOJO_CONTROLLER_NS, namespaceElement.getResolveScope(), sub -> {
          if (sub instanceof MojoHelperDefinition) {
            String packageName = sub.getNamespaceName();
            assert packageName != null;
            result.add(PerlExportDescriptor.create(packageName, sub.getSubName()));
          }
          return true;
        });
      return CachedValueProvider.Result.create(result, PsiModificationTracker.MODIFICATION_COUNT);
    });
  }
}
