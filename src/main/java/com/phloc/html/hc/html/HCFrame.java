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

import com.phloc.commons.CGlobal;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.html.CHTMLAttributes;
import com.phloc.html.EHTMLElement;
import com.phloc.html.annotations.DeprecatedInHTML5;
import com.phloc.html.hc.api.EHCScrolling;
import com.phloc.html.hc.conversion.IHCConversionSettings;
import com.phloc.html.hc.impl.AbstractHCElement;

/**
 * Represents an HTML &lt;frame&gt; element
 * 
 * @author philip
 */
@DeprecatedInHTML5
public class HCFrame extends AbstractHCElement <HCFrame>
{
  public static final boolean DEFAULT_FRAME_BORDER = true;
  public static final boolean DEFAULT_NO_RESIZE = false;

  private boolean m_bFrameBorder = DEFAULT_FRAME_BORDER;
  private String m_sLongDesc;
  private int m_nMarginWidth = CGlobal.ILLEGAL_UINT;
  private int m_nMarginHeight = CGlobal.ILLEGAL_UINT;
  private String m_sName;
  private boolean m_bNoResize = DEFAULT_NO_RESIZE;
  private EHCScrolling m_eScrolling;
  private String m_sSrc;

  public HCFrame ()
  {
    super (EHTMLElement.FRAME);
  }

  public HCFrame (@Nullable final String sName)
  {
    this ();
    setName (sName);
  }

  public boolean isFrameBorder ()
  {
    return m_bFrameBorder;
  }

  @Nonnull
  public HCFrame setFrameBorder (final boolean bFrameBorder)
  {
    m_bFrameBorder = bFrameBorder;
    return this;
  }

  @Nullable
  public String getLongDesc ()
  {
    return m_sLongDesc;
  }

  @Nonnull
  public HCFrame setLongDesc (@Nullable final String sLongDesc)
  {
    m_sLongDesc = sLongDesc;
    return this;
  }

  public int getMarginWidth ()
  {
    return m_nMarginWidth;
  }

  @Nonnull
  public HCFrame setMarginWidth (final int nMarginWidth)
  {
    m_nMarginWidth = nMarginWidth;
    return this;
  }

  public int getMarginHeight ()
  {
    return m_nMarginHeight;
  }

  @Nonnull
  public HCFrame setMarginHeight (final int nMarginHeight)
  {
    m_nMarginHeight = nMarginHeight;
    return this;
  }

  @Nullable
  public String getName ()
  {
    return m_sName;
  }

  @Nonnull
  public HCFrame setName (@Nullable final String sName)
  {
    m_sName = sName;
    return this;
  }

  public boolean isNoResize ()
  {
    return m_bNoResize;
  }

  @Nonnull
  public HCFrame setNoResize (final boolean bNoResize)
  {
    m_bNoResize = bNoResize;
    return this;
  }

  @Nullable
  public EHCScrolling getScrolling ()
  {
    return m_eScrolling;
  }

  @Nonnull
  public HCFrame setScrolling (@Nullable final EHCScrolling eScrolling)
  {
    m_eScrolling = eScrolling;
    return this;
  }

  @Nullable
  public String getSrc ()
  {
    return m_sSrc;
  }

  @Nonnull
  public HCFrame setSrc (@Nullable final String sSrc)
  {
    m_sSrc = sSrc;
    return this;
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettings aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    aElement.setAttribute (CHTMLAttributes.FRAMEBORDER, m_bFrameBorder ? "1" : "0");
    if (StringHelper.hasText (m_sLongDesc))
      aElement.setAttribute (CHTMLAttributes.LONGDESC, m_sLongDesc);
    if (m_nMarginWidth >= 0)
      aElement.setAttribute (CHTMLAttributes.MARGINWIDTH, Integer.toString (m_nMarginWidth));
    if (m_nMarginHeight >= 0)
      aElement.setAttribute (CHTMLAttributes.MARGINHEIGHT, Integer.toString (m_nMarginHeight));
    if (StringHelper.hasText (m_sName))
      aElement.setAttribute (CHTMLAttributes.NAME, m_sName);
    if (m_bNoResize)
      aElement.setAttribute (CHTMLAttributes.NORESIZE, CHTMLAttributes.NORESIZE);
    if (m_eScrolling != null)
      aElement.setAttribute (CHTMLAttributes.SCROLLING, m_eScrolling.getAttrValue ());
    if (StringHelper.hasText (m_sSrc))
      aElement.setAttribute (CHTMLAttributes.SRC, m_sSrc);
  }

  public String getPlainText ()
  {
    return "";
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("frameBorder", m_bFrameBorder)
                            .appendIfNotNull ("longDesc", m_sLongDesc)
                            .append ("marginWidth", m_nMarginWidth)
                            .append ("marginHeight", m_nMarginHeight)
                            .appendIfNotNull ("name", m_sName)
                            .append ("noResize", m_bNoResize)
                            .appendIfNotNull ("scrolling", m_eScrolling)
                            .appendIfNotNull ("src", m_sSrc)
                            .toString ();
  }
}
