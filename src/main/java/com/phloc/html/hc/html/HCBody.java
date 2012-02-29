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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.phloc.html.EHTMLElement;
import com.phloc.html.hc.IHCBaseNode;
import com.phloc.html.hc.conversion.HCConversionSettings;
import com.phloc.html.hc.impl.AbstractHCElementWithChildren;
import com.phloc.html.hc.impl.HCNodeList;

/**
 * Implementation of an HTML document &lt;body&gt; element
 * 
 * @author philip
 */
public class HCBody extends AbstractHCElementWithChildren <HCBody>
{
  private final List <IHCBaseNode> m_aOufOfBandNodes = new ArrayList <IHCBaseNode> ();

  public HCBody ()
  {
    super (EHTMLElement.BODY);
  }

  public void addOufOfBandNode (@Nonnull final IHCBaseNode aOufOfBandNode)
  {
    if (aOufOfBandNode == null)
      throw new NullPointerException ("outOfBandNode");
    m_aOufOfBandNodes.add (aOufOfBandNode);
  }

  public void addOufOfBandNodes (@Nonnull final List <IHCBaseNode> aOufOfBandNodes)
  {
    if (aOufOfBandNodes == null)
      throw new NullPointerException ("outOfBandNodes");
    m_aOufOfBandNodes.addAll (aOufOfBandNodes);
  }

  @Override
  public IHCBaseNode getOutOfBandNode (@Nonnull final HCConversionSettings aConversionSettings)
  {
    final IHCBaseNode aOutOfBandNode = super.getOutOfBandNode (aConversionSettings);
    if (m_aOufOfBandNodes.isEmpty ())
    {
      // This object has no out of band nodes
      return aOutOfBandNode;
    }

    // Concatenate nodes
    final HCNodeList ret = new HCNodeList ();
    ret.addChild (aOutOfBandNode);
    ret.addChildren (m_aOufOfBandNodes);
    return ret.getAsSimpleNode ();
  }
}
