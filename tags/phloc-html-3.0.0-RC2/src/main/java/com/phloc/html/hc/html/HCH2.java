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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.text.IPredefinedLocaleTextProvider;
import com.phloc.html.EHTMLElement;
import com.phloc.html.hc.IHCNode;
import com.phloc.html.hc.impl.AbstractHCElementWithChildren;

public final class HCH2 extends AbstractHCElementWithChildren <HCH2>
{
  public HCH2 ()
  {
    super (EHTMLElement.H2);
  }

  public HCH2 (@Nonnull final IPredefinedLocaleTextProvider aChild)
  {
    this (aChild.getText ());
  }

  public HCH2 (@Nullable final String sChild)
  {
    super (EHTMLElement.H2, sChild);
  }

  public HCH2 (@Nullable final String... aChildren)
  {
    super (EHTMLElement.H2, aChildren);
  }

  public HCH2 (@Nullable final IHCNode aChild)
  {
    super (EHTMLElement.H2, aChild);
  }

  public HCH2 (@Nullable final IHCNode... aChildren)
  {
    super (EHTMLElement.H2, aChildren);
  }

  public HCH2 (@Nullable final Iterable <? extends IHCNode> aChildren)
  {
    super (EHTMLElement.H2, aChildren);
  }
}