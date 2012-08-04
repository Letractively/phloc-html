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
import com.phloc.html.hc.HCConversionSettings;
import com.phloc.html.hc.IHCBaseNode;
import com.phloc.html.hc.IHCNode;

/**
 * Base class for an HTML control that consists only of another HC node (e.g.
 * for API limitation)
 * 
 * @author philip
 */
public abstract class AbstractWrappedHCNode extends AbstractHCNode
{
  @Nonnull
  protected abstract IHCNode getContainedHCNode ();

  /**
   * This method is called before the element itself is created. Overwrite this
   * method to perform actions that can only be done when the element is build
   * finally.
   * 
   * @param aConversionSettings
   *        Conversion settings to be applied
   */
  @OverrideOnDemand
  protected void prepareBeforeGetAsNode (@Nonnull final HCConversionSettings aConversionSettings)
  {}

  @Nonnull
  public final IMicroNode getAsNode (@Nonnull final HCConversionSettings aConversionSettings)
  {
    prepareBeforeGetAsNode (aConversionSettings);
    return getContainedHCNode ().getAsNode (aConversionSettings);
  }

  public final String getPlainText ()
  {
    return getContainedHCNode ().getPlainText ();
  }

  @Override
  public final IHCBaseNode getOutOfBandNode ()
  {
    return getContainedHCNode ().getOutOfBandNode ();
  }
}