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
package com.phloc.html.hc.html;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.phloc.commons.url.SimpleURL;
import com.phloc.html.hc.conversion.HCSettings;
import com.phloc.html.resource.css.CSSExternal;
import com.phloc.html.resource.js.JSExternal;

/**
 * Test class for class {@link HCHead}
 * 
 * @author philip
 */
public final class HCHeadTest
{
  @Test
  public void testBasic ()
  {
    final HCHead aHead = new HCHead ();
    assertEquals ("<head />", HCSettings.getAsHTMLString (aHead, false));

    aHead.setPageTitle ("phloc");
    assertEquals ("<head><title>phloc</title></head>", HCSettings.getAsHTMLString (aHead, false));

    aHead.setBaseHref ("/root");
    assertEquals ("<head><title>phloc</title><base href=\"/root\" /></head>", HCSettings.getAsHTMLString (aHead, false));
    aHead.setBaseHref (null);
    aHead.setBaseTarget (HCA_Target.BLANK);
    assertEquals ("<head><title>phloc</title><base target=\"_blank\" /></head>",
                  HCSettings.getAsHTMLString (aHead, false));
    aHead.setBaseTarget (null);
    assertEquals ("<head><title>phloc</title></head>", HCSettings.getAsHTMLString (aHead, false));

    aHead.setShortcutIconHref (new SimpleURL ("/favicon.ico"));
    assertEquals ("<head><title>phloc</title><link rel=\"shortcut icon\" href=\"/favicon.ico\"></link><link rel=\"icon\" type=\"image/icon\" href=\"/favicon.ico\"></link></head>",
                  HCSettings.getAsHTMLString (aHead, false));

    aHead.setShortcutIconHref (null);
    aHead.addJS (new JSExternal (new SimpleURL ("/my.js")));
    assertEquals ("<head><title>phloc</title><script type=\"text/javascript\" src=\"/my.js\"></script></head>",
                  HCSettings.getAsHTMLString (aHead, false));

    aHead.addCSS (new CSSExternal (new SimpleURL ("/my.css")));
    assertEquals ("<head><title>phloc</title><link rel=\"stylesheet\" type=\"text/css\" href=\"/my.css\"></link><script type=\"text/javascript\" src=\"/my.js\"></script></head>",
                  HCSettings.getAsHTMLString (aHead, false));
  }

  @Test
  public void testOutOfBandNodes ()
  {
    final HCHtml aHtml = new HCHtml ();
    aHtml.getBody ().addChild (new HCH1 ("Test"));
    aHtml.getBody ().addOutOfBandNode (new HCStyle ("h1{color:red;}"));
    // Ensure that the out-of-band nodes are handled, because we're not calling
    // aHtml.getAsNode ()
    aHtml.copyOutOfBandNodesFromBodyToHead (HCSettings.getConversionSettings (false));
    assertEquals ("<head><style type=\"text/css\">h1{color:red;}</style></head>",
                  HCSettings.getAsHTMLString (aHtml.getHead (), false));
  }
}
