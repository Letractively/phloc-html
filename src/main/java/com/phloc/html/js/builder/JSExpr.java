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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Factory methods that generate various {@link IJSExpression}s.
 */
public final class JSExpr
{
  /**
   * This class is not instanciable.
   */
  private JSExpr ()
  {}

  @Nonnull
  public static IJSExpression assign (@Nonnull final IJSAssignmentTarget lhs, @Nonnull final IJSExpression rhs)
  {
    return new JSAssignment (lhs, rhs);
  }

  @Nonnull
  public static IJSExpression assignPlus (@Nonnull final IJSAssignmentTarget lhs, @Nonnull final IJSExpression rhs)
  {
    return new JSAssignment (lhs, "+=", rhs);
  }

  @Nonnull
  public static JSInvocation _new (@Nonnull final AbstractJSClass c)
  {
    return new JSInvocation (c);
  }

  @Nonnull
  public static JSInvocation _new (@Nonnull final AbstractJSType t)
  {
    return new JSInvocation (t);
  }

  @Nonnull
  public static JSInvocation invoke (@Nonnull final String method)
  {
    return new JSInvocation ((IJSExpression) null, method);
  }

  public static JSInvocation invoke (final JSMethod method)
  {
    return new JSInvocation ((IJSExpression) null, method);
  }

  public static JSInvocation invoke (@Nullable final IJSExpression lhs, final String method)
  {
    return new JSInvocation (lhs, method);
  }

  public static JSInvocation invoke (@Nullable final IJSExpression lhs, final JSMethod method)
  {
    return new JSInvocation (lhs, method);
  }

  public static JSInvocation invoke (final JSFunction function)
  {
    return new JSInvocation (function);
  }

  public static JSFieldRef ref (final String field)
  {
    return new JSFieldRef ((IJSExpression) null, field);
  }

  public static JSFieldRef ref (final IJSExpression lhs, final JSVar field)
  {
    return new JSFieldRef (lhs, field);
  }

  public static JSFieldRef ref (final IJSExpression lhs, final String field)
  {
    return new JSFieldRef (lhs, field);
  }

  public static JSArrayCompRef component (final IJSExpression lhs, final IJSExpression index)
  {
    return new JSArrayCompRef (lhs, index);
  }

  @Nonnull
  public static JSCast cast (final AbstractJSType type, final IJSExpression expr)
  {
    return new JSCast (type, expr);
  }

  @Nonnull
  public static JSArray newArray (final AbstractJSType type)
  {
    return newArray (type, null);
  }

  /**
   * Generates {@code new T[size]}.
   * 
   * @param type
   *        The type of the array component. 'T' or {@code new T[size]}.
   */
  @Nonnull
  public static JSArray newArray (final AbstractJSType type, @Nullable final IJSExpression size)
  {
    // you cannot create an array whose component type is a generic
    return new JSArray (type, size);
  }

  /**
   * Generates {@code new T[size]}.
   * 
   * @param type
   *        The type of the array component. 'T' or {@code new T[size]}.
   */
  @Nonnull
  public static JSArray newArray (final AbstractJSType type, final int size)
  {
    return newArray (type, lit (size));
  }

  private static final IJSExpression __this = new JSAtom ("this");

  /**
   * Returns a reference to "this", an implicit reference to the current object.
   */
  @Nonnull
  public static IJSExpression _this ()
  {
    return __this;
  }

  /* -- Literals -- */

  private static final IJSExpression __null = new JSAtom ("null");

  @Nonnull
  public static IJSExpression _null ()
  {
    return __null;
  }

  /**
   * Boolean constant that represents <code>true</code>
   */
  public static final IJSExpression TRUE = new JSAtom ("true");

  /**
   * Boolean constant that represents <code>false</code>
   */
  public static final IJSExpression FALSE = new JSAtom ("false");

  /**
   * Boolean constant that represents <code>undefined</code>
   */
  public static final IJSExpression UNDEFINED = new JSAtom ("undefined");

  @Nonnull
  public static IJSExpression lit (final boolean b)
  {
    return b ? TRUE : FALSE;
  }

  @Nonnull
  public static IJSExpression lit (final int n)
  {
    return new JSAtom (Integer.toString (n));
  }

  @Nonnull
  public static IJSExpression lit (final long n)
  {
    return new JSAtom (Long.toString (n));
  }

  @Nonnull
  public static IJSExpression lit (final float f)
  {
    if (Float.isNaN (f))
      return new JSAtom ("Number.NaN");
    return new JSAtom (Float.toString (f));
  }

  @Nonnull
  public static IJSExpression lit (final double d)
  {
    if (Double.isNaN (d))
      return new JSAtom ("Number.NaN");
    return new JSAtom (Double.toString (d));
  }

  @Nonnull
  public static IJSExpression lit (final char c)
  {
    return new JSStringLiteral (Character.toString (c));
  }

  @Nonnull
  public static IJSExpression lit (final String s)
  {
    return new JSStringLiteral (s);
  }

  /**
   * Creates an expression directly from a source code fragment.
   * <p>
   * This method can be used as a short-cut to create a JExpression. For
   * example, instead of <code>_a.gt(_b)</code>, you can write it as:
   * <code>JExpr.direct("a>b")</code>.
   * <p>
   * Be warned that there is a danger in using this method, as it obfuscates the
   * object model.
   */
  public static IJSExpression direct (final String source)
  {
    return new AbstractJSExpressionImpl ()
    {
      public void generate (final JSFormatter f)
      {
        f.plain ('(').plain (source).plain (')');
      }
    };
  }
}