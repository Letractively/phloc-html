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

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.Immutable;

import com.phloc.commons.ValueEnforcer;
import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.charset.CCharset;
import com.phloc.commons.microdom.IMicroNode;
import com.phloc.commons.microdom.serialize.MicroWriter;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.commons.version.Version;
import com.phloc.commons.xml.serialize.IXMLWriterSettings;
import com.phloc.commons.xml.serialize.XMLWriterSettings;
import com.phloc.html.hc.IHCNode;
import com.phloc.html.hc.conversion.IHCConversionSettingsToNode;

/**
 * Represents an HTML conditional comment for IE specific usage. E.g.
 *
 * <pre>
 * &lt;!--[if IE 6]&gt;
 * Special instructions for IE 6 here
 * &lt;![endif]--&gt;
 * </pre>
 *
 * @author Philip Helger
 */
@Immutable
public class HCConditionalCommentNode extends AbstractHCWrappingNode
{
  public static final String DEFAULT_LINE_SEPARATOR = XMLWriterSettings.DEFAULT_NEWLINE_STRING;
  public static final Version IE5 = new Version (5);
  public static final Version IE6 = new Version (6);
  public static final Version IE7 = new Version (7);
  public static final Version IE8 = new Version (8);
  public static final Version IE9 = new Version (9);
  public static final Version IE10 = new Version (10);
  public static final Version IE11 = new Version (11);

  private static final ReadWriteLock s_aRWLock = new ReentrantReadWriteLock ();

  @GuardedBy ("s_aRWLock")
  private static String s_sDefaultLineSeparator = DEFAULT_LINE_SEPARATOR;

  private String m_sCondition;
  private final IHCNode m_aWrappedNode;
  private String m_sLineSeparator = getDefaultLineSeparator ();

  public HCConditionalCommentNode (@Nonnull @Nonempty final String sCondition, @Nonnull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notNull (aWrappedNode, "WrappedNode");
    if (aWrappedNode instanceof HCCommentNode)
      throw new IllegalArgumentException ("You cannot wrap a comment inside a conditional comment");
    if (aWrappedNode instanceof HCConditionalCommentNode)
      throw new IllegalArgumentException ("You cannot wrap a conditional comment inside another conditional comment");
    setCondition (sCondition);
    m_aWrappedNode = aWrappedNode;
  }

  /**
   * @return The condition for the conditional comment. Neither
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public String getCondition ()
  {
    return m_sCondition;
  }

  @Nonnull
  public HCConditionalCommentNode setCondition (@Nonnull @Nonempty final String sCondition)
  {
    ValueEnforcer.notEmpty (sCondition, "Condition");
    m_sCondition = sCondition;
    return this;
  }

  @Nonnull
  public IHCNode getWrappedNode ()
  {
    return m_aWrappedNode;
  }

  @Nonnull
  @Nonempty
  public String getLineSeparator ()
  {
    return m_sLineSeparator;
  }

  @Nonnull
  public HCConditionalCommentNode setLineSeparator (@Nonnull @Nonempty final String sLineSeparator)
  {
    ValueEnforcer.notEmpty (sLineSeparator, "LineSeparator");
    m_sLineSeparator = sLineSeparator;
    return this;
  }

  @Nonnull
  @Nonempty
  private String _getCommentText (@Nonnull final IMicroNode aNode, @Nonnull final IXMLWriterSettings aXMLWriterSettings)
  {
    // Only create a newline when alignment is enabled
    final String sLineSeparator = aXMLWriterSettings.getIndent ().isAlign () ? m_sLineSeparator : "";
    return '[' +
           m_sCondition +
           "]>" +
           sLineSeparator +
           MicroWriter.getNodeAsString (aNode, aXMLWriterSettings) +
           "<![endif]";
  }

  /**
   * This method wraps an arbitrary HC node in a conditional node. The passed
   * node is simply converted to an XML string and the content is put into the
   * conditional comment.
   *
   * @param aConversionSettings
   *        Conversion settings to be used.
   * @return The wrapped node. Never <code>null</code>.
   */
  @Nonnull
  public HCCommentNode getCommentNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // First convert the contained node to a micro node
    final IMicroNode aWrappedMicroNode = m_aWrappedNode.convertToNode (aConversionSettings);
    // Now wrap the created XML in the special format required for a conditional
    // comment
    final String sWrappedXML = _getCommentText (aWrappedMicroNode, aConversionSettings.getXMLWriterSettings ());
    return new HCCommentNode (sWrappedXML);
  }

  @Override
  @Nullable
  protected IMicroNode internalConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    return getCommentNode (aConversionSettings).convertToNode (aConversionSettings);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("condition", m_sCondition)
                                       .append ("lineSeparator",
                                                StringHelper.getHexEncoded (m_sLineSeparator,
                                                                            CCharset.CHARSET_ISO_8859_1_OBJ))
                                       .append ("wrappedNode", m_aWrappedNode)
                                       .toString ();
  }

  @Nonnull
  @Nonempty
  public static String getDefaultLineSeparator ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_sDefaultLineSeparator;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  public static void setDefaultLineSeparator (@Nonnull @Nonempty final String sDefaultLineSeparator)
  {
    if (StringHelper.hasNoText (sDefaultLineSeparator))
      throw new IllegalArgumentException ("defaultLineSeparator");

    s_aRWLock.writeLock ().lock ();
    try
    {
      s_sDefaultLineSeparator = sDefaultLineSeparator;
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  @Nonnull
  public static HCConditionalCommentNode createForIE (@Nonnull final IHCNode aWrappedNode)
  {
    return new HCConditionalCommentNode ("if IE", aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEExactVersion (@Nonnull final Version aVersion,
                                                                  @Nonnull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notNull (aVersion, "Version");

    return new HCConditionalCommentNode ("if IE " + aVersion.getAsString (), aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEExactVersion5 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE5, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEExactVersion6 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE6, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEExactVersion7 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE7, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEExactVersion8 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE8, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEExactVersion9 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE9, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEExactVersion10 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE10, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEExactVersion11 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE11, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIENotVersion (@Nonnull final Version aVersion,
                                                                @Nonnull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notNull (aVersion, "Version");

    return new HCConditionalCommentNode ("if !IE " + aVersion.getAsString (), aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerThanVersion (@Nonnull final Version aVersion,
                                                                      @Nonnull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notNull (aVersion, "Version");
    return new HCConditionalCommentNode ("if lt IE " + aVersion.getAsString (), aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerThanVersion5 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE5, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerThanVersion6 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE6, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerThanVersion7 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE7, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerThanVersion8 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE8, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerThanVersion9 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE9, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerThanVersion10 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE10, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerThanVersion11 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE11, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion (@Nonnull final Version aVersion,
                                                                             @Nonnull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notNull (aVersion, "Version");

    return new HCConditionalCommentNode ("if lte IE " + aVersion.getAsString (), aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion5 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE5, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion6 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE6, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion7 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE7, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion8 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE8, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion9 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE9, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion10 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE10, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion11 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE11, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterThanVersion (@Nonnull final Version aVersion,
                                                                        @Nonnull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notNull (aVersion, "Version");

    return new HCConditionalCommentNode ("if gt IE " + aVersion.getAsString (), aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterThanVersion5 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE5, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterThanVersion6 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE6, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterThanVersion7 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE7, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterThanVersion8 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE8, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterThanVersion9 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE9, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterThanVersion10 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE10, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterThanVersion11 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE11, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion (@Nonnull final Version aVersion,
                                                                               @Nonnull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notNull (aVersion, "Version");

    return new HCConditionalCommentNode ("if gte IE " + aVersion.getAsString (), aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion5 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE5, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion6 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE6, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion7 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE7, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion8 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE8, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion9 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE9, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion10 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE10, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion11 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE11, aWrappedNode);
  }

  @Nullable
  public static HCConditionalCommentNode getFromStringOrNull (@Nullable final String sValue,
                                                              @Nonnull final IHCNode aWrappedNode)
  {
    if (StringHelper.hasNoText (sValue))
      return null;
    return new HCConditionalCommentNode (sValue, aWrappedNode);
  }
}
