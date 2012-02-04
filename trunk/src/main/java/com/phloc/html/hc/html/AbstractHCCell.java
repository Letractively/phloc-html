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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.CGlobal;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.html.CHTMLAttributes;
import com.phloc.html.EHTMLElement;
import com.phloc.html.annotations.DeprecatedInHTML5;
import com.phloc.html.hc.api.EHCCellAlign;
import com.phloc.html.hc.conversion.HCConversionSettings;
import com.phloc.html.hc.impl.AbstractHCElementWithChildren;

/**
 * Abstract base class for table cells. Works for header, body and footer cells.
 * 
 * @author philip
 */
public abstract class AbstractHCCell extends AbstractHCElementWithChildren <AbstractHCCell>
{
  private HCRow m_aParentRow;
  private int m_nColspan = CGlobal.ILLEGAL_UINT;
  private int m_nRowspan = CGlobal.ILLEGAL_UINT;
  private EHCCellAlign m_eAlign;

  public AbstractHCCell (@Nonnull final EHTMLElement aElement)
  {
    super (aElement);
  }

  @Nullable
  public final HCRow getParentRow ()
  {
    return m_aParentRow;
  }

  @Nonnull
  final AbstractHCCell setParentRow (@Nullable final HCRow aParentRow)
  {
    m_aParentRow = aParentRow;
    return this;
  }

  @Nonnegative
  public final int getColspan ()
  {
    return m_nColspan > 1 ? m_nColspan : 1;
  }

  @Nonnull
  public final AbstractHCCell setColspan (final int nColspan)
  {
    m_nColspan = nColspan;
    return this;
  }

  @Nonnegative
  public final int getRowspan ()
  {
    return m_nRowspan > 1 ? m_nRowspan : 1;
  }

  @Nonnull
  public final AbstractHCCell setRowspan (final int nRowspan)
  {
    m_nRowspan = nRowspan;
    return this;
  }

  @DeprecatedInHTML5
  @Nonnull
  public final AbstractHCCell setAlign (final EHCCellAlign eAlign)
  {
    m_eAlign = eAlign;
    return this;
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, HCConversionSettings aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (m_nColspan > 1)
      aElement.setAttribute (CHTMLAttributes.COLSPAN, Integer.toString (m_nColspan));
    if (m_nRowspan > 1)
      aElement.setAttribute (CHTMLAttributes.ROWSPAN, Integer.toString (m_nRowspan));
    if (m_eAlign != null)
      aElement.setAttribute (CHTMLAttributes.ALIGN, m_eAlign.getAttrValue ());
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("colspan", m_nColspan)
                            .append ("rowSpan", m_nRowspan)
                            .append ("align", m_eAlign)
                            .toString ();
  }
}
