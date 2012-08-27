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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.string.ToStringGenerator;

/**
 * A list of JS statements that is itself a statement
 * 
 * @author philip
 */
public final class JSStatementList implements IJSStatement
{
  private final List <IJSStatement> m_aStatements = new ArrayList <IJSStatement> ();

  public JSStatementList ()
  {}

  public JSStatementList (@Nullable final IJSStatement... aStatements)
  {
    if (aStatements != null)
      for (final IJSStatement aStatement : aStatements)
        add (aStatement);
  }

  public JSStatementList (@Nullable final Iterable <IJSStatement> aStatements)
  {
    if (aStatements != null)
      for (final IJSStatement aStatement : aStatements)
        add (aStatement);
  }

  @Nonnull
  public JSStatementList add (@Nonnull final IJSStatement aStatement)
  {
    if (aStatement == null)
      throw new NullPointerException ("statement");
    m_aStatements.add (aStatement);
    return this;
  }

  @Override
  public void state (@Nonnull final JSFormatter f)
  {
    for (final IJSStatement aStatement : m_aStatements)
      f.stmt (aStatement);
  }

  @Nullable
  public String getJSCode ()
  {
    return JSPrinter.getAsString (this);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("statements", m_aStatements).toString ();
  }
}
