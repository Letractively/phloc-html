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
package com.phloc.html.hc;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.html.hc.api.EHCInputType;

/**
 * Base interface for controls base on &lt;input&gt; area.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCInput <IMPLTYPE extends IHCInput <IMPLTYPE>> extends IHCControl <IMPLTYPE>
{
  @Nonnull
  EHCInputType getType ();

  @Nullable
  String getPlaceholder ();

  @Nonnull
  IMPLTYPE setPlaceholder (@Nullable String sPlaceholder);
}
