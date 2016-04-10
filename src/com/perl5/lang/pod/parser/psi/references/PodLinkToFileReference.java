/*
 * Copyright 2016 Alexandr Evstigneev
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

import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.PsiFile;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.intellij.util.IncorrectOperationException;
import com.perl5.lang.perl.fileTypes.PerlFileTypePackage;
import com.perl5.lang.perl.util.PerlPackageUtil;
import com.perl5.lang.pod.filetypes.PodFileType;
import com.perl5.lang.pod.parser.psi.PodFormatterL;
import com.perl5.lang.pod.parser.psi.PodLinkDescriptor;
import com.perl5.lang.pod.parser.psi.util.PodFileUtil;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hurricup on 07.04.2016.
 */
public class PodLinkToFileReference extends PodReferenceBase<PodFormatterL>
{
	protected static final ResolveCache.PolyVariantResolver<PodLinkToFileReference> RESOLVER = new PodFileReferenceResolver();

	public PodLinkToFileReference(PodFormatterL element, TextRange range)
	{
		super(element, range);
	}

	@NotNull
	@Override
	public ResolveResult[] multiResolve(boolean incompleteCode)
	{
		return ResolveCache.getInstance(myElement.getProject()).resolveWithCaching(this, RESOLVER, true, incompleteCode);
	}

	@Override
	public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException
	{
		PodLinkDescriptor descriptor = myElement.getLinkDescriptor();
		if (descriptor != null)
		{
			String currentName = descriptor.getFileId();
			if (StringUtil.isNotEmpty(currentName) && newElementName.endsWith("." + PerlFileTypePackage.EXTENSION) || newElementName.endsWith("." + PodFileType.EXTENSION))
			{
				String[] nameChunks = currentName.split(PerlPackageUtil.PACKAGE_SEPARATOR);
				nameChunks[nameChunks.length - 1] = newElementName.replaceFirst(PodFileUtil.PM_OR_POD_EXTENSION_PATTERN, "");
				newElementName = StringUtils.join(nameChunks, PerlPackageUtil.PACKAGE_SEPARATOR);

				return super.handleElementRename(newElementName);
			}

			throw new IncorrectOperationException("Can't bind package use/require to a non-pm file: " + newElementName);
		}
		return myElement;
	}

	@Override
	public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException
	{
		return super.bindToElement(element);
	}

	private static class PodFileReferenceResolver implements ResolveCache.PolyVariantResolver<PodLinkToFileReference>
	{
		@NotNull
		@Override
		public ResolveResult[] resolve(@NotNull PodLinkToFileReference podLinkToFileReference, boolean incompleteCode)
		{
			PodFormatterL podLink = podLinkToFileReference.getElement();
			PodLinkDescriptor descriptor = podLink.getLinkDescriptor();

			if (descriptor != null && !descriptor.isUrl() && descriptor.getFileId() != null)
			{
				List<ResolveResult> results = new ArrayList<ResolveResult>();
				for (PsiFile targetFile : PodFileUtil.collectPodOrPackagePsiByDescriptor(podLink.getProject(), descriptor))
				{
					results.add(new PsiElementResolveResult(targetFile));
				}
				if (!results.isEmpty())
				{
					// fixme this could be smarter and check file for target section
					return results.toArray(new ResolveResult[results.size()]);
				}
			}

			return ResolveResult.EMPTY_ARRAY;
		}
	}
}
