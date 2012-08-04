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

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.microdom.IMicroElement;
import com.phloc.html.CHTMLAttributes;
import com.phloc.html.EHTMLElement;
import com.phloc.html.hc.CHCParam;
import com.phloc.html.hc.api.EHCInputType;
import com.phloc.html.hc.conversion.HCConversionSettings;
import com.phloc.html.hc.impl.AbstractHCElement;

public class HCHiddenField extends AbstractHCElement <HCHiddenField>
{
  private String m_sName;
  private String m_sValue;

  public HCHiddenField ()
  {
    super (EHTMLElement.INPUT);
  }

  public HCHiddenField (@Nullable final String sName, @Nullable final String sValue)
  {
    this ();
    setName (sName);
    setValue (sValue);
  }

  public HCHiddenField (@Nullable final String sID, @Nullable final String sName, @Nullable final String sValue)
  {
    this (sName, sValue);
    setID (sID);
  }

  public HCHiddenField (@Nullable final String sName, final int nValue)
  {
    this (sName, Integer.toString (nValue));
  }

  public HCHiddenField (@Nullable final String sName, @Nonnull final Locale aLocale)
  {
    this (sName, aLocale.toString ());
  }

  public HCHiddenField (@Nullable final String sName, final boolean bValue)
  {
    this ();
    setName (sName);
    setValue (bValue);
  }

  @Nonnull
  public final HCHiddenField setName (@Nullable final String sName)
  {
    m_sName = sName;
    return this;
  }

  @Nonnull
  public final HCHiddenField setValue (final boolean bValue)
  {
    return setValue (bValue ? CHCParam.VALUE_CHECKED : CHCParam.VALUE_UNCHECKED);
  }

  @Nonnull
  public final HCHiddenField setValue (@Nullable final String sValue)
  {
    m_sValue = sValue;
    return this;
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final HCConversionSettings aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    aElement.setAttribute (CHTMLAttributes.TYPE, EHCInputType.HIDDEN.getAttrValue ());
    if (m_sName != null)
      aElement.setAttribute (CHTMLAttributes.NAME, m_sName);
    if (m_sValue != null)
      aElement.setAttribute (CHTMLAttributes.VALUE, m_sValue);
  }

  @Nonnull
  public String getPlainText ()
  {
    return "";
  }
}