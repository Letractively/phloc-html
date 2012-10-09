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
package com.phloc.html.js.builder;

import javax.annotation.Nonnull;

import com.phloc.commons.string.ToStringGenerator;

/**
 * An atomic integer number
 * 
 * @author philip
 */
public class JSAtomInt extends AbstractJSAtomNumeric
{
  private final long m_nValue;

  public JSAtomInt (final long nValue)
  {
    m_nValue = nValue;
  }

  @Override
  public boolean isDecimalValue ()
  {
    return false;
  }

  @Override
  public double doubleValue ()
  {
    return m_nValue;
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericMinus ()
  {
    return new JSAtomInt (-m_nValue);
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericIncr ()
  {
    return new JSAtomInt (m_nValue + 1);
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericDecr ()
  {
    return new JSAtomInt (m_nValue - 1);
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericPlus (@Nonnull final AbstractJSAtomNumeric rhs)
  {
    if (rhs.isDecimalValue ())
      return new JSAtomDecimal (m_nValue + rhs.doubleValue ());
    return new JSAtomInt (m_nValue + (long) rhs.doubleValue ());
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericMinus (@Nonnull final AbstractJSAtomNumeric rhs)
  {
    if (rhs.isDecimalValue ())
      return new JSAtomDecimal (m_nValue - rhs.doubleValue ());
    return new JSAtomInt (m_nValue - (long) rhs.doubleValue ());
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericMul (@Nonnull final AbstractJSAtomNumeric rhs)
  {
    if (rhs.isDecimalValue ())
      return new JSAtomDecimal (m_nValue * rhs.doubleValue ());
    return new JSAtomInt (m_nValue * (long) rhs.doubleValue ());
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericDiv (@Nonnull final AbstractJSAtomNumeric rhs)
  {
    // In JS there is by default no integer division: 5/2===2.5
    return new JSAtomDecimal (m_nValue / rhs.doubleValue ());
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericMod (@Nonnull final AbstractJSAtomNumeric rhs)
  {
    if (rhs.isDecimalValue ())
      return new JSAtomDecimal (m_nValue % rhs.doubleValue ());
    return new JSAtomInt (m_nValue % (long) rhs.doubleValue ());
  }

  public long getContainedValue ()
  {
    return m_nValue;
  }

  public void generate (@Nonnull final JSFormatter f)
  {
    f.plain (Long.toString (m_nValue));
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("value", m_nValue).toString ();
  }
}
