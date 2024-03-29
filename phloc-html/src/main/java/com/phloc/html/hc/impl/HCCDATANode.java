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

import com.phloc.commons.microdom.IMicroCDATA;
import com.phloc.commons.microdom.impl.MicroCDATA;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.commons.text.IPredefinedLocaleTextProvider;
import com.phloc.html.hc.conversion.IHCConversionSettingsToNode;

/**
 * Represents a single CDATA node as HC node.
 * 
 * @author Philip Helger
 */
public class HCCDATANode extends AbstractHCNode
{
  private final String m_sText;

  public HCCDATANode (@Nonnull final IPredefinedLocaleTextProvider aTextProvider)
  {
    this (aTextProvider.getText ());
  }

  public HCCDATANode (@Nullable final String sText)
  {
    m_sText = sText == null ? "" : sText;
  }

  /**
   * @return The unescaped CDATA content text.
   */
  @Nonnull
  public String getText ()
  {
    return m_sText;
  }

  @Override
  @Nonnull
  protected IMicroCDATA internalConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    return new MicroCDATA (m_sText);
  }

  @Override
  @Nonnull
  public String getPlainText ()
  {
    return m_sText;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("text", m_sText).toString ();
  }
}
