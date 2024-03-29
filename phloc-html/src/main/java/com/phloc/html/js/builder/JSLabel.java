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
package com.phloc.html.js.builder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.ValueEnforcer;
import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Label that can be used for continue and break.
 *
 * @author Philip Helger
 */
public class JSLabel implements IJSStatement
{
  private final String m_sLabel;

  /**
   * constructor
   *
   * @param sLabel
   *        break label or null.
   */
  public JSLabel (@Nonnull @Nonempty final String sLabel)
  {
    m_sLabel = ValueEnforcer.notEmpty (sLabel, "Label");
  }

  @Nonnull
  @Nonempty
  public String label ()
  {
    return m_sLabel;
  }

  public void state (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain (m_sLabel).plain (':').nl ();
  }

  @Nullable
  public String getJSCode ()
  {
    return JSPrinter.getAsString (this);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final JSLabel rhs = (JSLabel) o;
    return m_sLabel.equals (rhs.m_sLabel);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sLabel).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("label", m_sLabel).toString ();
  }
}
