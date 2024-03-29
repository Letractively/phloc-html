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
import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.annotations.OverrideOnDemand;
import com.phloc.commons.annotations.ReturnsMutableCopy;
import com.phloc.commons.annotations.ReturnsMutableObject;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.lang.GenericReflection;
import com.phloc.commons.microdom.IMicroContainer;
import com.phloc.commons.microdom.impl.MicroContainer;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.html.EHTMLElement;
import com.phloc.html.hc.IHCHasChildrenMutable;
import com.phloc.html.hc.IHCNode;
import com.phloc.html.hc.conversion.IHCConversionSettingsToNode;
import com.phloc.html.hc.htmlext.HCUtils;

/**
 * This class is an abstract HC node that represents a list of nodes without
 * creating an HTML element by itself.
 * 
 * @author Philip Helger
 */
@NotThreadSafe
public abstract class AbstractHCHasChildrenMutable <THISTYPE extends AbstractHCHasChildrenMutable <THISTYPE, CHILDTYPE>, CHILDTYPE extends IHCNode> extends AbstractHCNode implements IHCHasChildrenMutable <THISTYPE, CHILDTYPE>
{
  private List <CHILDTYPE> m_aChildren;

  public AbstractHCHasChildrenMutable ()
  {}

  @Nonnull
  protected final THISTYPE thisAsT ()
  {
    // Avoid the unchecked cast warning in all places
    return GenericReflection.<AbstractHCHasChildrenMutable <THISTYPE, CHILDTYPE>, THISTYPE> uncheckedCast (this);
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
  protected void beforeAddChild (@Nonnull final CHILDTYPE aChild)
  {}

  /**
   * Callback
   * 
   * @param nIndex
   *        Index where the child was added. Always &ge; 0.
   * @param aChild
   *        The child that was added
   */
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void afterAddChild (@Nonnegative final int nIndex, @Nonnull final CHILDTYPE aChild)
  {
    aChild.onAdded (nIndex, this);
  }

  private void _addChild (@CheckForSigned final int nIndex, @Nullable final CHILDTYPE aChild)
  {
    if (aChild == this)
      throw new IllegalArgumentException ("Cannot append to self!");

    if (aChild != null)
    {
      beforeAddChild (aChild);
      if (m_aChildren == null)
        m_aChildren = new ArrayList <CHILDTYPE> ();
      int nAddIndex;
      if (nIndex < 0)
      {
        nAddIndex = m_aChildren.size ();
        m_aChildren.add (aChild);
      }
      else
      {
        nAddIndex = nIndex;
        m_aChildren.add (nIndex, aChild);
      }
      afterAddChild (nAddIndex, aChild);
    }
  }

  @Nonnull
  public final THISTYPE addChild (@Nullable final CHILDTYPE aChild)
  {
    _addChild (CGlobal.ILLEGAL_UINT, aChild);
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE addChild (@Nonnegative final int nIndex, @Nullable final CHILDTYPE aChild)
  {
    ValueEnforcer.isBetweenInclusive (nIndex, "Index", 0, getChildCount ());
    _addChild (nIndex, aChild);
    return thisAsT ();
  }

  @Nonnull
  @Deprecated
  public final THISTYPE addChildren (@Nullable final CHILDTYPE aChild)
  {
    return addChild (aChild);
  }

  @Nonnull
  public final THISTYPE addChildren (@Nullable final CHILDTYPE... aChildren)
  {
    if (aChildren != null)
      for (final CHILDTYPE aChild : aChildren)
        addChild (aChild);
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE addChildren (@Nullable final Iterable <? extends CHILDTYPE> aChildren)
  {
    if (aChildren != null)
      for (final CHILDTYPE aChild : aChildren)
        addChild (aChild);
    return thisAsT ();
  }

  @Nullable
  @CheckReturnValue
  public final <V extends CHILDTYPE> V addAndReturnChild (@Nullable final V aChild)
  {
    addChild (aChild);
    return aChild;
  }

  @Nullable
  @CheckReturnValue
  public final <V extends CHILDTYPE> V addAndReturnChild (@Nonnegative final int nIndex, @Nullable final V aChild)
  {
    addChild (nIndex, aChild);
    return aChild;
  }

  /**
   * Invoked after an element was removed.
   * 
   * @param nIndex
   *        The index where the element was removed from. Always &ge; 0. This is
   *        the OLD index and now contains a different or no child.
   * @param aChild
   *        The child that was removed. Never <code>null</code>.
   */
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void afterRemoveChild (@Nonnegative final int nIndex, @Nonnull final CHILDTYPE aChild)
  {
    aChild.onRemoved (nIndex, this);
  }

  @Nonnull
  public final THISTYPE removeChild (@Nullable final CHILDTYPE aChild)
  {
    if (aChild != null && m_aChildren != null)
    {
      final int nChildIndex = m_aChildren.indexOf (aChild);
      if (nChildIndex >= 0)
        removeChild (nChildIndex);
    }
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE removeChild (@Nonnegative final int nIndex)
  {
    final CHILDTYPE aRemovedChild = ContainerHelper.removeAndReturnElementAtIndex (m_aChildren, nIndex);
    if (aRemovedChild != null)
      afterRemoveChild (nIndex, aRemovedChild);
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE removeAllChildren ()
  {
    if (m_aChildren != null)
    {
      while (!m_aChildren.isEmpty ())
        removeChild (0);
      m_aChildren = null;
    }
    return thisAsT ();
  }

  @Nonnegative
  public final int getChildCount ()
  {
    return ContainerHelper.getSize (m_aChildren);
  }

  @Nullable
  @ReturnsMutableObject (reason = "speed")
  protected final List <CHILDTYPE> directGetChildren ()
  {
    return m_aChildren;
  }

  @Nonnull
  @ReturnsMutableCopy
  public final List <CHILDTYPE> getChildren ()
  {
    return ContainerHelper.newList (m_aChildren);
  }

  @Nullable
  public final CHILDTYPE getChildAtIndex (@Nonnegative final int nIndex)
  {
    return ContainerHelper.getSafe (m_aChildren, nIndex);
  }

  @Nullable
  public final CHILDTYPE getFirstChild ()
  {
    return ContainerHelper.getFirstElement (m_aChildren);
  }

  @Nullable
  public final CHILDTYPE getLastChild ()
  {
    return ContainerHelper.getLastElement (m_aChildren);
  }

  public final boolean recursiveContainsChildWithTagName (@Nonnull @Nonempty final EHTMLElement... aElements)
  {
    return HCUtils.recursiveGetFirstChildWithTagName (this, aElements) != null;
  }

  @Nonnull
  public final THISTYPE sortAllChildren (@Nonnull final Comparator <? super CHILDTYPE> aComparator)
  {
    ValueEnforcer.notNull (aComparator, "Comparator");
    if (m_aChildren != null)
      Collections.sort (m_aChildren, aComparator);
    return thisAsT ();
  }

  @Nonnull
  public final HCNodeList getAllChildrenAsNodeList ()
  {
    return new HCNodeList ().addChildren (m_aChildren);
  }

  /**
   * Try to simplify this node list as much as possible.
   * 
   * @return the most simple representation of this list. If the list is empty,
   *         <code>null</code> is returned. If exactly one element is contained,
   *         this element will be returned. If more than one element is
   *         contained no simplification can be performed.
   */
  @Nullable
  public IHCNode getAsSimpleNode ()
  {
    if (m_aChildren.isEmpty ())
      return null;

    if (m_aChildren.size () == 1)
      return ContainerHelper.getFirstElement (m_aChildren);

    // Return as-is
    return this;
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  public boolean canConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    if (hasChildren ())
    {
      // If at least one child is present and can be converted to a node, the
      // whole list can be converted to a node
      for (final CHILDTYPE aChild : m_aChildren)
        if (aChild.canConvertToNode (aConversionSettings))
          return true;
    }
    // No children, or all children cannot be converted -> cannot convert this
    // list
    return false;
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void internalBeforeConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Propagate to children
    if (hasChildren ())
      for (final CHILDTYPE aChild : m_aChildren)
        aChild.beforeConvertToNode (aConversionSettings);
  }

  @Nonnull
  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected IMicroContainer internalConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    final IMicroContainer ret = new MicroContainer ();
    if (hasChildren ())
      for (final CHILDTYPE aNode : m_aChildren)
        ret.appendChild (aNode.convertToNode (aConversionSettings));
    return ret;
  }

  @Override
  @Nonnull
  public String getPlainText ()
  {
    if (!hasChildren ())
      return "";

    final StringBuilder ret = new StringBuilder ();
    for (final CHILDTYPE aNode : m_aChildren)
    {
      final String sPlainText = aNode.getPlainText ();
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
    return ToStringGenerator.getDerived (super.toString ()).append ("children", m_aChildren).toString ();
  }
}
