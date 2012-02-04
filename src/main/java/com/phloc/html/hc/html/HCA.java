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
import com.phloc.commons.GlobalDebug;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.mime.IMimeType;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.url.ISimpleURL;
import com.phloc.html.CHTMLAttributes;
import com.phloc.html.EHTMLElement;
import com.phloc.html.XHTMLConsistencyException;
import com.phloc.html.hc.api.IHCHasTabIndex;
import com.phloc.html.hc.api5.IHCHasMedia;
import com.phloc.html.hc.conversion.HCConversionSettings;
import com.phloc.html.hc.impl.AbstractHCElementWithChildren;

/**
 * This is a HTML "a" element!
 * 
 * @author philip
 */
public class HCA extends AbstractHCElementWithChildren <HCA> implements IHCHasTabIndex <HCA>, IHCHasMedia <HCA>
{
  private ISimpleURL m_aHref;
  private HCA_Target m_aTarget;
  private String m_sName;
  private IMimeType m_aType;
  private int m_nTabIndex = CGlobal.ILLEGAL_UINT;
  private String m_sMediaQuery;

  public HCA ()
  {
    super (EHTMLElement.A);
  }

  public HCA (@Nonnull final ISimpleURL aHref)
  {
    this ();
    setHref (aHref);
  }

  @Nonnull
  public final HCA setHref (@Nonnull final ISimpleURL aHref)
  {
    if (aHref == null)
      throw new NullPointerException ("href");

    checkIfLinkIsMasked (aHref.getAsString ());
    m_aHref = aHref;
    return this;
  }

  @Nullable
  public final ISimpleURL getHref ()
  {
    return m_aHref;
  }

  @Nonnull
  public final HCA setTarget (@Nullable final HCA_Target aTarget)
  {
    m_aTarget = aTarget;
    return this;
  }

  @Nonnull
  public final HCA setName (@Nullable final String sName)
  {
    m_sName = sName;
    return this;
  }

  @Nonnull
  public final HCA setType (@Nullable final IMimeType aType)
  {
    m_aType = aType;
    return this;
  }

  public int getTabIndex ()
  {
    return m_nTabIndex;
  }

  @Nonnull
  public final HCA setTabIndex (final int nTabIndex)
  {
    m_nTabIndex = nTabIndex;
    return this;
  }

  @Nonnull
  public final HCA setMedia (@Nullable final String sMediaQuery)
  {
    m_sMediaQuery = sMediaQuery;
    return this;
  }

  @Override
  protected final void applyProperties (HCConversionSettings aConversionSettings, final IMicroElement aElement)
  {
    if (GlobalDebug.isDebugMode () && recursiveContainsChildWithTagName (EHTMLElement.A))
      XHTMLConsistencyException.onInconsistency ("Links may never contain other links!");

    super.applyProperties (aConversionSettings, aElement);
    if (m_aHref != null)
      aElement.setAttribute (CHTMLAttributes.HREF, m_aHref.getAsString ());
    if (m_aTarget != null)
    {
      // Note: attribute "target" is not allowed in XHTML 1.0 strict (but in
      // 1.1)
      aElement.setAttribute (CHTMLAttributes.TARGET, m_aTarget.getAttrValue ());
    }
    if (StringHelper.hasText (m_sName))
      aElement.setAttribute (CHTMLAttributes.NAME, m_sName);
    if (m_aType != null)
      aElement.setAttribute (CHTMLAttributes.TYPE, m_aType.getAsString ());
    if (m_nTabIndex != CGlobal.ILLEGAL_UINT)
      aElement.setAttribute (CHTMLAttributes.TABINDEX, Integer.toString (m_nTabIndex));
    // HTML5 only:
    if (StringHelper.hasText (m_sMediaQuery))
      aElement.setAttribute (CHTMLAttributes.MEDIA, m_sMediaQuery);
  }
}
