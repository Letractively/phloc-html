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
package com.phloc.html.js.builder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.ToStringGenerator;

/**
 * A return statement
 * 
 * @author philip
 */
public class JSReturn implements IJSStatement
{
  /**
   * Expression to return; may be null.
   */
  private final IJSExpression m_aExpr;

  /**
   * Return constructor
   * 
   * @param expr
   *        JExpression which evaluates to return value
   */
  public JSReturn (@Nullable final IJSExpression expr)
  {
    m_aExpr = expr;
  }

  public void state (@Nonnull final JSFormatter f)
  {
    f.plain ("return");
    if (m_aExpr != null)
      f.plain (' ').generatable (m_aExpr);
    f.plain (';').nl ();
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
    final JSReturn rhs = (JSReturn) o;
    return EqualsUtils.equals (m_aExpr, rhs.m_aExpr);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aExpr).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("expr", m_aExpr).toString ();
  }
}