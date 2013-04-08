/**
 * Copyright (C) 2006-2013 phloc systems
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
package com.phloc.html.hc.utils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phloc.commons.annotations.PresentForCodeCoverage;
import com.phloc.commons.annotations.ReturnsMutableCopy;
import com.phloc.html.hc.IHCHasChildren;
import com.phloc.html.hc.IHCNode;
import com.phloc.html.hc.api.IHCCSSNode;
import com.phloc.html.hc.api.IHCJSNode;
import com.phloc.html.hc.html.HCLink;
import com.phloc.html.hc.html.HCScript;
import com.phloc.html.hc.html.HCScriptFile;
import com.phloc.html.hc.html.HCScriptOnDocumentReady;
import com.phloc.html.hc.html.HCStyle;
import com.phloc.html.hc.htmlext.HCUtils;
import com.phloc.html.hc.impl.HCNodeList;
import com.phloc.html.js.builder.jquery.JQuery;
import com.phloc.html.js.provider.CollectingJSCodeProvider;

/**
 * This class is used to handle the special nodes (JS and CSS, inline and
 * reference).
 * 
 * @author philip
 */
@NotThreadSafe
public final class HCSpecialNodeHandler
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (HCSpecialNodeHandler.class);

  @PresentForCodeCoverage
  @SuppressWarnings ("unused")
  private static final HCSpecialNodeHandler s_aInstance = new HCSpecialNodeHandler ();

  private HCSpecialNodeHandler ()
  {}

  /**
   * Check if the passed node is a CSS node after unwrapping.
   * 
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link IHCCSSNode} (and
   *         not a special case).
   */
  public static boolean isCSSNode (@Nullable final IHCNode aNode)
  {
    final IHCNode aUnwrappedNode = HCUtils.getUnwrappedNode (aNode);
    return isDirectCSSNode (aUnwrappedNode);
  }

  /**
   * Check if the passed node is a CSS node.
   * 
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link IHCCSSNode} (and
   *         not a special case).
   */
  public static boolean isDirectCSSNode (@Nullable final IHCNode aNode)
  {
    // Direct CSS node?
    if (aNode instanceof IHCCSSNode)
    {
      // Special case
      if (aNode instanceof HCLink && !((HCLink) aNode).isCSSLink ())
        return false;
      return true;
    }

    return false;
  }

  /**
   * Check if the passed node is an inline CSS node after unwrapping.
   * 
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCStyle}.
   */
  public static boolean isCSSInlineNode (@Nullable final IHCNode aNode)
  {
    final IHCNode aUnwrappedNode = HCUtils.getUnwrappedNode (aNode);
    return isDirectCSSInlineNode (aUnwrappedNode);
  }

  /**
   * Check if the passed node is an inline CSS node.
   * 
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCStyle}.
   */
  public static boolean isDirectCSSInlineNode (@Nullable final IHCNode aNode)
  {
    // Inline CSS node?
    return aNode instanceof HCStyle;
  }

  /**
   * Check if the passed node is a file CSS node after unwrapping.
   * 
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCStyle}.
   */
  public static boolean isCSSFileNode (@Nullable final IHCNode aNode)
  {
    final IHCNode aUnwrappedNode = HCUtils.getUnwrappedNode (aNode);
    return isDirectCSSFileNode (aUnwrappedNode);
  }

  /**
   * Check if the passed node is a file CSS node.
   * 
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCStyle}.
   */
  public static boolean isDirectCSSFileNode (@Nullable final IHCNode aNode)
  {
    // File CSS node?
    return aNode instanceof HCLink && ((HCLink) aNode).isCSSLink ();
  }

  /**
   * Check if the passed node is a JS node after unwrapping.
   * 
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link IHCJSNode}.
   */
  public static boolean isJSNode (@Nullable final IHCNode aNode)
  {
    final IHCNode aUnwrappedNode = HCUtils.getUnwrappedNode (aNode);
    return isDirectJSNode (aUnwrappedNode);
  }

  /**
   * Check if the passed node is a JS node.
   * 
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link IHCJSNode}.
   */
  public static boolean isDirectJSNode (@Nullable final IHCNode aNode)
  {
    // Direct JS node?
    return aNode instanceof IHCJSNode;
  }

  /**
   * Check if the passed node is an inline JS node after unwrapping.
   * 
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCScript}.
   */
  public static boolean isJSInlineNode (@Nullable final IHCNode aNode)
  {
    final IHCNode aUnwrappedNode = HCUtils.getUnwrappedNode (aNode);
    return isDirectJSInlineNode (aUnwrappedNode);
  }

  /**
   * Check if the passed node is an inline JS node.
   * 
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCScript}.
   */
  public static boolean isDirectJSInlineNode (@Nullable final IHCNode aNode)
  {
    // Inline JS node?
    return aNode instanceof HCScript;
  }

  /**
   * Check if the passed node is a file JS node after unwrapping.
   * 
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCScriptFile}.
   */
  public static boolean isJSFileNode (@Nullable final IHCNode aNode)
  {
    final IHCNode aUnwrappedNode = HCUtils.getUnwrappedNode (aNode);
    return isDirectJSFileNode (aUnwrappedNode);
  }

  /**
   * Check if the passed node is a file JS node.
   * 
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCScriptFile}.
   */
  public static boolean isDirectJSFileNode (@Nullable final IHCNode aNode)
  {
    // File JS node?
    return aNode instanceof HCScriptFile;
  }

  /**
   * Merge all inline CSS and JS elements contained in the source nodes into one
   * script elements
   * 
   * @param aNodes
   *        Source list of nodes. May not be <code>null</code>.
   * @param bKeepOnDocumentReady
   *        if <code>true</code> than all combined document.ready() scripts are
   *        kept as document.ready() scripts. If <code>false</code> than all
   *        document.ready() scripts are converted to regular scripts and are
   *        executed after all other scripts. For AJAX calls, this should be
   *        <code>false</code>.
   * @return Target list. It contains all non-script nodes and at last one JS
   *         inline node (HCScript).
   */
  @Nonnull
  @ReturnsMutableCopy
  public static List <IHCNode> getMergedInlineCSSAndJSNodes (@Nonnull final List <IHCNode> aNodes,
                                                             final boolean bKeepOnDocumentReady)
  {
    if (aNodes == null)
      throw new NullPointerException ("nodes");

    final List <IHCNode> ret = new ArrayList <IHCNode> ();

    final StringBuilder aInlineCSS = new StringBuilder ();
    final CollectingJSCodeProvider aOnDocumentReadyJS = new CollectingJSCodeProvider ();
    final CollectingJSCodeProvider aInlineJS = new CollectingJSCodeProvider ();
    for (final IHCNode aNode : aNodes)
    {
      // Note: do not unwrap the node, because it is not allowed to merge JS/CSS
      // with a conditional comment with JS/CSS without a conditional comment!

      // Check HCScriptOnDocumentReady first, because it is a subclass of
      // HCScript
      if (aNode instanceof HCScriptOnDocumentReady)
      {
        aOnDocumentReadyJS.appendFlattened (((HCScriptOnDocumentReady) aNode).getOnDocumentReadyCode ());
      }
      else
        if (aNode instanceof HCScript)
        {
          aInlineJS.appendFlattened (((HCScript) aNode).getJSCodeProvider ());
        }
        else
          if (aNode instanceof HCStyle && ((HCStyle) aNode).hasNoMediaOrAll ())
          {
            // Merge only inline CSS nodes, that are media-independent
            aInlineCSS.append (((HCStyle) aNode).getStyleContent ());
          }
          else
            ret.add (aNode);
    }

    // Add all inline CSS
    if (aInlineCSS.length () > 0)
      ret.add (new HCStyle (aInlineCSS.toString ()));

    // on document ready always as last inline JS!
    if (!aOnDocumentReadyJS.isEmpty ())
      if (bKeepOnDocumentReady)
        aInlineJS.append (JQuery.onDocumentReady (aOnDocumentReadyJS));
      else
        aInlineJS.append (aOnDocumentReadyJS);

    // Finally add the inline JS
    if (!aInlineJS.isEmpty ())
      ret.add (new HCScript (aInlineJS));

    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static List <IHCNode> getWithoutSpecialNodes (@Nonnull final List <IHCNode> aNodes,
                                                       @Nonnull final HCSpecialNodes aSpecialNodes)
  {
    if (aNodes == null)
      throw new NullPointerException ("nodes");
    if (aSpecialNodes == null)
      throw new NullPointerException ("specialNodes");

    final List <IHCNode> ret = new ArrayList <IHCNode> ();

    for (final IHCNode aNode : aNodes)
    {
      if (isDirectCSSFileNode (aNode))
      {
        aSpecialNodes.addExternalCSS (((HCLink) aNode).getHrefString ());
      }
      else
        if (isDirectJSFileNode (aNode))
        {
          aSpecialNodes.addExternalJS (((HCScriptFile) aNode).getSrcString ());
        }
        else
          if (isDirectJSInlineNode (aNode))
          {
            aSpecialNodes.addInlineJS (((HCScript) aNode).getJSCodeProvider ());
          }
          else
            ret.add (aNode);
    }

    return ret;
  }

  /**
   * Extract all out-of-band nodes of the source node, merge JS and CSS and
   * finally extract all special nodes into the passed object.
   * 
   * @param aNode
   *        Source node. May not be <code>null</code>.
   * @param aSpecialNodes
   *        Target special node object to be filled. May not be
   *        <code>null</code>.
   * @param bKeepOnDocumentReady
   *        if <code>true</code> than all combined document.ready() scripts are
   *        kept as document.ready() scripts. If <code>false</code> than all
   *        document.ready() scripts are converted to regular scripts and are
   *        executed after all other scripts. For AJAX calls, this should be
   *        <code>false</code>.
   * @return A node list with all remaining nodes. Never <code>null</code>.
   */
  @Nonnull
  public static HCNodeList extractSpecialContent (@Nonnull final IHCHasChildren aNode,
                                                  @Nonnull final HCSpecialNodes aSpecialNodes,
                                                  final boolean bKeepOnDocumentReady)
  {
    if (aNode == null)
      throw new NullPointerException ("Node");
    if (aSpecialNodes == null)
      throw new NullPointerException ("SpecialNodes");

    // Handle out of band nodes
    List <IHCNode> aExtractedOutOfBandNodes = HCOutOfBandHandler.recursiveExtractOutOfBandNodes (aNode);

    // Merge JS/CSS nodes
    aExtractedOutOfBandNodes = getMergedInlineCSSAndJSNodes (aExtractedOutOfBandNodes, bKeepOnDocumentReady);

    // Extract the special nodes
    aExtractedOutOfBandNodes = getWithoutSpecialNodes (aExtractedOutOfBandNodes, aSpecialNodes);
    if (!aExtractedOutOfBandNodes.isEmpty ())
      s_aLogger.warn ("Out-of-band nodes are left after merging and extraction: " + aExtractedOutOfBandNodes);

    // Add the content without the out-of-band nodes
    final HCNodeList ret = HCNodeList.create (aNode);
    // And to be sure, add all remaining out-of-band nodes at the end
    ret.addChildren (aExtractedOutOfBandNodes);
    return ret;
  }
}