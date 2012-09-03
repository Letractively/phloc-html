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
package com.phloc.html.hc.impl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.microdom.IMicroNode;
import com.phloc.html.hc.IHCBaseNode;
import com.phloc.html.hc.conversion.HCConversionSettings;

/**
 * Implementation of a node that is ONLY an out-of-band node!
 * 
 * @author philip
 */
public final class HCOutOfBandNode extends AbstractHCNode
{
  private final IHCBaseNode m_aOutOfBandNode;

  public HCOutOfBandNode (@Nonnull final IHCBaseNode aOutOfBandNode)
  {
    if (aOutOfBandNode == null)
      throw new NullPointerException ("outOfBandNode");
    m_aOutOfBandNode = aOutOfBandNode;
  }

  @Nullable
  public IMicroNode getAsNode (@Nonnull final HCConversionSettings aConversionSettings)
  {
    return null;
  }

  public String getPlainText ()
  {
    return "";
  }

  @Override
  public IHCBaseNode getOutOfBandNode ()
  {
    return m_aOutOfBandNode;
  }
}