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
package com.phloc.html.hc;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.annotations.DevelopersNote;
import com.phloc.commons.text.IPredefinedLocaleTextProvider;

/**
 * Special node interface for objects containing other objects
 * 
 * @author Philip Helger
 * @param <THISTYPE>
 *        The type of the contained child objects.
 */
public interface IHCNodeWithChildren <THISTYPE extends IHCNodeWithChildren <THISTYPE>> extends IHCHasChildrenMutable <THISTYPE, IHCNode>
{
  /**
   * @param aTextProvider
   *        Child text to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  THISTYPE addChild (@Nullable IPredefinedLocaleTextProvider aTextProvider);

  /**
   * @param sText
   *        Child text to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  THISTYPE addChild (@Nullable String sText);

  /**
   * @param aNodeBuilder
   *        Child to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  THISTYPE addChild (@Nullable IHCNodeBuilder aNodeBuilder);

  /**
   * @param nIndex
   *        The index to where the element should be inserted.
   * @param aTextProvider
   *        Child text to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  THISTYPE addChild (@Nonnegative int nIndex, @Nullable IPredefinedLocaleTextProvider aTextProvider);

  /**
   * @param nIndex
   *        The index to where the element should be inserted.
   * @param sText
   *        Child text to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  THISTYPE addChild (@Nonnegative int nIndex, @Nullable String sText);

  /**
   * @param nIndex
   *        The index to where the element should be inserted.
   * @param aNodeBuilder
   *        Child to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  THISTYPE addChild (@Nonnegative int nIndex, @Nullable IHCNodeBuilder aNodeBuilder);

  /**
   * Use {@link #addChild(IPredefinedLocaleTextProvider)} instead
   * 
   * @param aChild
   *        Child to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  @Deprecated
  THISTYPE addChildren (@Nullable IPredefinedLocaleTextProvider aChild);

  /**
   * @param aChildren
   *        Children to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  THISTYPE addChildren (@Nullable IPredefinedLocaleTextProvider... aChildren);

  /**
   * Use {@link #addChild(String)} instead
   * 
   * @param sChild
   *        Child to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  @Deprecated
  THISTYPE addChildren (@Nullable String sChild);

  /**
   * @param aChildren
   *        Children to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  THISTYPE addChildren (@Nullable String... aChildren);

  /**
   * Use {@link #addChild(IHCNodeBuilder)} instead.
   * 
   * @param aChild
   *        The child to add. May be <code>null</code>.
   * @return this
   */
  @Deprecated
  @DevelopersNote ("Use addChild instead")
  THISTYPE addChildren (@Nullable IHCNodeBuilder aChild);

  /**
   * @param aChildren
   *        Children to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  THISTYPE addChildren (@Nullable IHCNodeBuilder... aChildren);
}
