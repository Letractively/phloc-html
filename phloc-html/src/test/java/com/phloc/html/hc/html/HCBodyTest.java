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
package com.phloc.html.hc.html;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.phloc.html.hc.conversion.HCSettings;
import com.phloc.html.js.EJSEvent;
import com.phloc.html.js.builder.JSExpr;

public final class HCBodyTest
{
  @Test
  public void testBody ()
  {
    final HCBody aBody = new HCBody ();
    assertEquals ("<body xmlns=\"http://www.w3.org/1999/xhtml\"></body>", HCSettings.getAsHTMLString (aBody, false));

    // With semicolon at the end
    aBody.addEventHandler (EJSEvent.ONLOAD, JSExpr.invoke ("onLoad"));
    // Empty event handler - ignored
    aBody.addEventHandler (EJSEvent.ONMOUSEDOWN, null);
    // With prefix
    aBody.setEventHandler (EJSEvent.ONCLICK, JSExpr.invoke ("onClick"));
    aBody.setCustomAttr ("bla", "foo");
    assertEquals ("<body onload=\"javascript:onLoad();\" onclick=\"javascript:onClick();\" bla=\"foo\" xmlns=\"http://www.w3.org/1999/xhtml\"></body>",
                  HCSettings.getAsHTMLString (aBody, false));
  }
}
