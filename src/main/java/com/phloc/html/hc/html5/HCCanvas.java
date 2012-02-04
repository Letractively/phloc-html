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
package com.phloc.html.hc.html5;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.CGlobal;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.text.IPredefinedLocaleTextProvider;
import com.phloc.html.CHTMLAttributes;
import com.phloc.html.EHTMLElement;
import com.phloc.html.annotations.SinceHTML5;
import com.phloc.html.hc.IHCNode;
import com.phloc.html.hc.conversion.HCConversionSettings;
import com.phloc.html.hc.impl.AbstractHCElementWithChildren;

@SinceHTML5
public final class HCCanvas extends AbstractHCElementWithChildren <HCCanvas>
{
  private long m_nHeight = CGlobal.ILLEGAL_ULONG;
  private long m_nWidth = CGlobal.ILLEGAL_ULONG;

  public HCCanvas ()
  {
    super (EHTMLElement.CANVAS);
  }

  public HCCanvas (@Nonnull final IPredefinedLocaleTextProvider aChild)
  {
    this (aChild.getText ());
  }

  public HCCanvas (@Nullable final String sChild)
  {
    super (EHTMLElement.CANVAS, sChild);
  }

  public HCCanvas (@Nullable final String... aChildren)
  {
    super (EHTMLElement.CANVAS, aChildren);
  }

  public HCCanvas (@Nullable final IHCNode aChild)
  {
    super (EHTMLElement.CANVAS, aChild);
  }

  public HCCanvas (@Nullable final IHCNode... aChildren)
  {
    super (EHTMLElement.CANVAS, aChildren);
  }

  public HCCanvas (@Nullable final Iterable <? extends IHCNode> aChildren)
  {
    super (EHTMLElement.CANVAS, aChildren);
  }

  @Nonnull
  public HCCanvas setHeight (final long nHeight)
  {
    m_nHeight = nHeight;
    return this;
  }

  @Nonnull
  public HCCanvas setWidth (final long nWidth)
  {
    m_nWidth = nWidth;
    return this;
  }

  @Override
  protected void applyProperties (final HCConversionSettings aConversionSettings, final IMicroElement aElement)
  {
    super.applyProperties (aConversionSettings, aElement);
    if (m_nHeight >= 0)
      aElement.setAttribute (CHTMLAttributes.HEIGHT, m_nHeight);
    if (m_nWidth >= 0)
      aElement.setAttribute (CHTMLAttributes.WIDTH, m_nWidth);
  }
}
