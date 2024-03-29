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
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.regex.RegExHelper;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Single line comments
 *
 * @author Philip Helger
 */
public class JSCommentSingleLine implements IJSStatement
{
  private final String m_sComment;

  public JSCommentSingleLine (@Nonnull final String sComment)
  {
    m_sComment = ValueEnforcer.notNull (sComment, "Comment");
  }

  @Override
  public void state (@Nonnull final JSFormatter aFormatter)
  {
    if (aFormatter.generateComments ())
      for (final String sLine : RegExHelper.getSplitToArray (m_sComment, "(\\r\\n|\\r|\\n)"))
        if (aFormatter.indentAndAlign ())
          aFormatter.plain ("// ").plain (sLine).nl ();
        else
          aFormatter.plain ("/*").plain (sLine).plain ("*/");
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
    final JSCommentSingleLine rhs = (JSCommentSingleLine) o;
    return m_sComment.equals (rhs.m_sComment);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sComment).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("comment", m_sComment).toString ();
  }
}
