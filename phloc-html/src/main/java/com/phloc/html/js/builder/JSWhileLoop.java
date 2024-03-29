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
import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.ToStringGenerator;

/**
 * While statement
 *
 * @author Philip Helger
 */
public class JSWhileLoop implements IJSStatement
{
  /**
   * Test part of While statement for determining exit state
   */
  private final IJSExpression m_aTest;

  /**
   * Block of statements which makes up body of this While statement
   */
  private JSBlock m_aBody;

  /**
   * Construct a While statement
   */
  public JSWhileLoop (@Nonnull final IJSExpression aTest)
  {
    m_aTest = ValueEnforcer.notNull (aTest, "Test");
  }

  @Nonnull
  public IJSExpression test ()
  {
    return m_aTest;
  }

  @Nonnull
  public JSBlock body ()
  {
    if (m_aBody == null)
      m_aBody = new JSBlock ();
    return m_aBody;
  }

  public void state (@Nonnull final JSFormatter aFormatter)
  {
    if (JSOp.hasOperator (m_aTest))
      aFormatter.plain ("while").generatable (m_aTest);
    else
      aFormatter.plain ("while(").generatable (m_aTest).plain (')');
    if (m_aBody != null)
      aFormatter.stmt (m_aBody);
    else
      aFormatter.plain (';').nl ();
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
    final JSWhileLoop rhs = (JSWhileLoop) o;
    return m_aTest.equals (rhs.m_aTest) && EqualsUtils.equals (m_aBody, rhs.m_aBody);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aTest).append (m_aBody).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("test", m_aTest).append ("body", m_aBody).toString ();
  }
}
