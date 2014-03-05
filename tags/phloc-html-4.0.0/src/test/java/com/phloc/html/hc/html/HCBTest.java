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
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.Test;

import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.text.IPredefinedLocaleTextProvider;
import com.phloc.html.hc.IHCNode;
import com.phloc.html.hc.IHCNodeBuilder;

/**
 * Test class for class {@link HCB}
 * This file is automatically generated from com.phloc.html.supplementary.main.MainCreateHCClasses so DO NOT EDIT!
 * 
 * @author Philip Helger
 */
public final class HCBTest
{
  @Test
  public void testCreate ()
  {
    assertFalse (new HCB ().hasChildren ());
    assertEquals (0, HCB.create ((IPredefinedLocaleTextProvider) null).getChildCount ());
    assertEquals (0, HCB.create ((IPredefinedLocaleTextProvider) null, (IPredefinedLocaleTextProvider) null).getChildCount ());
    assertEquals (0, HCB.create ((IHCNodeBuilder) null).getChildCount ());
    assertEquals (0, HCB.create ((IHCNodeBuilder) null, (IHCNodeBuilder) null).getChildCount ());
    assertEquals (1, HCB.create ("Text").getChildCount ());
    assertEquals (0, HCB.create ((String) null).getChildCount ());
    assertEquals (1, HCB.create (HCB.create ("Bold")).getChildCount ());
    assertEquals (0, HCB.create ((IHCNode) null).getChildCount ());
    assertEquals (3, HCB.create ("Hallo", "Welt", "!!!").getChildCount ());
    assertEquals (0, HCB.create (new String [0]).getChildCount ());
    assertEquals (0, HCB.create ((String []) null).getChildCount ());
    assertEquals (2, HCB.create (HCB.create ("Bold"), HCI.create ("Italic")).getChildCount ());
    assertEquals (0, HCB.create (new IHCNode [0]).getChildCount ());
    assertEquals (0, HCB.create ((IHCNode []) null).getChildCount ());
    assertEquals (2, HCB.create (ContainerHelper.newList (HCB.create ("Bold"), HCI.create ("Italic")))
                         .getChildCount ());
    assertEquals (0, HCB.create (new ArrayList <IHCNode> ()).getChildCount ());
  }
}
