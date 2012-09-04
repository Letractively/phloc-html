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

import java.io.IOException;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.IMicroNodeWithChildren;
import com.phloc.commons.microdom.impl.MicroText;
import com.phloc.commons.mime.CMimeType;
import com.phloc.commons.mime.IMimeType;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.css.ECSSVersion;
import com.phloc.css.decl.CSSDeclarationList;
import com.phloc.css.decl.CascadingStyleSheet;
import com.phloc.css.media.CSSMediaList;
import com.phloc.css.media.ECSSMedium;
import com.phloc.css.writer.CSSWriter;
import com.phloc.css.writer.CSSWriterSettings;
import com.phloc.html.CHTMLAttributes;
import com.phloc.html.EHTMLElement;
import com.phloc.html.hc.conversion.IHCConversionSettings;
import com.phloc.html.hc.impl.AbstractHCElement;

/**
 * Represents an HTML &lt;style&gt; element
 *
 * @author philip
 */
public class HCStyle extends AbstractHCElement <HCStyle>
{
  public static enum EMode
  {
    /**
     * Emit JS code as plain text, but XML masked
     */
    PLAIN_TEXT,
    /**
     * Emit JS code as plain text, but without XML masking
     */
    PLAIN_TEXT_NO_ESCAPE;
  }

  public static final EMode DEFAULT_MODE = EMode.PLAIN_TEXT_NO_ESCAPE;
  public static final IMimeType DEFAULT_TYPE = CMimeType.TEXT_CSS;
  private static final Logger s_aLogger = LoggerFactory.getLogger (HCStyle.class);

  private static EMode s_eDefaultMode = DEFAULT_MODE;

  private IMimeType m_aType = DEFAULT_TYPE;
  private CSSMediaList m_aMediaList;
  private final String m_sContent;
  private EMode m_eMode = s_eDefaultMode;

  public HCStyle (@Nullable final String sContent)
  {
    super (EHTMLElement.STYLE);
    m_sContent = sContent;
  }

  @Deprecated
  public HCStyle (@Nonnull final CascadingStyleSheet aCSS,
                  @Nonnull final ECSSVersion eVersion,
                  final boolean bOptimizedOutput) throws IOException
  {
    this (new CSSWriter (eVersion, bOptimizedOutput).getCSSAsString (aCSS));
  }

  public HCStyle (@Nonnull final CascadingStyleSheet aCSS, @Nonnull final CSSWriterSettings aSettings) throws IOException
  {
    this (new CSSWriter (aSettings).getCSSAsString (aCSS));
  }

  public HCStyle (@Nonnull final CSSDeclarationList aCSS, @Nonnull final CSSWriterSettings aSettings) throws IOException
  {
    this (new CSSWriter (aSettings).getCSSAsString (aCSS));
  }

  @Nonnull
  public IMimeType getType ()
  {
    return m_aType;
  }

  @Nonnull
  public HCStyle setType (@Nonnull final IMimeType aType)
  {
    if (aType == null)
      throw new NullPointerException ("type");
    m_aType = aType;
    return this;
  }

  @Nullable
  public CSSMediaList getMedia ()
  {
    return m_aMediaList;
  }

  @Nonnull
  public HCStyle setMedia (@Nullable final CSSMediaList aMediaList)
  {
    m_aMediaList = aMediaList;
    return this;
  }

  @Nonnull
  public HCStyle addMedium (@Nonnull final ECSSMedium eMedium)
  {
    if (m_aMediaList == null)
      m_aMediaList = new CSSMediaList ();
    m_aMediaList.addMedium (eMedium);
    return this;
  }

  @Nullable
  public String getStyleContent ()
  {
    return m_sContent;
  }

  @Nonnull
  public EMode getMode ()
  {
    return m_eMode;
  }

  @Nonnull
  public HCStyle setMode (@Nonnull final EMode eMode)
  {
    if (eMode == null)
      throw new NullPointerException ("mode");
    m_eMode = eMode;
    return this;
  }

  @Override
  protected boolean canConvertToNode (@Nonnull final IHCConversionSettings aConversionSettings)
  {
    // Don't create style elements with empty content....
    return StringHelper.hasText (m_sContent);
  }

  public static void setInlineStyle (@Nonnull final IMicroNodeWithChildren aElement,
                                     @Nullable final String sContent,
                                     @Nonnull final EMode eMode)
  {
    if (StringHelper.hasText (sContent))
      switch (eMode)
      {
        case PLAIN_TEXT:
          aElement.appendText (sContent);
          break;
        case PLAIN_TEXT_NO_ESCAPE:
          if (StringHelper.containsIgnoreCase (sContent, "</script>", Locale.US))
            throw new IllegalArgumentException ("The script text contains a closing script tag!");
          aElement.appendChild (new MicroText (sContent).setEscape (false));
          break;
      }
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettings aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    aElement.setAttribute (CHTMLAttributes.TYPE, m_aType.getAsString ());
    if (m_aMediaList != null && m_aMediaList.hasAnyMedia ())
      aElement.setAttribute (CHTMLAttributes.MEDIA, m_aMediaList.getMediaString ());
    setInlineStyle (aElement, m_sContent, m_eMode);
  }

  @Nonnull
  public String getPlainText ()
  {
    return "";
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("type", m_aType)
                            .appendIfNotNull ("mediaList", m_aMediaList)
                            .append ("content", m_sContent)
                            .append ("mode", m_eMode)
                            .toString ();
  }

  /**
   * Set how the content of style elements should be emitted. This only affects
   * new built objects, and does not alter existing objects! The default mode is
   * {@link #DEFAULT_MODE}.
   *
   * @param eMode
   *        The new mode to set. May not be <code>null</code>.
   */
  public static void setDefaultMode (@Nonnull final EMode eMode)
  {
    if (eMode == null)
      throw new NullPointerException ("mode");
    s_eDefaultMode = eMode;
    s_aLogger.info ("Default <style> mode set to " + eMode);
  }

  /**
   * @return The default mode to emit style content. Never <code>null</code>.
   */
  @Nonnull
  public static EMode getDefaultMode ()
  {
    return s_eDefaultMode;
  }
}
