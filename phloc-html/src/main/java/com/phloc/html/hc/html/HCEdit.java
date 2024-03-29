/**
 * Copyright (C) 2006-2014 phloc systems
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
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.html.CHTMLAttributeValues;
import com.phloc.html.CHTMLAttributes;
import com.phloc.html.hc.api.EHCInputType;
import com.phloc.html.hc.conversion.IHCConversionSettingsToNode;
import com.phloc.html.request.IHCRequestField;

/**
 * Represents an HTML &lt;input&gt; element with type "text"
 * 
 * @author Philip Helger
 */
public class HCEdit extends AbstractHCEdit <HCEdit>
{
  /** By default auto complete is not disabled */
  public static final boolean DEFAULT_DISABLE_AUTO_COMPLETE = false;

  private String m_sValue;
  private boolean m_bDisableAutoComplete = DEFAULT_DISABLE_AUTO_COMPLETE;

  public HCEdit ()
  {
    super (EHCInputType.TEXT);
  }

  public HCEdit (@Nullable final String sName)
  {
    super (EHCInputType.TEXT, sName);
  }

  public HCEdit (@Nullable final String sName, @Nullable final String sValue)
  {
    this (sName);
    setValue (sValue);
  }

  public HCEdit (@Nullable final String sName, final int nValue)
  {
    this (sName);
    setValue (nValue);
  }

  public HCEdit (@Nullable final String sName, final long nValue)
  {
    this (sName);
    setValue (nValue);
  }

  public HCEdit (@Nonnull final IHCRequestField aRF)
  {
    this (aRF.getFieldName (), aRF.getRequestValue ());
  }

  @Nullable
  public final String getValue ()
  {
    return m_sValue;
  }

  @Nonnull
  public final HCEdit setValue (final int nValue)
  {
    return setValue (Integer.toString (nValue));
  }

  @Nonnull
  public final HCEdit setValue (final long nValue)
  {
    return setValue (Long.toString (nValue));
  }

  @Nonnull
  public final HCEdit setValue (@Nullable final String sValue)
  {
    m_sValue = sValue;
    return this;
  }

  public final boolean isDisableAutoComplete ()
  {
    return m_bDisableAutoComplete;
  }

  @Nonnull
  public final HCEdit setDisableAutoComplete (final boolean bDisableAutoComplete)
  {
    m_bDisableAutoComplete = bDisableAutoComplete;
    return this;
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (m_sValue != null)
      aElement.setAttribute (CHTMLAttributes.VALUE, m_sValue);
    if (m_bDisableAutoComplete)
      aElement.setAttribute (CHTMLAttributes.AUTOCOMPLETE, CHTMLAttributeValues.OFF);
  }

  @Override
  public String getPlainText ()
  {
    return StringHelper.getNotNull (m_sValue);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("value", m_sValue)
                            .append ("disableAutoComplete", m_bDisableAutoComplete)
                            .toString ();
  }
}
