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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.phloc.commons.state.EChange;
import com.phloc.commons.url.SimpleURL;
import com.phloc.html.hc.conversion.HCSettings;
import com.phloc.html.hc.conversion.IHCConversionSettings;
import com.phloc.html.js.provider.UnparsedJSCodeProvider;
import com.phloc.html.meta.MetaElement;

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
    assertNull (aHead.getProfile ());
    assertNull (aHead.getPageTitle ());
    assertNull (aHead.getBaseHref ());
    assertNull (aHead.getBaseTarget ());
    assertTrue (aHead.getAllMetaElements ().isEmpty ());
    assertEquals (0, aHead.getMetaElementCount ());
    assertTrue (aHead.getAllLinks ().isEmpty ());
    assertEquals (0, aHead.getLinkCount ());
    assertTrue (aHead.getAllCSSNodes ().isEmpty ());
    assertTrue (aHead.getAllJSNodes ().isEmpty ());
    assertNotNull (aHead.getOutOfBandHandler ());
    assertEquals ("", aHead.getPlainText ());

    assertSame (aHead, aHead.setProfile ("any"));
    assertEquals ("any", aHead.getProfile ());
    assertEquals ("<head profile=\"any\"></head>", HCSettings.getAsHTMLString (aHead, false));

    assertSame (aHead, aHead.setPageTitle ("Title"));
    assertEquals ("Title", aHead.getPageTitle ());
    assertEquals ("<head profile=\"any\"><title>Title</title></head>", HCSettings.getAsHTMLString (aHead, false));

    assertSame (aHead, aHead.setBaseHref ("/"));
    assertEquals ("/", aHead.getBaseHref ());
    assertEquals ("<head profile=\"any\"><title>Title</title><base href=\"/\" /></head>",
                  HCSettings.getAsHTMLString (aHead, false));

    assertSame (aHead, aHead.setBaseTarget (HCA_Target.BLANK));
    assertEquals (HCA_Target.BLANK, aHead.getBaseTarget ());
    assertEquals ("<head profile=\"any\"><title>Title</title><base href=\"/\" target=\"_blank\" /></head>",
                  HCSettings.getAsHTMLString (aHead, false));
    assertNotNull (aHead.toString ());
  }

  @Test
  public void testMetaElements ()
  {
    final HCHead aHead = new HCHead ();
    assertTrue (aHead.getAllMetaElements ().isEmpty ());
    assertEquals (0, aHead.getMetaElementCount ());

    assertSame (aHead, aHead.addMetaElement (new MetaElement ("foo", "bar")));
    assertFalse (aHead.getAllMetaElements ().isEmpty ());
    assertEquals (1, aHead.getMetaElementCount ());
    assertEquals ("<head>" + "<meta name=\"foo\" content=\"bar\" />" + "</head>",
                  HCSettings.getAsHTMLString (aHead, false));

    assertSame (aHead, aHead.addMetaElement (new MetaElement ("goo", true, "car")));
    assertEquals (2, aHead.getMetaElementCount ());
    assertEquals ("<head>"
                  + "<meta name=\"foo\" content=\"bar\" />"
                  + "<meta http-equiv=\"goo\" content=\"car\" />"
                  + "</head>", HCSettings.getAsHTMLString (aHead, false));

    assertEquals (EChange.UNCHANGED, aHead.removeMetaElement ("any"));
    assertEquals (2, aHead.getMetaElementCount ());
    assertEquals (EChange.CHANGED, aHead.removeMetaElement ("foo"));
    assertEquals (1, aHead.getMetaElementCount ());
    assertEquals (EChange.UNCHANGED, aHead.removeMetaElement ("foo"));
    assertEquals (1, aHead.getMetaElementCount ());
    assertEquals ("<head>" + "<meta http-equiv=\"goo\" content=\"car\" />" + "</head>",
                  HCSettings.getAsHTMLString (aHead, false));
    assertEquals (EChange.CHANGED, aHead.removeMetaElement ("goo"));
    assertEquals (0, aHead.getMetaElementCount ());
    assertEquals ("<head></head>", HCSettings.getAsHTMLString (aHead, false));
  }

  @Test
  public void testGenerate ()
  {
    final HCHead aHead = new HCHead ();
    assertEquals ("<head></head>", HCSettings.getAsHTMLString (aHead, false));

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
    aHead.addJS (new HCScriptFile ().setSrc (new SimpleURL ("/my.js")));
    assertEquals ("<head><title>phloc</title><script type=\"text/javascript\" src=\"/my.js\"></script></head>",
                  HCSettings.getAsHTMLString (aHead, false));

    aHead.addCSS (HCLink.createCSSLink (new SimpleURL ("/my.css")));
    assertEquals ("<head><title>phloc</title><link rel=\"stylesheet\" type=\"text/css\" href=\"/my.css\"></link><script type=\"text/javascript\" src=\"/my.js\"></script></head>",
                  HCSettings.getAsHTMLString (aHead, false));
  }

  @Test
  public void testOutOfBandNodes1 ()
  {
    final IHCConversionSettings aCS = HCSettings.getConversionSettings (false);
    final HCHtml aHtml = new HCHtml ();
    aHtml.getBody ().addChild (new HCH1 ().addChild ("Test"));
    aHtml.getBody ().addOutOfBandNode (new HCStyle ("h1{color:red;}"));
    // Ensure that the out-of-band nodes are handled, because we're not calling
    // aHtml.getAsNode ()
    aHtml.copyOutOfBandNodesFromBodyToHead (aCS);
    assertEquals ("<head><style type=\"text/css\">h1{color:red;}</style></head>", aHtml.getHead ()
                                                                                       .getAsHTMLString (aCS));
    // Do it again and check for node consistency
    assertEquals ("<head><style type=\"text/css\">h1{color:red;}</style></head>", aHtml.getHead ()
                                                                                       .getAsHTMLString (aCS));

    // Call it twice
    assertNotNull (aHtml.getAsHTMLString (aCS));
    assertNotNull (aHtml.getAsHTMLString (aCS));
  }

  @Test
  public void testOutOfBandNodes2 ()
  {
    final IHCConversionSettings aCS = HCSettings.getConversionSettings (false);
    final HCHtml aHtml = new HCHtml ();
    aHtml.getBody ().addChild (new HCH1 ().addChild ("Test"));
    aHtml.getBody ().addOutOfBandNode (new HCScriptOnDocumentReady (new UnparsedJSCodeProvider ("a=b;")));
    aHtml.getBody ().addOutOfBandNode (new HCScriptOnDocumentReady (new UnparsedJSCodeProvider ("c=d;")));
    // Ensure that the out-of-band nodes are handled, because we're not calling
    // aHtml.getAsNode ()
    aHtml.copyOutOfBandNodesFromBodyToHead (aCS);
    assertEquals ("<head><script type=\"text/javascript\"><!--\n"
                  + "$(document).ready(function(){a=b;c=d;});\n"
                  + "//--></script></head>", aHtml.getHead ().getAsHTMLString (aCS));
    // Do it again and check for node consistency
    assertEquals ("<head><script type=\"text/javascript\"><!--\n"
                  + "$(document).ready(function(){a=b;c=d;});\n"
                  + "//--></script></head>", aHtml.getHead ().getAsHTMLString (aCS));

    // Call it twice
    assertNotNull (aHtml.getAsHTMLString (aCS));
    assertNotNull (aHtml.getAsHTMLString (aCS));
  }
}