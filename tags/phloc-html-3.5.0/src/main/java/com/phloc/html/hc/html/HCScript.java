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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phloc.commons.annotations.DevelopersNote;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.IMicroNodeWithChildren;
import com.phloc.commons.microdom.impl.MicroText;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.html.hc.conversion.IHCConversionSettings;
import com.phloc.html.js.IJSCodeProvider;
import com.phloc.html.js.provider.UnparsedJSCodeProvider;

/**
 * This class represents an HTML &lt;script&gt; element with inline content.
 * 
 * @author philip
 * @see HCScriptFile
 */
public class HCScript extends AbstractHCScript <HCScript> implements IJSCodeProvider
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
    PLAIN_TEXT_NO_ESCAPE,
    /**
     * Wrap the whole JS code in XML comments
     */
    WRAP_IN_COMMENT,
    /**
     * Wrap the whole JS code in an XML CDATA container
     */
    CDATA;
  }

  public static final EMode DEFAULT_MODE = EMode.WRAP_IN_COMMENT;
  private static final Logger s_aLogger = LoggerFactory.getLogger (HCScript.class);

  private static EMode s_eDefaultMode = DEFAULT_MODE;

  private final IJSCodeProvider m_aProvider;
  private String m_sJSCode;
  private EMode m_eMode = s_eDefaultMode;

  public HCScript (@Nonnull final IJSCodeProvider aProvider)
  {
    super ();
    if (aProvider == null)
      throw new NullPointerException ("provider");
    m_aProvider = aProvider;
  }

  @DevelopersNote ("Handle with care!")
  public HCScript (@Nonnull final String sJSCode)
  {
    this (new UnparsedJSCodeProvider (sJSCode));
  }

  public boolean isInlineJS ()
  {
    return true;
  }

  @Nonnull
  public IJSCodeProvider getJSCodeProvider ()
  {
    return m_aProvider;
  }

  /**
   * @deprecated Use {@link #getJSCode()} instead
   */
  @Deprecated
  @Nullable
  public String getJSContent ()
  {
    return getJSCode ();
  }

  @Nullable
  public String getJSCode ()
  {
    return m_aProvider.getJSCode ();
  }

  @Nonnull
  public EMode getMode ()
  {
    return m_eMode;
  }

  @Nonnull
  public HCScript setMode (@Nonnull final EMode eMode)
  {
    if (eMode == null)
      throw new NullPointerException ("mode");
    m_eMode = eMode;
    return this;
  }

  public static void setInlineScript (@Nonnull final IMicroNodeWithChildren aElement,
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
        case WRAP_IN_COMMENT:
          // <script>
          // my script bla
          // //</script>
          if (StringHelper.getLastChar (sContent) == '\n')
            aElement.appendComment ("\n" + sContent + "//");
          else
            aElement.appendComment ("\n" + sContent + "\n//");
          break;
        case CDATA:
          // Tested OK with FF6, Opera11, Chrome13, IE8, IE9
          /**
           * <pre>
           * //<![CDATA[
           * my script bla//]]>
           * </pre>
           */
          aElement.appendText ("//");
          aElement.appendCDATA ("\n" + sContent + "//");
          break;
      }
  }

  @Override
  protected boolean canConvertToNode (@Nonnull final IHCConversionSettings aConversionSettings)
  {
    m_sJSCode = getJSCode ();
    // Don't create script elements with empty content....
    return StringHelper.hasText (m_sJSCode);
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettings aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);

    // m_sJSCode is set in canConvertToNode which is called before this method!
    setInlineScript (aElement, m_sJSCode, m_eMode);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("provider", m_aProvider)
                            .append ("mode", m_eMode)
                            .toString ();
  }

  /**
   * Set how the content of script elements should be emitted. This only affects
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
    s_aLogger.info ("Default <script> mode set to " + eMode);
  }

  /**
   * @return The default mode to emit script content. Never <code>null</code>.
   */
  @Nonnull
  public static EMode getDefaultMode ()
  {
    return s_eDefaultMode;
  }
}
