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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.CheckForSigned;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.NotThreadSafe;

import com.phloc.commons.CGlobal;
import com.phloc.commons.ValueEnforcer;
import com.phloc.commons.annotations.DevelopersNote;
import com.phloc.commons.annotations.OverrideOnDemand;
import com.phloc.commons.annotations.ReturnsMutableCopy;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.commons.text.IPredefinedLocaleTextProvider;
import com.phloc.html.EHTMLElement;
import com.phloc.html.hc.IHCElementWithChildren;
import com.phloc.html.hc.IHCNode;
import com.phloc.html.hc.IHCNodeBuilder;
import com.phloc.html.hc.conversion.IHCConversionSettingsToNode;

@NotThreadSafe
public abstract class AbstractHCElementWithChildren <THISTYPE extends AbstractHCElementWithChildren <THISTYPE>> extends AbstractHCElement <THISTYPE> implements IHCElementWithChildren <THISTYPE>
{
  private List <IHCNode> m_aChildren;

  protected AbstractHCElementWithChildren (@Nonnull final EHTMLElement eElement)
  {
    super (eElement);
  }

  public final boolean hasChildren ()
  {
    return ContainerHelper.isNotEmpty (m_aChildren);
  }

  /**
   * Callback
   *
   * @param aChild
   *        The child that was added
   */
  @OverrideOnDemand
  protected void beforeAddChild (@Nonnull final IHCNode aChild)
  {}

  /**
   * Callback
   *
   * @param aChild
   *        The child that was added
   */
  @OverrideOnDemand
  protected void afterAddChild (@Nonnull final IHCNode aChild)
  {}

  @Nonnull
  private void _addChild (@CheckForSigned final int nIndex, @Nullable final IHCNode aChild)
  {
    if (aChild == this)
      throw new IllegalArgumentException ("Cannot append to self!");

    if (aChild != null)
    {
      beforeAddChild (aChild);
      if (m_aChildren == null)
        m_aChildren = new ArrayList <IHCNode> ();
      if (nIndex < 0)
        m_aChildren.add (aChild);
      else
        m_aChildren.add (nIndex, aChild);
      afterAddChild (aChild);
    }
  }

  @Nonnull
  public final THISTYPE addChild (@Nullable final IPredefinedLocaleTextProvider aTextProvider)
  {
    if (aTextProvider != null)
      return addChild (aTextProvider.getText ());
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE addChild (@Nullable final String sText)
  {
    // Empty text is OK!!!
    if (sText != null)
      addChild (new HCTextNode (sText));
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE addChild (@Nullable final IHCNodeBuilder aNodeBuilder)
  {
    if (aNodeBuilder != null)
      addChild (aNodeBuilder.build ());
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE addChild (@Nullable final IHCNode aChild)
  {
    _addChild (CGlobal.ILLEGAL_UINT, aChild);
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE addChild (@Nonnegative final int nIndex,
                                  @Nullable final IPredefinedLocaleTextProvider aTextProvider)
  {
    if (aTextProvider != null)
      return addChild (nIndex, aTextProvider.getText ());
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE addChild (@Nonnegative final int nIndex, @Nullable final String sText)
  {
    // Empty text is OK!!!
    if (sText != null)
      addChild (nIndex, new HCTextNode (sText));
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE addChild (@Nonnegative final int nIndex, @Nullable final IHCNodeBuilder aNodeBuilder)
  {
    if (aNodeBuilder != null)
      addChild (nIndex, aNodeBuilder.build ());
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE addChild (@Nonnegative final int nIndex, @Nullable final IHCNode aChild)
  {
    ValueEnforcer.isBetweenInclusive (nIndex, "Index", 0, getChildCount ());
    _addChild (nIndex, aChild);
    return thisAsT ();
  }

  @Nonnull
  @DevelopersNote ("Use addChild instead!")
  @Deprecated
  public final THISTYPE addChildren (@Nullable final IPredefinedLocaleTextProvider aChild)
  {
    return addChild (aChild);
  }

  @Nonnull
  public final THISTYPE addChildren (@Nullable final IPredefinedLocaleTextProvider... aChildren)
  {
    if (aChildren != null)
      for (final IPredefinedLocaleTextProvider aChild : aChildren)
        addChild (aChild);
    return thisAsT ();
  }

  @Nonnull
  @DevelopersNote ("Use addChild instead!")
  @Deprecated
  public final THISTYPE addChildren (@Nullable final String sChild)
  {
    return addChild (sChild);
  }

  @Nonnull
  public final THISTYPE addChildren (@Nullable final String... aChildren)
  {
    if (aChildren != null)
      for (final String sChild : aChildren)
        addChild (sChild);
    return thisAsT ();
  }

  @Nonnull
  @DevelopersNote ("Use addChild instead!")
  @Deprecated
  public final THISTYPE addChildren (@Nullable final IHCNodeBuilder aChild)
  {
    return addChild (aChild);
  }

  @Nonnull
  public final THISTYPE addChildren (@Nullable final IHCNodeBuilder... aChildren)
  {
    if (aChildren != null)
      for (final IHCNodeBuilder aChild : aChildren)
        addChild (aChild);
    return thisAsT ();
  }

  @Deprecated
  @DevelopersNote ("Use addChild instead!")
  @Nonnull
  public final THISTYPE addChildren (@Nullable final IHCNode aChild)
  {
    return addChild (aChild);
  }

  @Nonnull
  public final THISTYPE addChildren (@Nullable final IHCNode... aChildren)
  {
    if (aChildren != null)
      for (final IHCNode aChild : aChildren)
        addChild (aChild);
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE addChildren (@Nullable final Iterable <? extends IHCNode> aChildren)
  {
    if (aChildren != null)
      for (final IHCNode aChild : aChildren)
        addChild (aChild);
    return thisAsT ();
  }

  @Nullable
  @CheckReturnValue
  public final <V extends IHCNode> V addAndReturnChild (@Nullable final V aChild)
  {
    addChild (aChild);
    return aChild;
  }

  @Nullable
  @CheckReturnValue
  public final <V extends IHCNode> V addAndReturnChild (@Nonnegative final int nIndex, @Nullable final V aChild)
  {
    addChild (nIndex, aChild);
    return aChild;
  }

  @Nonnull
  public final THISTYPE removeChild (@Nullable final IHCNode aChild)
  {
    if (aChild != null && m_aChildren != null)
      m_aChildren.remove (aChild);
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE removeChild (@Nonnegative final int nIndex)
  {
    if (m_aChildren != null && m_aChildren.size () > nIndex)
      m_aChildren.remove (nIndex);
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE removeAllChildren ()
  {
    if (m_aChildren != null)
      m_aChildren.clear ();
    return thisAsT ();
  }

  @Nonnegative
  public final int getChildCount ()
  {
    return ContainerHelper.getSize (m_aChildren);
  }

  @Nullable
  @ReturnsMutableCopy
  public final List <IHCNode> getChildren ()
  {
    return ContainerHelper.newList (m_aChildren);
  }

  @Nonnull
  public final HCNodeList getAllChildrenAsNodeList ()
  {
    return new HCNodeList ().addChildren (m_aChildren);
  }

  @Nullable
  public final IHCNode getChildAtIndex (@Nonnegative final int nIndex)
  {
    return ContainerHelper.getSafe (m_aChildren, nIndex);
  }

  @Nullable
  public final IHCNode getFirstChild ()
  {
    return ContainerHelper.getFirstElement (m_aChildren);
  }

  @Nullable
  public final IHCNode getLastChild ()
  {
    return ContainerHelper.getLastElement (m_aChildren);
  }

  @Nonnull
  public final THISTYPE sortAllChildren (@Nonnull final Comparator <IHCNode> aComparator)
  {
    if (aComparator == null)
      throw new NullPointerException ("comparator");
    if (m_aChildren != null)
      Collections.sort (m_aChildren, aComparator);
    return thisAsT ();
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void internalBeforeConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    if (hasChildren ())
      for (final IHCNode aChild : m_aChildren)
        aChild.beforeConvertToNode (aConversionSettings);
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (hasChildren ())
      for (final IHCNode aChild : m_aChildren)
        aElement.appendChild (aChild.convertToNode (aConversionSettings));

    if (!aElement.hasChildren ())
    {
      // If no children are present, add an empty text node so that an opening
      // and a closing tag are written separately
      // Note: just using "hasChildren" is not enough, in case a child is
      // present, that is not rendered!
      if (!getElement ().mayBeSelfClosed ())
        aElement.appendText ("");
    }
  }

  @Override
  @Nonnull
  public String getPlainText ()
  {
    if (!hasChildren ())
      return "";
    final StringBuilder ret = new StringBuilder ();
    for (final IHCNode aChild : m_aChildren)
    {
      final String sPlainText = aChild.getPlainText ();
      if (StringHelper.hasText (sPlainText))
      {
        if (ret.length () > 0)
          ret.append (' ');
        ret.append (sPlainText);
      }
    }
    return ret.toString ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).appendIfNotNull ("children", m_aChildren).toString ();
  }
}
