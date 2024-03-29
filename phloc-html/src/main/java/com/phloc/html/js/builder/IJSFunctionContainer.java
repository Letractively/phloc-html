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
import javax.annotation.Nullable;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.html.js.IJSCodeProvider;

/**
 * The common aspect of a package and a function.
 * 
 * @author Philip Helger
 */
public interface IJSFunctionContainer extends IJSCodeProvider
{
  /**
   * Add a new public function to this container
   * 
   * @param sName
   *        function name
   * @exception JSNameAlreadyExistsException
   *            When the specified function was already created.
   */
  @Nonnull
  JSFunction function (@Nonnull @Nonempty String sName) throws JSNameAlreadyExistsException;

  /**
   * Add a new public function to this container
   * 
   * @param aReturnType
   *        optional return type
   * @param sName
   *        function name
   * @exception JSNameAlreadyExistsException
   *            When the specified function was already created.
   */
  @Nonnull
  JSFunction function (@Nullable AbstractJSType aReturnType, @Nonnull @Nonempty String sName) throws JSNameAlreadyExistsException;
}
