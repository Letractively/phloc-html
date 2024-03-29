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
package com.phloc.html.hc.conversion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.phloc.html.hc.html.HCBR;

/**
 * Test class for class {final @link HCSettings}.
 * 
 * @author Philip Helger
 */
public final class HCSettingsTest
{
  @Test
  public void testGetAsString ()
  {
    final HCBR aBR = new HCBR ();
    final String s = HCSettings.getAsHTMLString (aBR, false);
    assertNotNull (s);
    assertEquals ("<br xmlns=\"http://www.w3.org/1999/xhtml\" />", s);
  }

  @Test
  public void testGetAsStringWithoutNamespaces ()
  {
    final HCBR aBR = new HCBR ();
    final String s = HCSettings.getAsHTMLStringWithoutNamespaces (aBR, false);
    assertNotNull (s);
    assertEquals ("<br />", s);
  }
}
