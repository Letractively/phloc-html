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

import java.util.ArrayList;

import org.junit.Test;

import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.text.IPredefinedLocaleTextProvider;
import com.phloc.html.hc.IHCNode;

/**
 * Test class for class {@link HCSamp} This file is automatically generated from
 * com.phloc.html.tools.MainCreateHCClasses so DO NOT EDIT!
 * 
 * @author philip
 */
public final class HCSampTest
{
  @Test
  public void testCreate ()
  {
    assertFalse (new HCSamp ().hasChildren ());
    assertEquals (0, HCSamp.create ((IPredefinedLocaleTextProvider) null).getChildCount ());
    assertEquals (1, HCSamp.create ("Text").getChildCount ());
    assertEquals (0, HCSamp.create ((String) null).getChildCount ());
    assertEquals (1, HCSamp.create (HCB.create ("Bold")).getChildCount ());
    assertEquals (0, HCSamp.create ((IHCNode) null).getChildCount ());
    assertEquals (3, HCSamp.create ("Hallo", "Welt", "!!!").getChildCount ());
    assertEquals (0, HCSamp.create (new String [0]).getChildCount ());
    assertEquals (0, HCSamp.create ((String []) null).getChildCount ());
    assertEquals (2, HCSamp.create (HCB.create ("Bold"), HCI.create ("Italic")).getChildCount ());
    assertEquals (0, HCSamp.create (new IHCNode [0]).getChildCount ());
    assertEquals (0, HCSamp.create ((IHCNode []) null).getChildCount ());
    assertEquals (2, HCSamp.create (ContainerHelper.newList (HCB.create ("Bold"), HCI.create ("Italic")))
                           .getChildCount ());
    assertEquals (0, HCSamp.create (new ArrayList <IHCNode> ()).getChildCount ());
  }

  @SuppressWarnings ("deprecation")
  @Test
  public void testDeprecated ()
  {
    assertFalse (new HCSamp ().hasChildren ());
    assertEquals (0, new HCSamp ((IPredefinedLocaleTextProvider) null).getChildCount ());
    assertEquals (1, new HCSamp ("Text").getChildCount ());
    assertEquals (0, new HCSamp ((String) null).getChildCount ());
    assertEquals (1, new HCSamp (HCB.create ("Bold")).getChildCount ());
    assertEquals (0, new HCSamp ((IHCNode) null).getChildCount ());
    assertEquals (3, new HCSamp ("Hallo", "Welt", "!!!").getChildCount ());
    assertEquals (0, new HCSamp (new String [0]).getChildCount ());
    assertEquals (0, new HCSamp ((String []) null).getChildCount ());
    assertEquals (2, new HCSamp (HCB.create ("Bold"), HCI.create ("Italic")).getChildCount ());
    assertEquals (0, new HCSamp (new IHCNode [0]).getChildCount ());
    assertEquals (0, new HCSamp ((IHCNode []) null).getChildCount ());
    assertEquals (2, new HCSamp (ContainerHelper.newList (HCB.create ("Bold"), HCI.create ("Italic"))).getChildCount ());
    assertEquals (0, new HCSamp (new ArrayList <IHCNode> ()).getChildCount ());
  }
}
