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
package com.phloc.html.hc.impl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.NotThreadSafe;

import com.phloc.commons.ValueEnforcer;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.html.CHTMLAttributes;
import com.phloc.html.EHTMLElement;
import com.phloc.html.hc.IHCInput;
import com.phloc.html.hc.api.EHCInputType;
import com.phloc.html.hc.conversion.IHCConversionSettingsToNode;

// TODO change to http://dev.w3.org/html5/markup/input.text.html#input.text
@NotThreadSafe
public abstract class AbstractHCInput <THISTYPE extends AbstractHCInput <THISTYPE>> extends AbstractHCControl <THISTYPE> implements IHCInput <THISTYPE>
{
  private EHCInputType m_eType;
  private String m_sPlaceholder;

  public AbstractHCInput ()
  {
    super (EHTMLElement.INPUT);
  }

  public AbstractHCInput (@Nonnull final EHCInputType eType)
  {
    this ();
    setType (eType);
  }

  @Nonnull
  public final EHCInputType getType ()
  {
    return m_eType;
  }

  @Nonnull
  public final THISTYPE setType (@Nonnull final EHCInputType eType)
  {
    m_eType = ValueEnforcer.notNull (eType, "Type");
    return thisAsT ();
  }

  @Nullable
  public final String getPlaceholder ()
  {
    return m_sPlaceholder;
  }

  @Nonnull
  public final THISTYPE setPlaceholder (@Nullable final String sPlaceholder)
  {
    m_sPlaceholder = sPlaceholder;
    return thisAsT ();
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (m_eType != null)
      aElement.setAttribute (CHTMLAttributes.TYPE, m_eType);
    if (StringHelper.hasText (m_sPlaceholder))
      aElement.setAttribute (CHTMLAttributes.PLACEHOLDER, m_sPlaceholder);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("type", m_eType)
                            .appendIfNotNull ("placeholder", m_sPlaceholder)
                            .toString ();
  }
}
