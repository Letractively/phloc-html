/*
 * Copyright (C) 2011 René Jeschke <rene_jeschke@yahoo.de>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.phloc.html.markdown;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.phloc.commons.regex.RegExPool;
import com.phloc.html.markdown.ext.AbstractPlugin;

/**
 * Emitter class responsible for generating HTML output.
 * 
 * @author René Jeschke <rene_jeschke@yahoo.de>
 */
class Emitter
{
  /** Link references. */
  private final Map <String, LinkRef> m_aLinkRefs = new HashMap <String, LinkRef> ();
  /** The configuration. */
  private final Configuration m_aConfig;
  /** Extension flag. */
  public boolean m_bUseExtensions = false;
  /** Newline flag. */
  public boolean m_bConvertNewline2Br = false;
  /** Plugins references **/
  private final Map <String, AbstractPlugin> m_aPlugins = new HashMap <String, AbstractPlugin> ();

  /** Constructor. */
  public Emitter (final Configuration config)
  {
    m_aConfig = config;
    m_bUseExtensions = config.m_bForceExtendedProfile;
    m_bConvertNewline2Br = config.m_bConvertNewline2Br;
    for (final AbstractPlugin plugin : config.m_aPlugins)
    {
      register (plugin);
    }
  }

  public void register (final AbstractPlugin plugin)
  {
    m_aPlugins.put (plugin.getIdPlugin (), plugin);
  }

  /**
   * Adds a LinkRef to this set of LinkRefs.
   * 
   * @param key
   *        The key/id.
   * @param linkRef
   *        The LinkRef.
   */
  public void addLinkRef (final String key, final LinkRef linkRef)
  {
    m_aLinkRefs.put (key.toLowerCase (), linkRef);
  }

  /**
   * Transforms the given block recursively into HTML.
   * 
   * @param out
   *        The StringBuilder to write to.
   * @param root
   *        The Block to process.
   */
  public void emit (final StringBuilder out, final Block root)
  {
    root.removeSurroundingEmptyLines ();

    switch (root.m_eType)
    {
      case RULER:
        m_aConfig.m_aDecorator.horizontalRuler (out);
        return;
      case NONE:
      case XML:
        break;
      case HEADLINE:
        m_aConfig.m_aDecorator.openHeadline (out, root.m_nHlDepth);
        if (m_bUseExtensions && root.m_sId != null)
        {
          out.append (" id=\"");
          Utils.appendCode (out, root.m_sId, 0, root.m_sId.length ());
          out.append ('"');
        }
        out.append ('>');
        break;
      case PARAGRAPH:
        m_aConfig.m_aDecorator.openParagraph (out);
        break;
      case CODE:
      case FENCED_CODE:
        if (m_aConfig.m_aCodeBlockEmitter == null)
          m_aConfig.m_aDecorator.openCodeBlock (out);
        break;
      case BLOCKQUOTE:
        m_aConfig.m_aDecorator.openBlockquote (out);
        break;
      case UNORDERED_LIST:
        m_aConfig.m_aDecorator.openUnorderedList (out);
        break;
      case ORDERED_LIST:
        m_aConfig.m_aDecorator.openOrderedList (out);
        break;
      case LIST_ITEM:
        m_aConfig.m_aDecorator.openListItem (out);
        if (m_bUseExtensions && root.m_sId != null)
        {
          out.append (" id=\"");
          Utils.appendCode (out, root.m_sId, 0, root.m_sId.length ());
          out.append ('"');
        }
        out.append ('>');
        break;
      default:
        break;
    }

    if (root.hasLines ())
    {
      _emitLines (out, root);
    }
    else
    {
      Block block = root.m_aBlocks;
      while (block != null)
      {
        emit (out, block);
        block = block.m_aNext;
      }
    }

    switch (root.m_eType)
    {
      case RULER:
      case NONE:
      case XML:
        break;
      case HEADLINE:
        m_aConfig.m_aDecorator.closeHeadline (out, root.m_nHlDepth);
        break;
      case PARAGRAPH:
        m_aConfig.m_aDecorator.closeParagraph (out);
        break;
      case CODE:
      case FENCED_CODE:
        if (m_aConfig.m_aCodeBlockEmitter == null)
          m_aConfig.m_aDecorator.closeCodeBlock (out);
        break;
      case BLOCKQUOTE:
        m_aConfig.m_aDecorator.closeBlockquote (out);
        break;
      case UNORDERED_LIST:
        m_aConfig.m_aDecorator.closeUnorderedList (out);
        break;
      case ORDERED_LIST:
        m_aConfig.m_aDecorator.closeOrderedList (out);
        break;
      case LIST_ITEM:
        m_aConfig.m_aDecorator.closeListItem (out);
        break;
      default:
        break;
    }
  }

  /**
   * Transforms lines into HTML.
   * 
   * @param out
   *        The StringBuilder to write to.
   * @param block
   *        The Block to process.
   */
  private void _emitLines (final StringBuilder out, final Block block)
  {
    switch (block.m_eType)
    {
      case CODE:
        _emitCodeLines (out, block.m_aLines, block.m_sMeta, true);
        break;
      case FENCED_CODE:
        _emitCodeLines (out, block.m_aLines, block.m_sMeta, false);
        break;
      case PLUGIN:
        emitPluginLines (out, block.m_aLines, block.m_sMeta);
        break;
      case XML:
        _emitRawLines (out, block.m_aLines);
        break;
      case PARAGRAPH:
        _emitMarkedLines (out, block.m_aLines);
        break;
      default:
        _emitMarkedLines (out, block.m_aLines);
        break;
    }
  }

  /**
   * Finds the position of the given Token in the given String.
   * 
   * @param in
   *        The String to search on.
   * @param start
   *        The starting character position.
   * @param token
   *        The token to find.
   * @return The position of the token or -1 if none could be found.
   */
  private int _findToken (final String in, final int start, final EMarkToken token)
  {
    int pos = start;
    while (pos < in.length ())
    {
      if (_getToken (in, pos) == token)
        return pos;
      pos++;
    }
    return -1;
  }

  /**
   * Checks if there is a valid markdown link definition.
   * 
   * @param out
   *        The StringBuilder containing the generated output.
   * @param in
   *        Input String.
   * @param start
   *        Starting position.
   * @param token
   *        Either LINK or IMAGE.
   * @return The new position or -1 if there is no valid markdown link.
   */
  private int _checkLink (final StringBuilder out, final String in, final int start, final EMarkToken token)
  {
    boolean isAbbrev = false;
    int pos = start + (token == EMarkToken.LINK ? 1 : 2);
    final StringBuilder temp = new StringBuilder ();

    temp.setLength (0);
    pos = Utils.readMdLinkId (temp, in, pos);
    if (pos < start)
      return -1;

    final String name = temp.toString ();
    String link = null, comment = null;
    final int oldPos = pos++;
    pos = Utils.skipSpaces (in, pos);
    if (pos < start)
    {
      final LinkRef lr = m_aLinkRefs.get (name.toLowerCase ());
      if (lr != null)
      {
        isAbbrev = lr.m_bIsAbbrev;
        link = lr.m_sLink;
        comment = lr.m_sTitle;
        pos = oldPos;
      }
      else
      {
        return -1;
      }
    }
    else
      if (in.charAt (pos) == '(')
      {
        pos++;
        pos = Utils.skipSpaces (in, pos);
        if (pos < start)
          return -1;
        temp.setLength (0);
        final boolean useLt = in.charAt (pos) == '<';
        pos = useLt ? Utils.readUntil (temp, in, pos + 1, '>') : Utils.readMdLink (temp, in, pos);
        if (pos < start)
          return -1;
        if (useLt)
          pos++;
        link = temp.toString ();

        if (in.charAt (pos) == ' ')
        {
          pos = Utils.skipSpaces (in, pos);
          if (pos > start && in.charAt (pos) == '"')
          {
            pos++;
            temp.setLength (0);
            pos = Utils.readUntil (temp, in, pos, '"');
            if (pos < start)
              return -1;
            comment = temp.toString ();
            pos++;
            pos = Utils.skipSpaces (in, pos);
            if (pos == -1)
              return -1;
          }
        }
        if (in.charAt (pos) != ')')
          return -1;
      }
      else
        if (in.charAt (pos) == '[')
        {
          pos++;
          temp.setLength (0);
          pos = Utils.readRawUntil (temp, in, pos, ']');
          if (pos < start)
            return -1;
          final String id = temp.length () > 0 ? temp.toString () : name;
          final LinkRef lr = m_aLinkRefs.get (id.toLowerCase ());
          if (lr != null)
          {
            link = lr.m_sLink;
            comment = lr.m_sTitle;
          }
        }
        else
        {
          final LinkRef lr = m_aLinkRefs.get (name.toLowerCase ());
          if (lr != null)
          {
            isAbbrev = lr.m_bIsAbbrev;
            link = lr.m_sLink;
            comment = lr.m_sTitle;
            pos = oldPos;
          }
          else
          {
            return -1;
          }
        }

    if (link == null)
      return -1;

    if (token == EMarkToken.LINK)
    {
      if (isAbbrev && comment != null)
      {
        if (!m_bUseExtensions)
          return -1;
        out.append ("<abbr title=\"");
        Utils.appendValue (out, comment);
        out.append ("\">");
        _recursiveEmitLine (out, name, 0, EMarkToken.NONE);
        out.append ("</abbr>");
      }
      else
      {
        m_aConfig.m_aDecorator.openLink (out);
        out.append (" href=\"");
        Utils.appendValue (out, link);
        out.append ('"');
        if (comment != null)
        {
          out.append (" title=\"");
          Utils.appendValue (out, comment);
          out.append ('"');
        }
        out.append ('>');
        _recursiveEmitLine (out, name, 0, EMarkToken.NONE);
        out.append ("</a>");
      }
    }
    else
    {
      m_aConfig.m_aDecorator.openImage (out);
      out.append (" src=\"");
      Utils.appendValue (out, link);
      out.append ("\" alt=\"");
      Utils.appendValue (out, name);
      out.append ('"');
      if (comment != null)
      {
        out.append (" title=\"");
        Utils.appendValue (out, comment);
        out.append ('"');
      }
      out.append (" />");
    }

    return pos;
  }

  /**
   * Check if there is a valid HTML tag here. This method also transforms auto
   * links and mailto auto links.
   * 
   * @param out
   *        The StringBuilder to write to.
   * @param in
   *        Input String.
   * @param start
   *        Starting position.
   * @return The new position or -1 if nothing valid has been found.
   */
  private int _checkHtml (final StringBuilder out, final String in, final int start)
  {
    final StringBuilder temp = new StringBuilder ();
    int pos;

    // Check for auto links
    temp.setLength (0);
    pos = Utils.readUntil (temp, in, start + 1, ':', ' ', '>', '\n');
    if (pos != -1 && in.charAt (pos) == ':' && HTML.isLinkPrefix (temp.toString ()))
    {
      pos = Utils.readUntil (temp, in, pos, '>');
      if (pos != -1)
      {
        final String link = temp.toString ();
        m_aConfig.m_aDecorator.openLink (out);
        out.append (" href=\"");
        Utils.appendValue (out, link);
        out.append ("\">");
        Utils.appendValue (out, link);
        out.append ("</a>");
        return pos;
      }
    }

    // Check for mailto or adress auto link
    temp.setLength (0);
    pos = Utils.readUntil (temp, in, start + 1, '@', ' ', '>', '\n');
    if (pos != -1 && in.charAt (pos) == '@')
    {
      pos = Utils.readUntil (temp, in, pos, '>');
      if (pos != -1)
      {
        final String link = temp.toString ();
        m_aConfig.m_aDecorator.openLink (out);
        out.append (" href=\"");

        // address auto links
        if (link.startsWith ("@"))
        {
          final String slink = link.substring (1);
          final String url = "https://maps.google.com/maps?q=" + slink.replace (' ', '+');
          out.append (url);
          out.append ("\">");
          out.append (slink);
        }
        // mailto auto links
        else
        {
          Utils.appendMailto (out, "mailto:", 0, 7);
          Utils.appendMailto (out, link, 0, link.length ());
          out.append ("\">");
          Utils.appendMailto (out, link, 0, link.length ());
        }
        out.append ("</a>");
        return pos;
      }
    }

    // Check for inline html
    if (start + 2 < in.length ())
    {
      temp.setLength (0);
      return Utils.readXML (out, in, start, m_aConfig.m_bSafeMode);
    }

    return -1;
  }

  /**
   * Check if this is a valid XML/HTML entity.
   * 
   * @param out
   *        The StringBuilder to write to.
   * @param in
   *        Input String.
   * @param start
   *        Starting position
   * @return The new position or -1 if this entity in invalid.
   */
  private static int _checkEntity (final StringBuilder out, final String in, final int start)
  {
    final int pos = Utils.readUntil (out, in, start, ';');
    if (pos < 0 || out.length () < 3)
      return -1;
    if (out.charAt (1) == '#')
    {
      if (out.charAt (2) == 'x' || out.charAt (2) == 'X')
      {
        if (out.length () < 4)
          return -1;
        for (int i = 3; i < out.length (); i++)
        {
          final char c = out.charAt (i);
          if ((c < '0' || c > '9') && ((c < 'a' || c > 'f') && (c < 'A' || c > 'F')))
            return -1;
        }
      }
      else
      {
        for (int i = 2; i < out.length (); i++)
        {
          final char c = out.charAt (i);
          if (c < '0' || c > '9')
            return -1;
        }
      }
      out.append (';');
    }
    else
    {
      for (int i = 1; i < out.length (); i++)
      {
        final char c = out.charAt (i);
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z'))
          return -1;
      }
      out.append (';');
      return HTML.isEntity (out.toString ()) ? pos : -1;
    }

    return pos;
  }

  /**
   * Recursively scans through the given line, taking care of any markdown
   * stuff.
   * 
   * @param out
   *        The StringBuilder to write to.
   * @param in
   *        Input String.
   * @param start
   *        Start position.
   * @param token
   *        The matching Token (for e.g. '*')
   * @return The position of the matching Token or -1 if token was NONE or no
   *         Token could be found.
   */
  private int _recursiveEmitLine (final StringBuilder out, final String in, final int start, final EMarkToken token)
  {
    int pos = start, a, b;
    final StringBuilder temp = new StringBuilder ();
    while (pos < in.length ())
    {
      final EMarkToken mt = _getToken (in, pos);
      if (token != EMarkToken.NONE)
        if (mt == token ||
            (token == EMarkToken.EM_STAR && mt == EMarkToken.STRONG_STAR) ||
            (token == EMarkToken.EM_UNDERSCORE && mt == EMarkToken.STRONG_UNDERSCORE))
          return pos;

      switch (mt)
      {
        case IMAGE:
        case LINK:
          temp.setLength (0);
          b = _checkLink (temp, in, pos, mt);
          if (b > 0)
          {
            out.append (temp);
            pos = b;
          }
          else
          {
            out.append (in.charAt (pos));
          }
          break;
        case EM_STAR:
        case EM_UNDERSCORE:
          temp.setLength (0);
          b = _recursiveEmitLine (temp, in, pos + 1, mt);
          if (b > 0)
          {
            m_aConfig.m_aDecorator.openEmphasis (out);
            out.append (temp);
            m_aConfig.m_aDecorator.closeEmphasis (out);
            pos = b;
          }
          else
          {
            out.append (in.charAt (pos));
          }
          break;
        case STRONG_STAR:
        case STRONG_UNDERSCORE:
          temp.setLength (0);
          b = _recursiveEmitLine (temp, in, pos + 2, mt);
          if (b > 0)
          {
            m_aConfig.m_aDecorator.openStrong (out);
            out.append (temp);
            m_aConfig.m_aDecorator.closeStrong (out);
            pos = b + 1;
          }
          else
          {
            out.append (in.charAt (pos));
          }
          break;
        case STRIKE:
          temp.setLength (0);
          b = _recursiveEmitLine (temp, in, pos + 2, mt);
          if (b > 0)
          {
            m_aConfig.m_aDecorator.openStrike (out);
            out.append (temp);
            m_aConfig.m_aDecorator.closeStrike (out);
            pos = b + 1;
          }
          else
          {
            out.append (in.charAt (pos));
          }
          break;
        case SUPER:
          temp.setLength (0);
          b = _recursiveEmitLine (temp, in, pos + 1, mt);
          if (b > 0)
          {
            m_aConfig.m_aDecorator.openSuper (out);
            out.append (temp);
            m_aConfig.m_aDecorator.closeSuper (out);
            pos = b;
          }
          else
          {
            out.append (in.charAt (pos));
          }
          break;
        case CODE_SINGLE:
        case CODE_DOUBLE:
          a = pos + (mt == EMarkToken.CODE_DOUBLE ? 2 : 1);
          b = _findToken (in, a, mt);
          if (b > 0)
          {
            pos = b + (mt == EMarkToken.CODE_DOUBLE ? 1 : 0);
            while (a < b && in.charAt (a) == ' ')
              a++;
            if (a < b)
            {
              while (in.charAt (b - 1) == ' ')
                b--;
              m_aConfig.m_aDecorator.openCodeSpan (out);
              Utils.appendCode (out, in, a, b);
              m_aConfig.m_aDecorator.closeCodeSpan (out);
            }
          }
          else
          {
            out.append (in.charAt (pos));
          }
          break;
        case HTML:
          temp.setLength (0);
          b = _checkHtml (temp, in, pos);
          if (b > 0)
          {
            out.append (temp);
            pos = b;
          }
          else
          {
            out.append ("&lt;");
          }
          break;
        case ENTITY:
          temp.setLength (0);
          b = _checkEntity (temp, in, pos);
          if (b > 0)
          {
            out.append (temp);
            pos = b;
          }
          else
          {
            out.append ("&amp;");
          }
          break;
        case X_LINK_OPEN:
          temp.setLength (0);
          b = _recursiveEmitLine (temp, in, pos + 2, EMarkToken.X_LINK_CLOSE);
          if (b > 0 && m_aConfig.m_aSpecialLinkEmitter != null)
          {
            m_aConfig.m_aSpecialLinkEmitter.emitSpan (out, temp.toString ());
            pos = b + 1;
          }
          else
          {
            out.append (in.charAt (pos));
          }
          break;
        case X_COPY:
          out.append ("&copy;");
          pos += 2;
          break;
        case X_REG:
          out.append ("&reg;");
          pos += 2;
          break;
        case X_TRADE:
          out.append ("&trade;");
          pos += 3;
          break;
        case X_NDASH:
          out.append ("&ndash;");
          pos++;
          break;
        case X_MDASH:
          out.append ("&mdash;");
          pos += 2;
          break;
        case X_HELLIP:
          out.append ("&hellip;");
          pos += 2;
          break;
        case X_LAQUO:
          out.append ("&laquo;");
          pos++;
          break;
        case X_RAQUO:
          out.append ("&raquo;");
          pos++;
          break;
        case X_RDQUO:
          out.append ("&rdquo;");
          break;
        case X_LDQUO:
          out.append ("&ldquo;");
          break;
        case ESCAPE:
          pos++;
          out.append (in.charAt (pos));
          break;
        default:
          out.append (in.charAt (pos));
          break;
      }
      pos++;
    }
    return -1;
  }

  /**
   * Turns every whitespace character into a space character.
   * 
   * @param c
   *        Character to check
   * @return 32 is c was a whitespace, c otherwise
   */
  private static char _whitespaceToSpace (final char c)
  {
    return Character.isWhitespace (c) ? ' ' : c;
  }

  /**
   * Check if there is any markdown Token.
   * 
   * @param in
   *        Input String.
   * @param pos
   *        Starting position.
   * @return The Token.
   */
  private EMarkToken _getToken (final String in, final int pos)
  {
    final char c0 = pos > 0 ? _whitespaceToSpace (in.charAt (pos - 1)) : ' ';
    final char c = _whitespaceToSpace (in.charAt (pos));
    final char c1 = pos + 1 < in.length () ? _whitespaceToSpace (in.charAt (pos + 1)) : ' ';
    final char c2 = pos + 2 < in.length () ? _whitespaceToSpace (in.charAt (pos + 2)) : ' ';
    final char c3 = pos + 3 < in.length () ? _whitespaceToSpace (in.charAt (pos + 3)) : ' ';

    switch (c)
    {
      case '*':
        if (c1 == '*')
        {
          return c0 != ' ' || c2 != ' ' ? EMarkToken.STRONG_STAR : EMarkToken.EM_STAR;
        }
        return c0 != ' ' || c1 != ' ' ? EMarkToken.EM_STAR : EMarkToken.NONE;
      case '_':
        if (c1 == '_')
        {
          return c0 != ' ' || c2 != ' ' ? EMarkToken.STRONG_UNDERSCORE : EMarkToken.EM_UNDERSCORE;
        }
        if (m_bUseExtensions)
        {
          return Character.isLetterOrDigit (c0) && c0 != '_' && Character.isLetterOrDigit (c1) ? EMarkToken.NONE
                                                                                              : EMarkToken.EM_UNDERSCORE;
        }
        return c0 != ' ' || c1 != ' ' ? EMarkToken.EM_UNDERSCORE : EMarkToken.NONE;
      case '~':
        if (m_bUseExtensions && c1 == '~')
        {
          return EMarkToken.STRIKE;
        }
        return EMarkToken.NONE;
      case '!':
        if (c1 == '[')
          return EMarkToken.IMAGE;
        return EMarkToken.NONE;
      case '[':
        if (m_bUseExtensions && c1 == '[')
          return EMarkToken.X_LINK_OPEN;
        return EMarkToken.LINK;
      case ']':
        if (m_bUseExtensions && c1 == ']')
          return EMarkToken.X_LINK_CLOSE;
        return EMarkToken.NONE;
      case '`':
        return c1 == '`' ? EMarkToken.CODE_DOUBLE : EMarkToken.CODE_SINGLE;
      case '\\':
        if (Utils.isEscapeChar (c1))
          return EMarkToken.ESCAPE;
        return EMarkToken.NONE;
      case '<':
        if (m_bUseExtensions && c1 == '<')
          return EMarkToken.X_LAQUO;
        return EMarkToken.HTML;
      case '&':
        return EMarkToken.ENTITY;
      default:
        if (m_bUseExtensions)
        {
          switch (c)
          {
            case '-':
              if (c1 == '-')
                return c2 == '-' ? EMarkToken.X_MDASH : EMarkToken.X_NDASH;
              break;
            case '^':
              return c0 == '^' || c1 == '^' ? EMarkToken.NONE : EMarkToken.SUPER;
            case '>':
              if (c1 == '>')
                return EMarkToken.X_RAQUO;
              break;
            case '.':
              if (c1 == '.' && c2 == '.')
                return EMarkToken.X_HELLIP;
              break;
            case '(':
              if (c1 == 'C' && c2 == ')')
                return EMarkToken.X_COPY;
              if (c1 == 'R' && c2 == ')')
                return EMarkToken.X_REG;
              if (c1 == 'T' & c2 == 'M' & c3 == ')')
                return EMarkToken.X_TRADE;
              break;
            case '"':
              if (!Character.isLetterOrDigit (c0) && c1 != ' ')
                return EMarkToken.X_LDQUO;
              if (c0 != ' ' && !Character.isLetterOrDigit (c1))
                return EMarkToken.X_RDQUO;
              break;
          }
        }
        return EMarkToken.NONE;
    }
  }

  /**
   * Writes a set of markdown lines into the StringBuilder.
   * 
   * @param out
   *        The StringBuilder to write to.
   * @param lines
   *        The lines to write.
   */
  private void _emitMarkedLines (final StringBuilder out, final Line lines)
  {
    final StringBuilder in = new StringBuilder ();
    Line line = lines;
    while (line != null)
    {
      if (!line.m_bIsEmpty)
      {
        in.append (line.m_sValue.substring (line.m_nLeading, line.m_sValue.length () - line.m_nTrailing));
        if (line.m_nTrailing >= 2 && !m_bConvertNewline2Br)
          in.append ("<br />");
      }
      if (line.m_aNext != null)
      {
        in.append ('\n');
        if (m_bConvertNewline2Br)
        {
          in.append ("<br />");
        }
      }
      line = line.m_aNext;
    }

    _recursiveEmitLine (out, in.toString (), 0, EMarkToken.NONE);
  }

  /**
   * Writes a set of raw lines into the StringBuilder.
   * 
   * @param out
   *        The StringBuilder to write to.
   * @param lines
   *        The lines to write.
   */
  private void _emitRawLines (final StringBuilder out, final Line lines)
  {
    Line line = lines;
    if (m_aConfig.m_bSafeMode)
    {
      final StringBuilder temp = new StringBuilder ();
      while (line != null)
      {
        if (!line.m_bIsEmpty)
        {
          temp.append (line.m_sValue);
        }
        temp.append ('\n');
        line = line.m_aNext;
      }
      final String in = temp.toString ();
      for (int pos = 0; pos < in.length (); pos++)
      {
        if (in.charAt (pos) == '<')
        {
          temp.setLength (0);
          final int t = Utils.readXML (temp, in, pos, m_aConfig.m_bSafeMode);
          if (t != -1)
          {
            out.append (temp);
            pos = t;
          }
          else
          {
            out.append (in.charAt (pos));
          }
        }
        else
        {
          out.append (in.charAt (pos));
        }
      }
    }
    else
    {
      while (line != null)
      {
        if (!line.m_bIsEmpty)
        {
          out.append (line.m_sValue);
        }
        out.append ('\n');
        line = line.m_aNext;
      }
    }
  }

  /**
   * Writes a code block into the StringBuilder.
   * 
   * @param out
   *        The StringBuilder to write to.
   * @param lines
   *        The lines to write.
   * @param meta
   *        Meta information.
   */
  private void _emitCodeLines (final StringBuilder out, final Line lines, final String meta, final boolean removeIndent)
  {
    Line line = lines;
    if (m_aConfig.m_aCodeBlockEmitter != null)
    {
      final List <String> list = new ArrayList <String> ();
      while (line != null)
      {
        if (line.m_bIsEmpty)
          list.add ("");
        else
          list.add (removeIndent ? line.m_sValue.substring (4) : line.m_sValue);
        line = line.m_aNext;
      }
      m_aConfig.m_aCodeBlockEmitter.emitBlock (out, list, meta);
    }
    else
    {
      while (line != null)
      {
        if (!line.m_bIsEmpty)
        {
          for (final char c : line.m_sValue.substring (4).toCharArray ())
          {
            switch (c)
            {
              case '&':
                out.append ("&amp;");
                break;
              case '<':
                out.append ("&lt;");
                break;
              case '>':
                out.append ("&gt;");
                break;
              default:
                out.append (c);
                break;
            }
          }
        }
        out.append ('\n');
        line = line.m_aNext;
      }
    }
  }

  /**
   * interprets a plugin block into the StringBuilder.
   * 
   * @param out
   *        The StringBuilder to write to.
   * @param lines
   *        The lines to write.
   * @param meta
   *        Meta information.
   */
  protected void emitPluginLines (final StringBuilder out, final Line lines, final String meta)
  {
    Line line = lines;

    String idPlugin = meta;
    String sparams = null;
    Map <String, String> params = null;
    final int iow = meta.indexOf (' ');
    if (iow != -1)
    {
      idPlugin = meta.substring (0, iow);
      sparams = meta.substring (iow + 1);
      if (sparams != null)
      {
        params = parsePluginParams (sparams);
      }
    }

    if (params == null)
    {
      params = new HashMap <String, String> ();
    }
    final ArrayList <String> list = new ArrayList <String> ();
    while (line != null)
    {
      if (line.m_bIsEmpty)
        list.add ("");
      else
        list.add (line.m_sValue);
      line = line.m_aNext;
    }

    final AbstractPlugin plugin = m_aPlugins.get (idPlugin);
    if (plugin != null)
    {
      plugin.emit (out, list, params);
    }
  }

  protected Map <String, String> parsePluginParams (final String s)
  {
    final Map <String, String> params = new HashMap <String, String> ();
    final Pattern p = RegExPool.getPattern ("(\\w+)=\"*((?<=\")[^\"]+(?=\")|([^\\s]+))\"*");

    final Matcher m = p.matcher (s);
    while (m.find ())
    {
      params.put (m.group (1), m.group (2));
    }

    return params;
  }

}