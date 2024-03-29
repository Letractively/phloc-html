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
package com.phloc.html.js.builder;

import javax.annotation.Nonnull;

import com.phloc.commons.ValueEnforcer;

/**
 * Indicates that a declaration is already created.
 *
 * @author Philip Helger
 */
public class JSNameAlreadyExistsException extends RuntimeException
{
  private final IJSDeclaration m_aExisting;

  public JSNameAlreadyExistsException (@Nonnull final IJSDeclaration aExisting)
  {
    m_aExisting = ValueEnforcer.notNull (aExisting, "Existing");
  }

  /**
   * Gets a reference to the existing {@link IJSDeclaration}.
   *
   * @return This method always return non-null valid object.
   */
  @Nonnull
  public IJSDeclaration getExisting ()
  {
    return m_aExisting;
  }
}
