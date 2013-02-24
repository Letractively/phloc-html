/**
 * Copyright (C) 2006-2013 phloc systems
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
 * Test class for class {@link HCEM} This file is automatically generated from
 * com.phloc.html.tools.MainCreateHCClasses so DO NOT EDIT!
 * 
 * @author philip
 */
public final class HCEMTest
{
  @Test
  public void testCreate ()
  {
    assertFalse (new HCEM ().hasChildren ());
    assertEquals (0, HCEM.create ((IPredefinedLocaleTextProvider) null).getChildCount ());
    assertEquals (0, HCEM.create ((IPredefinedLocaleTextProvider) null, (IPredefinedLocaleTextProvider) null)
                         .getChildCount ());
    assertEquals (0, HCEM.create ((IHCNodeBuilder) null).getChildCount ());
    assertEquals (0, HCEM.create ((IHCNodeBuilder) null, (IHCNodeBuilder) null).getChildCount ());
    assertEquals (1, HCEM.create ("Text").getChildCount ());
    assertEquals (0, HCEM.create ((String) null).getChildCount ());
    assertEquals (1, HCEM.create (HCB.create ("Bold")).getChildCount ());
    assertEquals (0, HCEM.create ((IHCNode) null).getChildCount ());
    assertEquals (3, HCEM.create ("Hallo", "Welt", "!!!").getChildCount ());
    assertEquals (0, HCEM.create (new String [0]).getChildCount ());
    assertEquals (0, HCEM.create ((String []) null).getChildCount ());
    assertEquals (2, HCEM.create (HCB.create ("Bold"), HCI.create ("Italic")).getChildCount ());
    assertEquals (0, HCEM.create (new IHCNode [0]).getChildCount ());
    assertEquals (0, HCEM.create ((IHCNode []) null).getChildCount ());
    assertEquals (2, HCEM.create (ContainerHelper.newList (HCB.create ("Bold"), HCI.create ("Italic")))
                         .getChildCount ());
    assertEquals (0, HCEM.create (new ArrayList <IHCNode> ()).getChildCount ());
  }
}
