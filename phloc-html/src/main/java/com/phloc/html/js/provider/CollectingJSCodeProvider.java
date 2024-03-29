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
package com.phloc.html.js.provider;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.phloc.commons.ICloneable;
import com.phloc.commons.IHasSize;
import com.phloc.commons.annotations.ReturnsMutableCopy;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.html.js.IJSCodeProvider;

/**
 * A JSCode provider that encapsulates a list of {@link IJSCodeProvider}
 * elements and itself implements {@link IJSCodeProvider}.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class CollectingJSCodeProvider implements IJSCodeProvider, IHasSize, ICloneable <CollectingJSCodeProvider>
{
  private final List <IJSCodeProvider> m_aList = new ArrayList <IJSCodeProvider> ();

  public CollectingJSCodeProvider ()
  {}

  public CollectingJSCodeProvider (@Nullable final IJSCodeProvider... aProviders)
  {
    if (aProviders != null)
      for (final IJSCodeProvider aProvider : aProviders)
        if (aProvider != null)
          append (aProvider);
  }

  public CollectingJSCodeProvider (@Nullable final Iterable <? extends IJSCodeProvider> aProviders)
  {
    if (aProviders != null)
      for (final IJSCodeProvider aProvider : aProviders)
        if (aProvider != null)
          append (aProvider);
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <IJSCodeProvider> getAll ()
  {
    return ContainerHelper.newList (m_aList);
  }

  @Nonnull
  public CollectingJSCodeProvider append (@Nullable final IJSCodeProvider aProvider)
  {
    if (aProvider != null)
      m_aList.add (aProvider);
    return this;
  }

  @Nonnull
  public CollectingJSCodeProvider appendFlattened (@Nullable final IJSCodeProvider aProvider)
  {
    if (aProvider != null)
      if (aProvider instanceof CollectingJSCodeProvider)
        m_aList.addAll (((CollectingJSCodeProvider) aProvider).m_aList);
      else
        m_aList.add (aProvider);
    return this;
  }

  @Nonnull
  public CollectingJSCodeProvider prepend (@Nullable final IJSCodeProvider aProvider)
  {
    return addAtIndex (0, aProvider);
  }

  @Nonnull
  public CollectingJSCodeProvider prependFlattened (@Nullable final IJSCodeProvider aProvider)
  {
    return addAtIndexFlattened (0, aProvider);
  }

  @Nonnull
  public CollectingJSCodeProvider addAtIndex (@Nonnegative final int nIndex, @Nullable final IJSCodeProvider aProvider)
  {
    if (aProvider != null)
      m_aList.add (nIndex, aProvider);
    return this;
  }

  @Nonnull
  public CollectingJSCodeProvider addAtIndexFlattened (@Nonnegative final int nIndex,
                                                       @Nullable final IJSCodeProvider aProvider)
  {
    if (aProvider != null)
      if (aProvider instanceof CollectingJSCodeProvider)
        m_aList.addAll (nIndex, ((CollectingJSCodeProvider) aProvider).m_aList);
      else
        m_aList.add (nIndex, aProvider);
    return this;
  }

  @Nonnull
  public CollectingJSCodeProvider removeAtIndex (@Nonnegative final int nIndex)
  {
    if (nIndex >= 0 && nIndex < m_aList.size ())
      m_aList.remove (nIndex);
    return this;
  }

  public void reset ()
  {
    m_aList.clear ();
  }

  public boolean isEmpty ()
  {
    return m_aList.isEmpty ();
  }

  public boolean isNotEmpty ()
  {
    return !m_aList.isEmpty ();
  }

  @Nonnegative
  public int size ()
  {
    return m_aList.size ();
  }

  @Nonnull
  public String getJSCode ()
  {
    final StringBuilder aSB = new StringBuilder ();
    for (final IJSCodeProvider aJSCodeProvider : m_aList)
      aSB.append (aJSCodeProvider.getJSCode ());
    return aSB.toString ();
  }

  @Nonnull
  public CollectingJSCodeProvider getClone ()
  {
    return new CollectingJSCodeProvider (m_aList);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!(o instanceof CollectingJSCodeProvider))
      return false;
    final CollectingJSCodeProvider rhs = (CollectingJSCodeProvider) o;
    return m_aList.equals (rhs.m_aList);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aList).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotEmpty ("list", m_aList).toString ();
  }
}
