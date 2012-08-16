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

/**
 * JClass for generating expressions containing operators
 */
public final class JSOp
{
  private JSOp ()
  {}

  /**
   * Determine whether the top level of an expression involves an operator.
   */
  static boolean hasTopOp (final IJSExpression e)
  {
    return (e instanceof JSUnaryOp) || (e instanceof JSBinaryOp);
  }

  /* -- Unary operators -- */

  private static class JSUnaryOp extends AbstractJSExpressionImpl
  {
    protected String m_sOp;
    protected IJSExpression m_aExpr;
    protected boolean opFirst = true;

    JSUnaryOp (final String op, final IJSExpression e)
    {
      this.m_sOp = op;
      this.m_aExpr = e;
    }

    JSUnaryOp (final IJSExpression e, final String op)
    {
      this.m_sOp = op;
      this.m_aExpr = e;
      opFirst = false;
    }

    public void generate (final JSFormatter f)
    {
      if (opFirst)
        f.plain ('(').plain (m_sOp).generatable (m_aExpr).plain (')');
      else
        f.plain ('(').generatable (m_aExpr).plain (m_sOp).plain (')');
    }

  }

  public static IJSExpression minus (final IJSExpression e)
  {
    return new JSUnaryOp ("-", e);
  }

  /**
   * Logical not <tt>'!x'</tt>.
   */
  public static IJSExpression not (final IJSExpression e)
  {
    if (e == JSExpr.TRUE)
      return JSExpr.FALSE;
    if (e == JSExpr.FALSE)
      return JSExpr.TRUE;
    return new JSUnaryOp ("!", e);
  }

  public static IJSExpression complement (final IJSExpression e)
  {
    return new JSUnaryOp ("~", e);
  }

  private static class JSTightUnaryOp extends JSUnaryOp
  {
    JSTightUnaryOp (final IJSExpression e, final String op)
    {
      super (e, op);
    }

    JSTightUnaryOp (final String op, final IJSExpression e)
    {
      super (op, e);
    }

    @Override
    public void generate (final JSFormatter f)
    {
      if (opFirst)
        f.plain (m_sOp).generatable (m_aExpr);
      else
        f.generatable (m_aExpr).plain (m_sOp);
    }
  }

  public static IJSExpression incr (final IJSExpression e)
  {
    return new JSTightUnaryOp (e, "++");
  }

  public static IJSExpression decr (final IJSExpression e)
  {
    return new JSTightUnaryOp (e, "--");
  }

  public static IJSExpression typeof (final IJSExpression e)
  {
    return new JSTightUnaryOp ("typeof ", e);
  }

  /* -- Binary operators -- */

  private static class JSBinaryOp extends AbstractJSExpressionImpl
  {
    private final IJSExpression m_aLeft;
    private final String m_sOp;
    private final IJSGeneratable m_aRight;

    JSBinaryOp (final String op, final IJSExpression left, final IJSGeneratable right)
    {
      this.m_aLeft = left;
      this.m_sOp = op;
      this.m_aRight = right;
    }

    public void generate (final JSFormatter f)
    {
      f.plain ('(').generatable (m_aLeft).plain (m_sOp).generatable (m_aRight).plain (')');
    }
  }

  public static IJSExpression plus (final IJSExpression left, final IJSExpression right)
  {
    return new JSBinaryOp ("+", left, right);
  }

  public static IJSExpression minus (final IJSExpression left, final IJSExpression right)
  {
    return new JSBinaryOp ("-", left, right);
  }

  public static IJSExpression mul (final IJSExpression left, final IJSExpression right)
  {
    return new JSBinaryOp ("*", left, right);
  }

  public static IJSExpression div (final IJSExpression left, final IJSExpression right)
  {
    return new JSBinaryOp ("/", left, right);
  }

  public static IJSExpression mod (final IJSExpression left, final IJSExpression right)
  {
    return new JSBinaryOp ("%", left, right);
  }

  public static IJSExpression shl (final IJSExpression left, final IJSExpression right)
  {
    return new JSBinaryOp ("<<", left, right);
  }

  public static IJSExpression shr (final IJSExpression left, final IJSExpression right)
  {
    return new JSBinaryOp (">>", left, right);
  }

  public static IJSExpression shrz (final IJSExpression left, final IJSExpression right)
  {
    return new JSBinaryOp (">>>", left, right);
  }

  public static IJSExpression band (final IJSExpression left, final IJSExpression right)
  {
    return new JSBinaryOp ("&", left, right);
  }

  public static IJSExpression bor (final IJSExpression left, final IJSExpression right)
  {
    return new JSBinaryOp ("|", left, right);
  }

  public static IJSExpression cand (final IJSExpression left, final IJSExpression right)
  {
    if (left == JSExpr.TRUE)
      return right;
    if (right == JSExpr.TRUE)
      return left;
    if (left == JSExpr.FALSE)
      return left; // JExpr.FALSE
    if (right == JSExpr.FALSE)
      return right; // JExpr.FALSE
    return new JSBinaryOp ("&&", left, right);
  }

  public static IJSExpression cor (final IJSExpression left, final IJSExpression right)
  {
    if (left == JSExpr.TRUE)
      return left; // JExpr.TRUE
    if (right == JSExpr.TRUE)
      return right; // JExpr.FALSE
    if (left == JSExpr.FALSE)
      return right;
    if (right == JSExpr.FALSE)
      return left;
    return new JSBinaryOp ("||", left, right);
  }

  public static IJSExpression xor (final IJSExpression left, final IJSExpression right)
  {
    return new JSBinaryOp ("^", left, right);
  }

  public static IJSExpression lt (final IJSExpression left, final IJSExpression right)
  {
    return new JSBinaryOp ("<", left, right);
  }

  public static IJSExpression lte (final IJSExpression left, final IJSExpression right)
  {
    return new JSBinaryOp ("<=", left, right);
  }

  public static IJSExpression gt (final IJSExpression left, final IJSExpression right)
  {
    return new JSBinaryOp (">", left, right);
  }

  public static IJSExpression gte (final IJSExpression left, final IJSExpression right)
  {
    return new JSBinaryOp (">=", left, right);
  }

  // equals
  public static IJSExpression eq (final IJSExpression left, final IJSExpression right)
  {
    return new JSBinaryOp ("==", left, right);
  }

  // exactly equals
  public static IJSExpression eeq (final IJSExpression left, final IJSExpression right)
  {
    return new JSBinaryOp ("===", left, right);
  }

  // not equal
  public static IJSExpression ne (final IJSExpression left, final IJSExpression right)
  {
    return new JSBinaryOp ("!=", left, right);
  }

  // exactly not equal
  public static IJSExpression ene (final IJSExpression left, final IJSExpression right)
  {
    return new JSBinaryOp ("!==", left, right);
  }

  /* -- Ternary operators -- */

  private static class JSTernaryOp extends AbstractJSExpressionImpl
  {
    private final String m_sOp1;
    private final String m_sOp2;
    private final IJSExpression m_aExpr1;
    private final IJSExpression m_aExpr2;
    private final IJSExpression m_aExpr3;

    JSTernaryOp (final String op1,
                 final String op2,
                 final IJSExpression e1,
                 final IJSExpression e2,
                 final IJSExpression e3)
    {
      this.m_aExpr1 = e1;
      this.m_sOp1 = op1;
      this.m_aExpr2 = e2;
      this.m_sOp2 = op2;
      this.m_aExpr3 = e3;
    }

    public void generate (final JSFormatter f)
    {
      f.plain ('(').generatable (m_aExpr1).plain (m_sOp1).generatable (m_aExpr2).plain (m_sOp2).generatable (m_aExpr3).plain (')');
    }
  }

  public static IJSExpression cond (final IJSExpression cond, final IJSExpression ifTrue, final IJSExpression ifFalse)
  {
    return new JSTernaryOp ("?", ":", cond, ifTrue, ifFalse);
  }
}
