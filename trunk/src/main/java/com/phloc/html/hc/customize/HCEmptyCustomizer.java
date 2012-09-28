/**
 * Copyright (C) 2006-2012 phloc systems
 * http://www.phloc.com
 * office[at]phloc[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.phloc.html.hc.customize;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.phloc.commons.string.ToStringGenerator;
import com.phloc.html.EHTMLVersion;
import com.phloc.html.hc.IHCBaseNode;
import com.phloc.html.hc.IHCElement;
import com.phloc.html.hc.IHCNodeWithChildren;
import com.phloc.html.hc.html.HCBody;
import com.phloc.html.hc.html.HCHead;

/**
 * An implementation of {@link IHCCustomizer} that does nothing.
 * 
 * @author philip
 */
@Immutable
public class HCEmptyCustomizer implements IHCCustomizer
{
  public HCEmptyCustomizer ()
  {}

  public void customizeHCElement (@Nonnull final IHCNodeWithChildren <?> aParentElement,
                                  @Nonnull final IHCElement <?> aElement,
                                  @Nonnull final EHTMLVersion eHTMLVersion)
  {
    // Nada
  }

  public void handleOutOfBandNodes (@Nonnull final List <IHCBaseNode> aOutOfBandNodes,
                                    @Nonnull final HCHead aHead,
                                    @Nonnull final HCBody aBody)
  {
    // Nada
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).toString ();
  }
}