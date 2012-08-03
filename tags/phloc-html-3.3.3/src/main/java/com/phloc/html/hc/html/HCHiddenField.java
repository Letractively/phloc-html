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
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.html.CHTMLAttributes;
import com.phloc.html.hc.CHCParam;
import com.phloc.html.hc.IHCRequestField;
import com.phloc.html.hc.api.EHCInputType;
import com.phloc.html.hc.conversion.IHCConversionSettings;
import com.phloc.html.hc.impl.AbstractHCInput;

/**
 * Represents an HTML &lt;input&gt; element with type "hidden"
 * 
 * @author philip
 */
public class HCHiddenField extends AbstractHCInput <HCHiddenField>
{
  private String m_sValue;

  public HCHiddenField ()
  {
    super (EHCInputType.HIDDEN);
  }

  public HCHiddenField (@Nullable final String sName, @Nullable final String sValue)
  {
    this ();
    setName (sName);
    setValue (sValue);
  }

  @Deprecated
  public HCHiddenField (@Nullable final String sID, @Nullable final String sName, @Nullable final String sValue)
  {
    this (sName, sValue);
    setID (sID);
  }

  public HCHiddenField (@Nullable final String sName, final int nValue)
  {
    this (sName, Integer.toString (nValue));
  }

  public HCHiddenField (@Nullable final String sName, final long nValue)
  {
    this (sName, Long.toString (nValue));
  }

  public HCHiddenField (@Nullable final String sName, @Nonnull final Locale aLocale)
  {
    this (sName, aLocale.toString ());
  }

  public HCHiddenField (@Nullable final String sName, final boolean bValue)
  {
    this (sName, bValue ? CHCParam.VALUE_CHECKED : CHCParam.VALUE_UNCHECKED);
  }

  public HCHiddenField (@Nonnull final IHCRequestField aRF)
  {
    this (aRF.getFieldName (), aRF.getRequestValue ());
  }

  @Nullable
  public final String getValue ()
  {
    return m_sValue;
  }

  @Deprecated
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
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettings aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (m_sValue != null)
      aElement.setAttribute (CHTMLAttributes.VALUE, m_sValue);
  }

  @Override
  @Nonnull
  public String getPlainText ()
  {
    return "";
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).appendIfNotNull ("value", m_sValue).toString ();
  }
}
