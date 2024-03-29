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
package com.phloc.html.hc.conversion;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.phloc.commons.ValueEnforcer;
import com.phloc.commons.annotations.ReturnsMutableCopy;
import com.phloc.commons.annotations.ReturnsMutableObject;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.commons.xml.EXMLIncorrectCharacterHandling;
import com.phloc.commons.xml.serialize.EXMLSerializeFormat;
import com.phloc.commons.xml.serialize.EXMLSerializeIndent;
import com.phloc.commons.xml.serialize.IXMLWriterSettings;
import com.phloc.commons.xml.serialize.XMLWriterSettings;
import com.phloc.css.ECSSVersion;
import com.phloc.css.ICSSWriterSettings;
import com.phloc.css.writer.CSSWriterSettings;
import com.phloc.html.EHTMLVersion;
import com.phloc.html.hc.customize.HCDefaultCustomizer;
import com.phloc.html.hc.customize.IHCCustomizer;

@NotThreadSafe
public class HCConversionSettings implements IHCConversionSettings
{
  // Is implied from default XMLWriter settings
  /** Default indent and align HTML: true */
  public static final boolean DEFAULT_INDENT_AND_ALIGN_HTML = true;
  /** Default CSS version 3.0 */
  public static final ECSSVersion DEFAULT_CSS_VERSION = ECSSVersion.CSS30;
  /** Default indent and align CSS: true */
  public static final boolean DEFAULT_INDENT_AND_ALIGN_CSS = true;
  /** Default consistency checks: true */
  public static final boolean DEFAULT_CONSISTENCY_CHECKS = true;
  /** Default extract out-of-band nodes: true */
  public static final boolean DEFAULT_EXTRACT_OUT_OF_BAND_NODES = true;

  private final EHTMLVersion m_eHTMLVersion;
  private final String m_sHTMLNamespaceURI;
  private XMLWriterSettings m_aXMLWriterSettings;
  private CSSWriterSettings m_aCSSWriterSettings;
  private boolean m_bConsistencyChecksEnabled;
  private boolean m_bExtractOutOfBandNodes;
  private IHCCustomizer m_aCustomizer;

  @Nonnull
  public static XMLWriterSettings createDefaultXMLWriterSettings ()
  {
    return new XMLWriterSettings ().setFormat (EXMLSerializeFormat.XHTML)
                                   .setIncorrectCharacterHandling (EXMLIncorrectCharacterHandling.DO_NOT_WRITE_LOG_WARNING)
                                   .setIndent (DEFAULT_INDENT_AND_ALIGN_HTML ? EXMLSerializeIndent.INDENT_AND_ALIGN
                                                                            : EXMLSerializeIndent.NONE);
  }

  @Nonnull
  public static CSSWriterSettings createDefaultCSSWriterSettings ()
  {
    return new CSSWriterSettings (DEFAULT_CSS_VERSION, !DEFAULT_INDENT_AND_ALIGN_CSS);
  }

  @Nonnull
  public static IHCCustomizer createDefaultCustomizer ()
  {
    // Use default constructor for backwards compatibility
    return new HCDefaultCustomizer ();
  }

  /**
   * Constructor
   *
   * @param eHTMLVersion
   *        The HTML version to use. May not be <code>null</code>.
   */
  public HCConversionSettings (@Nonnull final EHTMLVersion eHTMLVersion)
  {
    ValueEnforcer.notNull (eHTMLVersion, "HTMLVersion");

    m_eHTMLVersion = eHTMLVersion;
    m_sHTMLNamespaceURI = eHTMLVersion.getNamespaceURI ();
    m_aXMLWriterSettings = createDefaultXMLWriterSettings ();
    m_aCSSWriterSettings = createDefaultCSSWriterSettings ();
    m_bConsistencyChecksEnabled = DEFAULT_CONSISTENCY_CHECKS;
    m_bExtractOutOfBandNodes = DEFAULT_EXTRACT_OUT_OF_BAND_NODES;
    m_aCustomizer = createDefaultCustomizer ();
  }

  /**
   * Copy ctor. Also creates a copy of the {@link XMLWriterSettings} and the
   * {@link CSSWriterSettings}.
   *
   * @param aBase
   *        Object to copy the settings from. May not be <code>null</code>.
   */
  public HCConversionSettings (@Nonnull final IHCConversionSettings aBase)
  {
    this (aBase, aBase.getHTMLVersion ());
  }

  /**
   * Kind of copy ctor. Also creates a copy of the {@link XMLWriterSettings} and
   * the {@link CSSWriterSettings}.
   *
   * @param aBase
   *        Object to copy the settings from. May not be <code>null</code>.
   * @param eHTMLVersion
   *        A different HTML version to use than the one from the base settings
   */
  public HCConversionSettings (@Nonnull final IHCConversionSettings aBase, @Nonnull final EHTMLVersion eHTMLVersion)
  {
    ValueEnforcer.notNull (aBase, "Base");
    ValueEnforcer.notNull (eHTMLVersion, "HTMLVersion");

    m_eHTMLVersion = eHTMLVersion;
    m_sHTMLNamespaceURI = eHTMLVersion.getNamespaceURI ();
    m_aXMLWriterSettings = new XMLWriterSettings (aBase.getXMLWriterSettings ());
    m_aCSSWriterSettings = new CSSWriterSettings (aBase.getCSSWriterSettings ());
    m_bConsistencyChecksEnabled = aBase.areConsistencyChecksEnabled ();
    m_bExtractOutOfBandNodes = aBase.isExtractOutOfBandNodes ();
    m_aCustomizer = aBase.getCustomizer ();
  }

  @Nonnull
  public EHTMLVersion getHTMLVersion ()
  {
    return m_eHTMLVersion;
  }

  @Nullable
  public String getHTMLNamespaceURI ()
  {
    return m_sHTMLNamespaceURI;
  }

  /**
   * Set the XML writer settings to be used. By default values equivalent to
   * {@link XMLWriterSettings#DEFAULT_XML_SETTINGS} are used.
   *
   * @param aXMLWriterSettings
   *        The XML writer settings to be used. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public HCConversionSettings setXMLWriterSettings (@Nonnull final IXMLWriterSettings aXMLWriterSettings)
  {
    ValueEnforcer.notNull (aXMLWriterSettings, "XMLWriterSettings");

    // The objects are cached with indent and no-indent for performance reasons
    m_aXMLWriterSettings = new XMLWriterSettings (aXMLWriterSettings);
    return this;
  }

  @Nonnull
  @ReturnsMutableObject (reason = "Design")
  public XMLWriterSettings getXMLWriterSettings ()
  {
    return m_aXMLWriterSettings;
  }

  @Nonnull
  @ReturnsMutableCopy
  public XMLWriterSettings getMutableXMLWriterSettings ()
  {
    return m_aXMLWriterSettings.getClone ();
  }

  /**
   * Set the CSS writer settings to be used.
   *
   * @param aCSSWriterSettings
   *        The settings. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public HCConversionSettings setCSSWriterSettings (@Nonnull final ICSSWriterSettings aCSSWriterSettings)
  {
    ValueEnforcer.notNull (aCSSWriterSettings, "CSSWriterSettings");

    m_aCSSWriterSettings = new CSSWriterSettings (aCSSWriterSettings);
    return this;
  }

  @Nonnull
  @ReturnsMutableObject (reason = "Design")
  public CSSWriterSettings getCSSWriterSettings ()
  {
    return m_aCSSWriterSettings;
  }

  @Nonnull
  @ReturnsMutableCopy
  public CSSWriterSettings getMutableCSSWriterSettings ()
  {
    return new CSSWriterSettings (m_aCSSWriterSettings);
  }

  /**
   * Enable or disable the consistency checks. It is recommended that the
   * consistency checks are only run in debug mode!
   *
   * @param bConsistencyChecksEnabled
   *        The new value.
   * @return this
   */
  @Nonnull
  public HCConversionSettings setConsistencyChecksEnabled (final boolean bConsistencyChecksEnabled)
  {
    m_bConsistencyChecksEnabled = bConsistencyChecksEnabled;
    return this;
  }

  public boolean areConsistencyChecksEnabled ()
  {
    return m_bConsistencyChecksEnabled;
  }

  /**
   * Enable or disable the extraction of out-of-band nodes.
   *
   * @param bExtractOutOfBandNodes
   *        The new value.
   * @return this
   */
  @Nonnull
  public HCConversionSettings setExtractOutOfBandNodes (final boolean bExtractOutOfBandNodes)
  {
    m_bExtractOutOfBandNodes = bExtractOutOfBandNodes;
    return this;
  }

  public boolean isExtractOutOfBandNodes ()
  {
    return m_bExtractOutOfBandNodes;
  }

  /**
   * Set the global customizer to be used to globally customize created
   * elements.
   *
   * @param aCustomizer
   *        The customizer to be used. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public HCConversionSettings setCustomizer (@Nonnull final IHCCustomizer aCustomizer)
  {
    m_aCustomizer = ValueEnforcer.notNull (aCustomizer, "Customizer");
    return this;
  }

  @Nonnull
  public IHCCustomizer getCustomizer ()
  {
    return m_aCustomizer;
  }

  @Nonnull
  public HCConversionSettings getClone ()
  {
    return new HCConversionSettings (this);
  }

  @Nonnull
  public HCConversionSettings getClone (@Nonnull final EHTMLVersion eHTMLVersion)
  {
    return new HCConversionSettings (this, eHTMLVersion);
  }

  @Nonnull
  public HCConversionSettings getCloneIfNecessary (@Nonnull final EHTMLVersion eHTMLVersion)
  {
    return m_eHTMLVersion.equals (eHTMLVersion) ? this : getClone (eHTMLVersion);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("htmlVersion", m_eHTMLVersion)
                                       .append ("XMLWriterSettings", m_aXMLWriterSettings)
                                       .append ("CSSWriterSettings", m_aCSSWriterSettings)
                                       .append ("consistencyChecksEnabled", m_bConsistencyChecksEnabled)
                                       .append ("extractOutOfBandNodes", m_bExtractOutOfBandNodes)
                                       .append ("customizer", m_aCustomizer)
                                       .toString ();
  }
}
