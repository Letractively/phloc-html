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
}