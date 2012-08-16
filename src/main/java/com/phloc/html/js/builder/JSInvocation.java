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

import java.util.ArrayList;
import java.util.List;

/**
 * JMethod invocation
 */
public final class JSInvocation extends AbstractJSExpressionImpl implements IJSStatement
{

  /**
   * Object expression upon which this method will be invoked, or null if this
   * is a constructor invocation
   */
  private IJSGenerable m_aObject;

  /**
   * Name of the method to be invoked. Either this field is set, or
   * {@link #m_aMethod}, or {@link #m_aType} (in which case it's a constructor
   * invocation.) This allows {@link JSMethod#name(String) the name of the
   * method to be changed later}.
   */
  private String m_sName;

  private JSMethod m_aMethod;

  private boolean m_bIsConstructor = false;

  /**
   * List of argument expressions for this method invocation
   */
  private final List <IJSExpression> args = new ArrayList <IJSExpression> ();

  /**
   * If isConstructor==true, this field keeps the type to be created.
   */
  private AbstractJSType m_aType = null;

  /**
   * Invokes a method on an object.
   * 
   * @param object
   *        JExpression for the object upon which the named method will be
   *        invoked, or null if none
   * @param name
   *        Name of method to invoke
   */
  JSInvocation (final IJSExpression object, final String name)
  {
    this ((IJSGenerable) object, name);
  }

  JSInvocation (final IJSExpression object, final JSMethod method)
  {
    this ((IJSGenerable) object, method);
  }

  /**
   * Invokes a static method on a class.
   */
  JSInvocation (final AbstractJSClass type, final String name)
  {
    this ((IJSGenerable) type, name);
  }

  JSInvocation (final AbstractJSClass type, final JSMethod method)
  {
    this ((IJSGenerable) type, method);
  }

  private JSInvocation (final IJSGenerable object, final String name)
  {
    this.m_aObject = object;
    if (name.indexOf ('.') >= 0)
      throw new IllegalArgumentException ("method name contains '.': " + name);
    this.m_sName = name;
  }

  private JSInvocation (final IJSGenerable object, final JSMethod method)
  {
    this.m_aObject = object;
    this.m_aMethod = method;
  }

  /**
   * Invokes a constructor of an object (i.e., creates a new object.)
   * 
   * @param c
   *        Type of the object to be created. If this type is an array type,
   *        added arguments are treated as array initializer. Thus you can
   *        create an expression like <code>new int[]{1,2,3,4,5}</code>.
   */
  JSInvocation (final AbstractJSType c)
  {
    this.m_bIsConstructor = true;
    this.m_aType = c;
  }

  /**
   * Add an expression to this invocation's argument list
   * 
   * @param arg
   *        Argument to add to argument list
   */
  public JSInvocation arg (final IJSExpression arg)
  {
    if (arg == null)
      throw new IllegalArgumentException ();
    args.add (arg);
    return this;
  }

  /**
   * Adds a literal argument. Short for {@code arg(JExpr.lit(v))}
   */
  public JSInvocation arg (final String v)
  {
    return arg (JSExpr.lit (v));
  }

  /**
   * Returns all arguments of the invocation.
   * 
   * @return If there's no arguments, an empty array will be returned.
   */
  public IJSExpression [] listArgs ()
  {
    return args.toArray (new IJSExpression [args.size ()]);
  }

  public void generate (final JSFormatter f)
  {
    if (m_bIsConstructor && m_aType.isArray ())
    {
      // [RESULT] new T[]{arg1,arg2,arg3,...};
      f.p ("new").g (m_aType).p ('{');
    }
    else
    {
      if (m_bIsConstructor)
        f.p ("new").g (m_aType).p ('(');
      else
      {
        String name = this.m_sName;
        if (name == null)
          name = this.m_aMethod.name ();

        if (m_aObject != null)
          f.g (m_aObject).p ('.').p (name).p ('(');
        else
          f.id (name).p ('(');
      }
    }

    f.g (args);

    if (m_bIsConstructor && m_aType.isArray ())
      f.p ('}');
    else
      f.p (')');
  }

  public void state (final JSFormatter f)
  {
    f.g (this).p (';').nl ();
  }
}
