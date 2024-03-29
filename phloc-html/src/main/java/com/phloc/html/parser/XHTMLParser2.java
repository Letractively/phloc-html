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
package com.phloc.html.parser;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phloc.commons.ValueEnforcer;
import com.phloc.commons.annotations.ReturnsMutableCopy;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.microdom.IMicroContainer;
import com.phloc.commons.microdom.IMicroDocument;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.IMicroNode;
import com.phloc.commons.microdom.impl.MicroContainer;
import com.phloc.commons.microdom.serialize.MicroReader;
import com.phloc.commons.regex.RegExHelper;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.xml.CXML;
import com.phloc.commons.xml.EXMLIncorrectCharacterHandling;
import com.phloc.commons.xml.EXMLParserFeature;
import com.phloc.commons.xml.serialize.EXMLSerializeVersion;
import com.phloc.commons.xml.serialize.ISAXReaderSettings;
import com.phloc.commons.xml.serialize.SAXReaderSettings;
import com.phloc.commons.xml.serialize.XMLEmitterPhloc;
import com.phloc.html.EHTMLElement;
import com.phloc.html.EHTMLVersion;
import com.phloc.html.entities.HTMLEntityResolver;
import com.phloc.html.hc.IHCNode;
import com.phloc.html.hc.htmlext.HCUtils;
import com.phloc.html.hc.impl.HCDOMWrapper;
import com.phloc.html.hc.impl.HCTextNode;

/**
 * Utility class for parsing stuff as HTML.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class XHTMLParser2
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (XHTMLParser2.class);

  private final EHTMLVersion m_eHTMLVersion;

  // By default enable a little secured reader settings.
  // * DOCTYPE must be allowed because it is common in HTML files
  // * parameter entities must be allowed, because otherwise the HTML DTDs
  // cannot be read correctly
  // Note: SECURE_PROCESSING is not available in JDK parser 1.6.0_32 (most
  // probably also not in the previous versions)

  private SAXReaderSettings m_aAdditionalSAXReaderSettings = new SAXReaderSettings ().setFeatureValue (EXMLParserFeature.SECURE_PROCESSING,
                                                                                                       true)
                                                                                     .setFeatureValue (EXMLParserFeature.EXTERNAL_GENERAL_ENTITIES,
                                                                                                       false);

  public XHTMLParser2 ()
  {
    this (EHTMLVersion.DEFAULT);
  }

  public XHTMLParser2 (@Nonnull final EHTMLVersion eHTMLVersion)
  {
    this.m_eHTMLVersion = ValueEnforcer.notNull (eHTMLVersion, "HTMLVersion");
  }

  /**
   * @return The HTML version as specified in the constructor. Never
   *         <code>null</code>.
   */
  @Nonnull
  public EHTMLVersion getHTMLVersion ()
  {
    return this.m_eHTMLVersion;
  }

  /**
   * @return A copy of the additional SAX reader settings that are used for
   *         parsing. By default a secure processing is active, that disallows
   *         inline DTDs in HTML documents.
   */
  @Nonnull
  @ReturnsMutableCopy
  public SAXReaderSettings getAdditionalSAXReaderSettings ()
  {
    // Return a clone
    return this.m_aAdditionalSAXReaderSettings.getClone ();
  }

  /**
   * Set additional SAX reader settings that are used when an XHTML fragment is
   * read. All settings are reused when parsing except for the entity resolver
   * which is always set to the default {@link HTMLEntityResolver}.
   *
   * @param aAdditionalSaxReaderSettings
   *        The settings to be used. May be <code>null</code>.
   */
  public void setAdditionalSAXReaderSettings (@Nullable final ISAXReaderSettings aAdditionalSaxReaderSettings)
  {
    this.m_aAdditionalSAXReaderSettings = SAXReaderSettings.createCloneOnDemand (aAdditionalSaxReaderSettings);
  }

  /**
   * Check whether the passed text looks like it contains XHTML code. This is a
   * heuristic check only and does not perform actual parsing!
   *
   * @param sText
   *        The text to check.
   * @return <code>true</code> if the text looks like HTML
   */
  public static boolean looksLikeXHTML (@Nullable final String sText)
  {
    // If the text contains an open angle bracket followed by a character that
    // we think of it as HTML
    // (?s) enables the "dotall" mode - see Pattern.DOTALL
    return StringHelper.hasText (sText) && RegExHelper.stringMatchesPattern ("(?s).*<[a-zA-Z].+", sText);
  }

  /**
   * Check if the given fragment is valid XHTML 1.1 mark-up. This method tries
   * to parse the XHTML fragment, so it is potentially slow!
   *
   * @param sXHTMLFragment
   *        The XHTML fragment to parse. It is not checked, whether the value
   *        looks like HTML or not.
   * @return <code>true</code> if the fragment is valid, <code>false</code>
   *         otherwise.
   */
  public boolean isValidXHTMLFragment (@Nullable final String sXHTMLFragment)
  {
    return StringHelper.hasNoText (sXHTMLFragment) || parseXHTMLFragment (sXHTMLFragment) != null;
  }

  /**
   * Parse the given fragment as XHTML 1.1. This is a sanity method for
   * {@link #parseXHTMLFragment(String)} with the predefined XHTML 1.1 document
   * type.
   *
   * @param sXHTMLFragment
   *        The XHTML fragment to parse. May be <code>null</code>.
   * @return <code>null</code> if parsing failed.
   */
  @Nullable
  public IMicroDocument parseXHTMLFragment (@Nullable final String sXHTMLFragment)
  {
    // Build mini HTML and insert fragment in the middle.
    // If parsing succeeds, it is considered valid HTML.
    final String sHTMLNamespaceURI = this.m_eHTMLVersion.getNamespaceURI ();
    final String sXHTML = XMLEmitterPhloc.getDocTypeHTMLRepresentation (EXMLSerializeVersion.XML_10,
                                                                        EXMLIncorrectCharacterHandling.DEFAULT,
                                                                        this.m_eHTMLVersion.getDocType ()) +
                          "<html" +
                          (sHTMLNamespaceURI != null ? ' ' + CXML.XML_ATTR_XMLNS + "=\"" + sHTMLNamespaceURI + '"' : "") +
                          "><head><title></title></head><body>" +
                          StringHelper.getNotNull (sXHTMLFragment) +
                          "</body></html>";
    return parseXHTMLDocument (sXHTML);
  }

  /**
   * This method parses a full HTML document into a {@link IMicroDocument} using
   * the additional SAX reader settings and always the
   * {@link HTMLEntityResolver} as an entity resolver.
   *
   * @param sXHTML
   *        The complete XHTML document as a string. May be <code>null</code>.
   * @return <code>null</code> if interpretation failed
   */
  @Nullable
  public IMicroDocument parseXHTMLDocument (@Nullable final String sXHTML)
  {
    return MicroReader.readMicroXML (sXHTML,
                                     this.m_aAdditionalSAXReaderSettings.getClone ()
                                                                        .setEntityResolver (HTMLEntityResolver.getInstance ()));
  }

  /**
   * Interpret the passed XHTML fragment as HTML and retrieve a result container
   * with all body elements.
   *
   * @param sXHTML
   *        The XHTML text fragment. This fragment is parsed as an HTML body and
   *        may therefore not contain the &lt;body&gt; tag.
   * @return <code>null</code> if the passed text could not be interpreted as
   *         XHTML or if no body element was found, an {@link IMicroContainer}
   *         with all body children otherwise.
   */
  @Nullable
  public IMicroContainer unescapeXHTMLFragment (@Nullable final String sXHTML)
  {
    // Ensure that the content is surrounded by a single tag
    final IMicroDocument aDoc = parseXHTMLFragment (sXHTML);
    if (aDoc != null && aDoc.getDocumentElement () != null)
    {
      // Find "body" case insensitive
      final IMicroElement eBody = HCUtils.getFirstChildElement (aDoc.getDocumentElement (), EHTMLElement.BODY);
      if (eBody != null)
      {
        final IMicroContainer ret = new MicroContainer ();
        if (eBody.hasChildren ())
        {
          // Make a copy of the list, because it is modified in
          // detachFromParent!
          for (final IMicroNode aChildNode : ContainerHelper.newList (eBody.getChildren ()))
            ret.appendChild (aChildNode.detachFromParent ());
        }
        return ret;
      }
    }
    return null;
  }

  /**
   * If the passed text looks like XHTML, unescape it (using
   * {@link #unescapeXHTMLFragment(String)}) else return a simple text node.
   *
   * @param sText
   *        The text to be converted. May be <code>null</code>.
   * @return A non-<code>null</code> IHCNode with the result representation
   *         (e.g. an {@link HCTextNode} or an {@link HCDOMWrapper} with an
   *         {@link IMicroContainer} having all the body elements)
   */
  @Nonnull
  public IHCNode convertToXHTMLFragmentOnDemand (@Nullable final String sText)
  {
    if (looksLikeXHTML (sText))
    {
      final IMicroContainer aCont = unescapeXHTMLFragment (sText);
      if (aCont != null)
        return new HCDOMWrapper (aCont);
      s_aLogger.error ("Failed to unescape XHTML:\n" + sText);
    }
    return new HCTextNode (sText);
  }
}
