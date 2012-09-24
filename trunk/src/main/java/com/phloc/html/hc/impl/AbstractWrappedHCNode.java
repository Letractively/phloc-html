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

import com.phloc.commons.annotations.OverrideOnDemand;
import com.phloc.commons.microdom.IMicroNode;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.html.hc.IHCBaseNode;
import com.phloc.html.hc.IHCNode;
import com.phloc.html.hc.conversion.IHCConversionSettings;

/**
 * Base class for an HTML control that consists only of another HC node (e.g.
 * for API limitation)
 * 
 * @author philip
 */
public abstract class AbstractWrappedHCNode extends AbstractHCNode
{
  private boolean m_bPrepared = false;

  public final boolean isPrepared ()
  {
    return m_bPrepared;
  }

  /**
   * This method is called before the element itself is created. Overwrite this
   * method to perform actions that can only be done when the element is build
   * finally. This method is only called once before the first call to
   * {@link #getContainedHCNode()}!
   * 
   * @param aConversionSettings
   *        Conversion settings to be applied
   */
  @OverrideOnDemand
  protected void prepareBeforeGetAsNode (@Nonnull final IHCConversionSettings aConversionSettings)
  {}

  @Nonnull
  protected abstract IHCNode getContainedHCNode ();

  @Nonnull
  public final IMicroNode getAsNode (@Nonnull final IHCConversionSettings aConversionSettings)
  {
    if (!m_bPrepared)
    {
      prepareBeforeGetAsNode (aConversionSettings);
      m_bPrepared = true;
    }
    return getContainedHCNode ().getAsNode (aConversionSettings);
  }

  public final String getPlainText ()
  {
    return getContainedHCNode ().getPlainText ();
  }

  @Override
  public IHCBaseNode getOutOfBandNode (@Nonnull final IHCConversionSettings aConversionSettings)
  {
    return getContainedHCNode ().getOutOfBandNode (aConversionSettings);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("prepared", m_bPrepared).toString ();
  }
}
