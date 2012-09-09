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
package com.phloc.html.parser.tools;

import java.io.File;
import java.util.Locale;

import com.phloc.commons.charset.CCharset;
import com.phloc.commons.io.file.SimpleFileIO;

public class MainCreateHCClasses
{
  private static final String TEMPLATE = "package com.phloc.html.hc.html;\r\n"
                                         + "\r\n"
                                         + "import javax.annotation.Nonnull;\r\n"
                                         + "import javax.annotation.Nullable;\r\n"
                                         + "\r\n"
                                         + "import com.phloc.commons.text.IPredefinedLocaleTextProvider;\r\n"
                                         + "import com.phloc.html.EHTMLElement;\r\n"
                                         + "import com.phloc.html.hc.IHCNode;\r\n"
                                         + "import com.phloc.html.hc.impl.AbstractHCElementWithChildren;\r\n"
                                         + "\r\n"
                                         + "/**\r\n"
                                         + " * Represents an HTML &lt;$$$&gt; element<br>\r\n"
                                         + " * This file is automatically generated from " +
                                         MainCreateHCClasses.class.getName () +
                                         " so DO NOT EDIT!\r\n" +
                                         " * \r\n" +
                                         " * @author philip\r\n" +
                                         " */\r\n" +
                                         "public class HC$$ extends $SUPERCLASS$ <HC$$>\r\n" +
                                         "{\r\n" +
                                         "  /**\r\n" +
                                         "   * Create a new $$$ element\r\n" +
                                         "   */\r\n" +
                                         "  public HC$$ ()\r\n" +
                                         "  {\r\n" +
                                         "    super ($SUPERPARAM$);\r\n" +
                                         "  }\r\n" +
                                         "\r\n" +
                                         "  @Deprecated\r\n" +
                                         "  public HC$$ (@Nonnull final IPredefinedLocaleTextProvider aChild)\r\n" +
                                         "  {\r\n" +
                                         "    this ();\r\n" +
                                         "    addChild (aChild);\r\n" +
                                         "  }\r\n" +
                                         "\r\n" +
                                         "  @Deprecated\r\n" +
                                         "  public HC$$ (@Nullable final String sChild)\r\n" +
                                         "  {\r\n" +
                                         "    this ();\r\n" +
                                         "    addChild (sChild);\r\n" +
                                         "  }\r\n" +
                                         "\r\n" +
                                         "  @Deprecated\r\n" +
                                         "  public HC$$ (@Nullable final String... aChildren)\r\n" +
                                         "  {\r\n" +
                                         "    this ();\r\n" +
                                         "    addChildren (aChildren);\r\n" +
                                         "  }\r\n" +
                                         "\r\n" +
                                         "  @Deprecated\r\n" +
                                         "  public HC$$ (@Nullable final IHCNode aChild)\r\n" +
                                         "  {\r\n" +
                                         "    this ();\r\n" +
                                         "    addChild (aChild);\r\n" +
                                         "  }\r\n" +
                                         "\r\n" +
                                         "  @Deprecated\r\n" +
                                         "  public HC$$ (@Nullable final IHCNode... aChildren)\r\n" +
                                         "  {\r\n" +
                                         "    this ();\r\n" +
                                         "    addChildren (aChildren);\r\n" +
                                         "  }\r\n" +
                                         "\r\n" +
                                         "  @Deprecated\r\n" +
                                         "  public HC$$ (@Nullable final Iterable <? extends IHCNode> aChildren)\r\n" +
                                         "  {\r\n" +
                                         "    this ();\r\n" +
                                         "    addChildren (aChildren);\r\n" +
                                         "  }\r\n" +
                                         "\r\n" +
                                         "  /**\r\n" +
                                         "   * Create a new $$$ element with the passed child text\r\n" +
                                         "   * @param aChild The child text provider to be appended. May be <code>null</code>\r\n" +
                                         "   * @return The created HC$$ element and never <code>null</code>\r\n" +
                                         "   */\r\n" +
                                         "  @Nonnull\r\n" +
                                         "  public static HC$$ create (@Nullable final IPredefinedLocaleTextProvider aChild)\r\n" +
                                         "  {\r\n" +
                                         "    return new HC$$ ().addChild (aChild);\r\n" +
                                         "  }\r\n" +
                                         "\r\n" +
                                         "  /**\r\n" +
                                         "   * Create a new $$$ element with the passed child text\r\n" +
                                         "   * @param sChild The child to be appended. May be <code>null</code>\r\n" +
                                         "   * @return The created HC$$ element and never <code>null</code>\r\n" +
                                         "   */\r\n" +
                                         "  @Nonnull\r\n" +
                                         "  public static HC$$ create (@Nullable final String sChild)\r\n" +
                                         "  {\r\n" +
                                         "    return new HC$$ ().addChild (sChild);\r\n" +
                                         "  }\r\n" +
                                         "\r\n" +
                                         "  /**\r\n" +
                                         "   * Create a new $$$ element with the passed child texts\r\n" +
                                         "   * @param aChildren The child texts to be appended. May be <code>null</code>\r\n" +
                                         "   * @return The created HC$$ element and never <code>null</code>\r\n" +
                                         "   */\r\n" +
                                         "  @Nonnull\r\n" +
                                         "  public static HC$$ create (@Nullable final String... aChildren)\r\n" +
                                         "  {\r\n" +
                                         "    return new HC$$ ().addChildren (aChildren);\r\n" +
                                         "  }\r\n" +
                                         "\r\n" +
                                         "  /**\r\n" +
                                         "   * Create a new $$$ element with the passed child node\r\n" +
                                         "   * @param aChild The child node to be appended. May be <code>null</code>\r\n" +
                                         "   * @return The created HC$$ element and never <code>null</code>\r\n" +
                                         "   */\r\n" +
                                         "  @Nonnull\r\n" +
                                         "  public static HC$$ create (@Nullable final IHCNode aChild)\r\n" +
                                         "  {\r\n" +
                                         "    return new HC$$ ().addChild (aChild);\r\n" +
                                         "  }\r\n" +
                                         "\r\n" +
                                         "  /**\r\n" +
                                         "   * Create a new $$$ element with the passed child nodes\r\n" +
                                         "   * @param aChildren The child nodes to be appended. May be <code>null</code>\r\n" +
                                         "   * @return The created HC$$ element and never <code>null</code>\r\n" +
                                         "   */\r\n" +
                                         "  @Nonnull\r\n" +
                                         "  public static HC$$ create (@Nullable final IHCNode... aChildren)\r\n" +
                                         "  {\r\n" +
                                         "    return new HC$$ ().addChildren (aChildren);\r\n" +
                                         "  }\r\n" +
                                         "\r\n" +
                                         "  /**\r\n" +
                                         "   * Create a new $$$ element with the passed child nodes\r\n" +
                                         "   * @param aChildren The child nodes to be appended. May be <code>null</code>\r\n" +
                                         "   * @return The created HC$$ element and never <code>null</code>\r\n" +
                                         "   */\r\n" +
                                         "  @Nonnull\r\n" +
                                         "  public static HC$$ create (@Nullable final Iterable <? extends IHCNode> aChildren)\r\n" +
                                         "  {\r\n" +
                                         "    return new HC$$ ().addChildren (aChildren);\r\n" +
                                         "  }\r\n" +
                                         "}\r\n" +
                                         "";

  public static void main (final String [] args)
  {
    final String [] ELEMENTS = new String [] { "Abbr",
                                              "Acronym",
                                              "Address",
                                              "B",
                                              "BDO",
                                              "BlockQuote",
                                              "Center",
                                              "Div",
                                              "EM",
                                              "H1",
                                              "H2",
                                              "H3",
                                              "H4",
                                              "H5",
                                              "H6",
                                              "I",
                                              "Ins",
                                              "KBD",
                                              "LI",
                                              "Listing",
                                              "P",
                                              "Pre",
                                              "Q",
                                              "S",
                                              "Samp",
                                              "Span",
                                              "Strong",
                                              "Sub",
                                              "Sup",
                                              "XMP" };
    final File aBaseDir = new File ("src/main/java/com/phloc/html/hc/html");
    for (final String s : ELEMENTS)
    {
      final String sUC = s.toUpperCase (Locale.US);
      String sSuperClass = "AbstractHCElementWithChildren";
      String sSuperParam = "EHTMLElement." + sUC;
      if (s.equals ("Div"))
      {
        sSuperClass = "AbstractHCDiv";
        sSuperParam = "";
      }
      else
        if (s.equals ("Span"))
        {
          sSuperClass = "AbstractHCSpan";
          sSuperParam = "";
        }

      String sCode = TEMPLATE;
      sCode = sCode.replace ("$SUPERCLASS$", sSuperClass);
      sCode = sCode.replace ("$SUPERPARAM$", sSuperParam);
      sCode = sCode.replace ("$$$", sUC);
      sCode = sCode.replace ("$$", s);

      final File aFile = new File (aBaseDir, "HC" + s + ".java");
      if (!aFile.exists ())
        throw new IllegalStateException (aFile.getPath () + " does not exist");
      SimpleFileIO.writeFile (aFile, sCode, CCharset.CHARSET_UTF_8);
    }
    System.out.println ("Done");
  }
}
