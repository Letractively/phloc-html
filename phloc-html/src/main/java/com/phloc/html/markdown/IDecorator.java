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

import com.phloc.html.hc.html.HCA;
import com.phloc.html.hc.html.HCCode;
import com.phloc.html.hc.html.HCImg;
import com.phloc.html.hc.html.HCLI;
import com.phloc.html.hc.impl.AbstractHCElementWithChildren;

/**
 * Decorator interface.
 * 
 * @author René Jeschke <rene_jeschke@yahoo.de>
 */
public interface IDecorator
{
  /**
   * Called when a paragraph is opened.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;p>");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void openParagraph (HCStack out);

  /**
   * Called when a paragraph is closed.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;/p>\n");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void closeParagraph (HCStack out);

  /**
   * Called when a blockquote is opened. Default implementation is:
   * 
   * <pre>
   * <code>out.append("&lt;blockquote>");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void openBlockquote (HCStack out);

  /**
   * Called when a blockquote is closed.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;/blockquote>\n");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void closeBlockquote (HCStack out);

  /**
   * Called when a code block is opened.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;pre>&lt;code>");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void openCodeBlock (HCStack out);

  /**
   * Called when a code block is closed.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;/code>&lt;/pre>\n");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void closeCodeBlock (HCStack out);

  /**
   * Called when a code span is opened.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;code>");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  HCCode openCodeSpan (HCStack out);

  /**
   * Called when a code span is closed.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;/code>");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void closeCodeSpan (HCStack out);

  /**
   * Called when a headline is opened.
   * <p>
   * <strong>Note:</strong> Don't close the HTML tag!
   * </p>
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code> out.append("&lt;h");
   * out.append(level);</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  AbstractHCElementWithChildren <?> openHeadline (HCStack out, int level);

  /**
   * Called when a headline is closed.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code> out.append("&lt;/h");
   * out.append(level);
   * out.append(">\n");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void closeHeadline (HCStack out, int level);

  /**
   * Called when a strong span is opened.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;strong>");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void openStrong (HCStack out);

  /**
   * Called when a strong span is closed.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;/strong>");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void closeStrong (HCStack out);

  /**
   * Called when a strike span is opened.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;s>");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void openStrike (HCStack out);

  /**
   * Called when a strike span is closed.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;/s>");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void closeStrike (HCStack out);

  /**
   * Called when an emphasis span is opened.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;em>");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void openEmphasis (HCStack out);

  /**
   * Called when an emphasis span is closed.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;/em>");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void closeEmphasis (HCStack out);

  /**
   * Called when a superscript span is opened.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;sup>");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void openSuper (HCStack out);

  /**
   * Called when a superscript span is closed.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;/sup>");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void closeSuper (HCStack out);

  /**
   * Called when an ordered list is opened.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;ol>\n");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void openOrderedList (HCStack out);

  /**
   * Called when an ordered list is closed.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;/ol>\n");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void closeOrderedList (HCStack out);

  /**
   * Called when an unordered list is opened.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;ul>\n");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void openUnorderedList (HCStack out);

  /**
   * Called when an unordered list is closed.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;/ul>\n");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void closeUnorderedList (HCStack out);

  /**
   * Called when a list item is opened.
   * <p>
   * <strong>Note:</strong> Don't close the HTML tag!
   * </p>
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;li");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  HCLI openListItem (HCStack out);

  /**
   * Called when a list item is closed.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;/li>\n");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void closeListItem (HCStack out);

  /**
   * Called when a horizontal ruler is encountered.
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;hr />\n");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  void appendHorizontalRuler (HCStack out);

  /**
   * Called when a link is opened.
   * <p>
   * <strong>Note:</strong> Don't close the HTML tag!
   * </p>
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;a");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  HCA openLink (HCStack out);

  void closeLink (HCStack out);

  /**
   * Called when an image is opened.
   * <p>
   * <strong>Note:</strong> Don't close the HTML tag!
   * </p>
   * <p>
   * Default implementation is:
   * </p>
   * 
   * <pre>
   * <code>out.append("&lt;img");</code>
   * </pre>
   * 
   * @param out
   *        The StringBuilder to write to.
   */
  HCImg appendImage (HCStack out);
}
