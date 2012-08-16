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
 * For statement
 */

public class JSForLoop implements IJSStatement
{
  private final List <Object> inits = new ArrayList <Object> ();
  private IJSExpression test = null;
  private final List <IJSExpression> updates = new ArrayList <IJSExpression> ();
  private JSSBlock body = null;

  public JSVar init (final AbstractJSType type, final String var, final IJSExpression e)
  {
    final JSVar v = new JSVar (type, var, e);
    inits.add (v);
    return v;
  }

  public void init (final JSVar v, final IJSExpression e)
  {
    inits.add (JSExpr.assign (v, e));
  }

  public void test (final IJSExpression e)
  {
    this.test = e;
  }

  public void update (final IJSExpression e)
  {
    updates.add (e);
  }

  public JSSBlock body ()
  {
    if (body == null)
      body = new JSSBlock ();
    return body;
  }

  public void state (final JSFormatter f)
  {
    f.plain ("for (");
    boolean first = true;
    for (final Object o : inits)
    {
      if (!first)
        f.plain (',');
      if (o instanceof JSVar)
        f.var ((JSVar) o);
      else
        f.generable ((IJSExpression) o);
      first = false;
    }
    f.plain (';').generable (test).plain (';').generable (updates).plain (')');
    if (body != null)
      f.generable (body).nl ();
    else
      f.plain (';').nl ();
  }

}
