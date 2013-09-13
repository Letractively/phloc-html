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
package com.phloc.html.hc.html;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.text.IPredefinedLocaleTextProvider;
import com.phloc.html.EHTMLElement;
import com.phloc.html.hc.IHCNode;
import com.phloc.html.hc.IHCNodeBuilder;
import com.phloc.html.hc.impl.AbstractHCElementWithChildren;

/**
 * Represents an HTML &lt;NOSCRIPT&gt; element<br>
 * This file is automatically generated from
 * com.phloc.html.tools.MainCreateHCClasses so DO NOT EDIT!
 * 
 * @author Philip Helger
 */
public class HCNoScript extends AbstractHCElementWithChildren <HCNoScript>
{
  /**
   * Create a new NOSCRIPT element
   */
  public HCNoScript ()
  {
    super (EHTMLElement.NOSCRIPT);
  }

  /**
   * Create a new NOSCRIPT element with the passed child text
   * 
   * @param aChild
   *        The child text provider to be appended. May be <code>null</code>
   * @return The created HCNoScript element and never <code>null</code>
   */
  @Nonnull
  public static HCNoScript create (@Nullable final IPredefinedLocaleTextProvider aChild)
  {
    return new HCNoScript ().addChild (aChild);
  }

  /**
   * Create a new NOSCRIPT element with the passed child texts
   * 
   * @param aChildren
   *        The child texts to be appended. May be <code>null</code>
   * @return The created HCNoScript element and never <code>null</code>
   */
  @Nonnull
  public static HCNoScript create (@Nullable final IPredefinedLocaleTextProvider... aChildren)
  {
    return new HCNoScript ().addChildren (aChildren);
  }

  /**
   * Create a new NOSCRIPT element with the passed child text
   * 
   * @param sChild
   *        The child to be appended. May be <code>null</code>
   * @return The created HCNoScript element and never <code>null</code>
   */
  @Nonnull
  public static HCNoScript create (@Nullable final String sChild)
  {
    return new HCNoScript ().addChild (sChild);
  }

  /**
   * Create a new NOSCRIPT element with the passed child texts
   * 
   * @param aChildren
   *        The child texts to be appended. May be <code>null</code>
   * @return The created HCNoScript element and never <code>null</code>
   */
  @Nonnull
  public static HCNoScript create (@Nullable final String... aChildren)
  {
    return new HCNoScript ().addChildren (aChildren);
  }

  /**
   * Create a new NOSCRIPT element with the passed child node
   * 
   * @param aChild
   *        The child node to be appended. May be <code>null</code>
   * @return The created HCNoScript element and never <code>null</code>
   */
  @Nonnull
  public static HCNoScript create (@Nullable final IHCNodeBuilder aChild)
  {
    return new HCNoScript ().addChild (aChild);
  }

  /**
   * Create a new NOSCRIPT element with the passed child nodes
   * 
   * @param aChildren
   *        The child nodes to be appended. May be <code>null</code>
   * @return The created HCNoScript element and never <code>null</code>
   */
  @Nonnull
  public static HCNoScript create (@Nullable final IHCNodeBuilder... aChildren)
  {
    return new HCNoScript ().addChildren (aChildren);
  }

  /**
   * Create a new NOSCRIPT element with the passed child node
   * 
   * @param aChild
   *        The child node to be appended. May be <code>null</code>
   * @return The created HCNoScript element and never <code>null</code>
   */
  @Nonnull
  public static HCNoScript create (@Nullable final IHCNode aChild)
  {
    return new HCNoScript ().addChild (aChild);
  }

  /**
   * Create a new NOSCRIPT element with the passed child nodes
   * 
   * @param aChildren
   *        The child nodes to be appended. May be <code>null</code>
   * @return The created HCNoScript element and never <code>null</code>
   */
  @Nonnull
  public static HCNoScript create (@Nullable final IHCNode... aChildren)
  {
    return new HCNoScript ().addChildren (aChildren);
  }

  /**
   * Create a new NOSCRIPT element with the passed child nodes
   * 
   * @param aChildren
   *        The child nodes to be appended. May be <code>null</code>
   * @return The created HCNoScript element and never <code>null</code>
   */
  @Nonnull
  public static HCNoScript create (@Nullable final Iterable <? extends IHCNode> aChildren)
  {
    return new HCNoScript ().addChildren (aChildren);
  }
}
