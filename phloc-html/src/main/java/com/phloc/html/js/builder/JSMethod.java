/**
 * Copyright (C) 2006-2013 phloc systems
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.html.js.marshal.JSMarshaller;

/**
 * JS method.
 * 
 * @author philip
 */
public class JSMethod extends JSFunction
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (JSMethod.class);

  private final JSDefinedClass m_aOwnerClass;

  /**
   * Constructor
   * 
   * @param aOwnerClass
   *        Owning class
   * @param aType
   *        Return type for the method
   * @param sName
   *        Name of this method
   */
  public JSMethod (@Nonnull final JSDefinedClass aOwnerClass,
                   @Nullable final AbstractJSType aType,
                   @Nonnull @Nonempty final String sName)
  {
    super (aType, sName);
    if (aOwnerClass == null)
      throw new NullPointerException ("class");
    if (!JSMarshaller.isJSIdentifier (sName))
      throw new IllegalArgumentException ("The name '" + sName + "' is not a legal JS identifier!");
    if (sName.equals (aOwnerClass.name ()))
      throw new IllegalArgumentException ("You cannot name a method like the constructor!");
    if (!Character.isLowerCase (sName.charAt (0)))
      s_aLogger.warn ("Method names should always start with a lowercase character: '" + sName + "'");
    m_aOwnerClass = aOwnerClass;
    body ().newlineAtEnd (false);
  }

  @Nonnull
  public JSDefinedClass parentClass ()
  {
    return m_aOwnerClass;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSMethod rhs = (JSMethod) o;
    return m_aOwnerClass.name ().equals (rhs.m_aOwnerClass.name ());
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aOwnerClass.name ()).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("ownerClass", m_aOwnerClass.name ()).toString ();
  }
}