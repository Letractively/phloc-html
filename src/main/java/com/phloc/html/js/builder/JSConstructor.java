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
package com.phloc.html.js.builder;

import javax.annotation.Nonnull;

import com.phloc.commons.string.ToStringGenerator;

/**
 * JS constructor.
 * 
 * @author philip
 */
public class JSConstructor extends JSFunction
{
  private final JSDefinedClass m_aClass;

  /**
   * Constructor constructor
   * 
   * @param aClass
   *        JClass containing this constructor
   */
  public JSConstructor (@Nonnull final JSDefinedClass aClass)
  {
    super (null, aClass.name ());
    m_aClass = aClass;
  }

  @Nonnull
  public JSDefinedClass parentClass ()
  {
    return m_aClass;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("class", m_aClass.name ()).toString ();
  }
}
