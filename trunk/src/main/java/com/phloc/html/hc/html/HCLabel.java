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
package com.phloc.html.hc.html;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.text.IPredefinedLocaleTextProvider;
import com.phloc.html.CHTMLAttributes;
import com.phloc.html.EHTMLElement;
import com.phloc.html.hc.HCConversionSettings;
import com.phloc.html.hc.IHCNode;
import com.phloc.html.hc.impl.AbstractHCElementWithChildren;

public final class HCLabel extends AbstractHCElementWithChildren <HCLabel>
{
  private String m_sFor;

  public HCLabel ()
  {
    super (EHTMLElement.LABEL);
  }

  public HCLabel (@Nonnull final IPredefinedLocaleTextProvider aChild)
  {
    this (aChild.getText ());
  }

  public HCLabel (@Nullable final String sChild)
  {
    super (EHTMLElement.LABEL, sChild);
  }

  public HCLabel (@Nullable final String... aChildren)
  {
    super (EHTMLElement.LABEL, aChildren);
  }

  public HCLabel (@Nullable final IHCNode aChild)
  {
    super (EHTMLElement.LABEL, aChild);
  }

  public HCLabel (@Nullable final IHCNode... aChildren)
  {
    super (EHTMLElement.LABEL, aChildren);
  }

  public HCLabel (@Nullable final Iterable <? extends IHCNode> aChildren)
  {
    super (EHTMLElement.LABEL, aChildren);
  }

  /**
   * Indicates that this label is used as the description for another object.
   * 
   * @param sFor
   *        The HTML ID of the other object.
   * @return this
   */
  @Nonnull
  public HCLabel setFor (@Nullable final String sFor)
  {
    m_sFor = sFor;
    return this;
  }

  @Override
  protected void applyProperties (HCConversionSettings aConversionSettings, final IMicroElement aElement)
  {
    super.applyProperties (aConversionSettings, aElement);
    if (StringHelper.hasText (m_sFor))
      aElement.setAttribute (CHTMLAttributes.FOR, m_sFor);
  }
}
