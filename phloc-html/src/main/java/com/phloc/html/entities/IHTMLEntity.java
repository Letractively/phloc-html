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
package com.phloc.html.entities;

import javax.annotation.Nonnull;

import com.phloc.commons.annotations.Nonempty;

/**
 * Base interface for a single HTML entity.
 * 
 * @author Philip Helger
 */
public interface IHTMLEntity
{
  /**
   * @return The name of the entity. E.g. <code>nbsp</code>
   */
  @Nonnull
  @Nonempty
  String getEntityName ();

  /**
   * @return The reference to the entity. E.g. <code>&amp;nbsp;</code>
   */
  @Nonnull
  @Nonempty
  String getEntityReference ();
}
