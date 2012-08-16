/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.phloc.html.js.builder;

import java.io.Closeable;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collection;

import javax.annotation.Nonnull;

import com.phloc.commons.string.StringHelper;

/**
 * This is a utility class for managing indentation and other basic formatting
 * for PrintWriter.
 */
public class JSFormatter implements Closeable
{
  /**
   * Current number of indentation strings to print
   */
  private int m_nIndentLevel;

  /**
   * String to be used for each indentation. Defaults to four spaces.
   */
  private final String m_sIndentSpace;

  /**
   * Stream associated with this JFormatter
   */
  private final PrintWriter m_aPW;

  private boolean m_bAtBeginningOfLine = true;

  /**
   * Creates a JFormatter.
   * 
   * @param s
   *        PrintWriter to JFormatter to use.
   * @param space
   *        Incremental indentation string, similar to tab value.
   */
  public JSFormatter (final PrintWriter s, final String space)
  {
    m_aPW = s;
    m_sIndentSpace = space;
  }

  /**
   * Creates a formatter with default incremental indentations of four spaces.
   */
  public JSFormatter (final PrintWriter s)
  {
    this (s, "  ");
  }

  /**
   * Creates a formatter with default incremental indentations of four spaces.
   */
  public JSFormatter (final Writer w)
  {
    this (new PrintWriter (w));
  }

  private void _spaceIfNeeded ()
  {
    if (m_bAtBeginningOfLine)
    {
      if (m_nIndentLevel > 0)
        m_aPW.print (StringHelper.getRepeated (m_sIndentSpace, m_nIndentLevel));
      m_bAtBeginningOfLine = false;
    }
  }

  /**
   * Closes this formatter.
   */
  public void close ()
  {
    m_aPW.close ();
  }

  /**
   * Decrement the indentation level.
   */
  @Nonnull
  public JSFormatter outdent ()
  {
    m_nIndentLevel--;
    return this;
  }

  /**
   * Increment the indentation level.
   */
  @Nonnull
  public JSFormatter indent ()
  {
    m_nIndentLevel++;
    return this;
  }

  /**
   * Print a char into the stream
   * 
   * @param c
   *        the char
   */
  @Nonnull
  public JSFormatter plain (final char c)
  {
    _spaceIfNeeded ();
    m_aPW.print (c);
    return this;
  }

  /**
   * Print a String into the stream
   * 
   * @param s
   *        the String
   */
  @Nonnull
  public JSFormatter plain (@Nonnull final String s)
  {
    _spaceIfNeeded ();
    m_aPW.print (s);
    return this;
  }

  @Nonnull
  public JSFormatter t (@Nonnull final AbstractJSType type)
  {
    if (type.isReference ())
      return type ((AbstractJSClass) type);
    return generatable (type);
  }

  /**
   * Print a type name.
   * <p>
   * In the collecting mode we use this information to decide what types to
   * import and what not to.
   */
  @Nonnull
  public JSFormatter type (@Nonnull final AbstractJSClass type)
  {
    if (type.outer () != null)
      type (type.outer ()).plain ('.').plain (type.name ());
    else
      plain (type.fullName ()); // collision was detected, so generate FQCN
    return this;
  }

  /**
   * Print an identifier
   */
  @Nonnull
  public JSFormatter id (@Nonnull final String id)
  {
    plain (id);
    return this;
  }

  /**
   * Print a new line into the stream
   */
  @Nonnull
  public JSFormatter nl ()
  {
    m_aPW.println ();
    m_bAtBeginningOfLine = true;
    return this;
  }

  /**
   * Cause the JGenerable object to generate source for itself
   * 
   * @param g
   *        the JGenerable object
   */
  @Nonnull
  public JSFormatter generatable (@Nonnull final IJSGeneratable g)
  {
    g.generate (this);
    return this;
  }

  /**
   * Produces {@link IJSGeneratable}s separated by ','
   */
  @Nonnull
  public JSFormatter generatable (@Nonnull final Collection <? extends IJSGeneratable> list)
  {
    boolean first = true;
    for (final IJSGeneratable item : list)
    {
      if (!first)
        plain (',');
      generatable (item);
      first = false;
    }
    return this;
  }

  /**
   * Cause the JDeclaration to generate source for itself
   * 
   * @param d
   *        the JDeclaration object
   */
  @Nonnull
  public JSFormatter decl (@Nonnull final IJSDeclaration d)
  {
    d.declare (this);
    return this;
  }

  /**
   * Cause the JStatement to generate source for itself
   * 
   * @param s
   *        the JStatement object
   */
  @Nonnull
  public JSFormatter stmt (@Nonnull final IJSStatement s)
  {
    s.state (this);
    return this;
  }

  /**
   * Cause the JVar to generate source for itself
   * 
   * @param v
   *        the JVar object
   */
  @Nonnull
  public JSFormatter var (@Nonnull final JSVar v)
  {
    v.bind (this);
    return this;
  }
}