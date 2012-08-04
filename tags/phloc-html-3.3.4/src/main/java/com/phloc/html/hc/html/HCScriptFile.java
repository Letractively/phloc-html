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
import com.phloc.commons.mime.CMimeType;
import com.phloc.commons.mime.IMimeType;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.commons.url.ISimpleURL;
import com.phloc.html.CHTMLAttributeValues;
import com.phloc.html.CHTMLAttributes;
import com.phloc.html.EHTMLElement;
import com.phloc.html.hc.conversion.IHCConversionSettings;
import com.phloc.html.hc.impl.AbstractHCElement;

/**
 * Represents an HTML &lt;script&gt; element that loads the code from a source
 * URL.
 * 
 * @author philip
 * @see HCScript
 */
public final class HCScriptFile extends AbstractHCElement <HCScriptFile>
{
  public static final IMimeType DEFAULT_TYPE = CMimeType.TEXT_JAVASCRIPT;
  public static final boolean DEFAULT_DEFER = false;

  private IMimeType m_aType = DEFAULT_TYPE;
  private ISimpleURL m_aSrc;
  private boolean m_bDefer = DEFAULT_DEFER;

  public HCScriptFile ()
  {
    super (EHTMLElement.SCRIPT);
  }

  public HCScriptFile (@Nullable final ISimpleURL aSrc)
  {
    this ();
    setSrc (aSrc);
  }

  public HCScriptFile (@Nullable final ISimpleURL aSrc, final boolean bDefer)
  {
    this (aSrc);
    setDefer (bDefer);
  }

  @Nonnull
  public IMimeType getType ()
  {
    return m_aType;
  }

  @Nonnull
  public HCScriptFile setType (@Nonnull final IMimeType aType)
  {
    if (aType == null)
      throw new NullPointerException ("type");
    m_aType = aType;
    return this;
  }

  @Nullable
  public ISimpleURL getSrc ()
  {
    return m_aSrc;
  }

  @Nonnull
  public HCScriptFile setSrc (@Nullable final ISimpleURL aSrc)
  {
    m_aSrc = aSrc;
    return this;
  }

  public boolean isDefer ()
  {
    return m_bDefer;
  }

  @Nonnull
  public HCScriptFile setDefer (final boolean bDefer)
  {
    m_bDefer = bDefer;
    return this;
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettings aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    aElement.setAttribute (CHTMLAttributes.TYPE, m_aType.getAsString ());
    if (m_aSrc != null)
      aElement.setAttribute (CHTMLAttributes.SRC, m_aSrc.getAsString ());
    if (m_bDefer)
      aElement.setAttribute (CHTMLAttributes.DEFER, CHTMLAttributeValues.DEFER);

    // Tag may not be self closed
    aElement.appendText ("");
  }

  public String getPlainText ()
  {
    return "";
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("type", m_aType)
                            .appendIfNotNull ("src", m_aSrc)
                            .append ("defer", m_bDefer)
                            .toString ();
  }
}
