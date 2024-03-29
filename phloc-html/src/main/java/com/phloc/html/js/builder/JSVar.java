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
import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.html.js.marshal.JSMarshaller;

/**
 * Variables and fields.
 *
 * @author Philip Helger
 */
public class JSVar extends AbstractJSAssignmentTarget implements IJSDeclaration
{
  /**
   * type of the variable
   */
  private AbstractJSType m_aType;

  /**
   * Name of the variable
   */
  private String m_sName;

  /**
   * Initialization of the variable in its declaration
   */
  private IJSExpression m_aInit;

  /**
   * Constructor
   *
   * @param sName
   *        name of the variable
   */
  public JSVar (@Nonnull @Nonempty final String sName)
  {
    this (sName, (IJSExpression) null);
  }

  /**
   * Constructor
   *
   * @param sName
   *        Name of this variable
   * @param aInit
   *        Value to initialize this variable to
   */
  public JSVar (@Nonnull @Nonempty final String sName, @Nullable final IJSExpression aInit)
  {
    this ((AbstractJSType) null, sName, aInit);
  }

  /**
   * Constructor
   *
   * @param aType
   *        Type of this variable
   * @param sName
   *        Name of this variable
   */
  public JSVar (@Nullable final AbstractJSType aType, @Nonnull @Nonempty final String sName)
  {
    this (aType, sName, (IJSExpression) null);
  }

  /**
   * Constructor
   *
   * @param aType
   *        Datatype of this variable
   * @param sName
   *        Name of this variable
   * @param aInit
   *        Value to initialize this variable to
   */
  public JSVar (@Nullable final AbstractJSType aType,
                @Nonnull @Nonempty final String sName,
                @Nullable final IJSExpression aInit)
  {
    if (!JSMarshaller.isJSIdentifier (sName))
      throw new IllegalArgumentException ("The name '" + sName + "' is not a legal JS identifier!");
    m_aType = aType;
    m_sName = sName;
    m_aInit = aInit;
  }

  /**
   * @return the type of this variable.
   */
  @Nullable
  public AbstractJSType type ()
  {
    return m_aType;
  }

  /**
   * Sets the type of this variable.
   *
   * @param aNewType
   *        new type. may be <code>null</code>.
   * @return the old type value.
   */
  @Nullable
  public AbstractJSType type (@Nullable final AbstractJSType aNewType)
  {
    final AbstractJSType aOldType = m_aType;
    m_aType = aNewType;
    return aOldType;
  }

  /**
   * Get the name of this variable
   *
   * @return Name of the variable
   */
  @Nonnull
  @Nonempty
  public String name ()
  {
    return m_sName;
  }

  /**
   * Changes the name of this variable.
   */
  @Nonnull
  public JSVar name (@Nonnull @Nonempty final String sName)
  {
    if (!JSMarshaller.isJSIdentifier (sName))
      throw new IllegalArgumentException ("The name '" + sName + "' is not a legal JS identifier!");
    m_sName = sName;
    return this;
  }

  /**
   * @return <code>true</code> if an init expression is present,
   *         <code>false</code> otherwise
   */
  public boolean hasInit ()
  {
    return m_aInit != null;
  }

  /**
   * @return The init expression currently assigned
   */
  @Nullable
  public IJSExpression init ()
  {
    return m_aInit;
  }

  /**
   * Initialize this variable
   *
   * @param aNewInit
   *        Expression to be used to initialize this field. May be
   *        <code>null</code>.
   */
  @Nonnull
  public JSVar init (@Nullable final IJSExpression aNewInit)
  {
    m_aInit = aNewInit;
    return this;
  }

  void bind (@Nonnull final JSFormatter aFormatter)
  {
    if (m_aType != null && aFormatter.generateTypeNames ())
      aFormatter.plain ("/*").generatable (m_aType).plain ("*/");
    aFormatter.plain (m_sName);
    if (m_aInit != null)
      aFormatter.plain ('=').generatable (m_aInit);
  }

  public void declare (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain ("var ");
    bind (aFormatter);
    aFormatter.plain (';').nl ();
  }

  public void generate (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain (m_sName);
  }

  @Override
  @Nonnull
  public String getJSCode ()
  {
    return JSPrinter.getAsString ((IJSDeclaration) this);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSVar rhs = (JSVar) o;
    return EqualsUtils.equals (m_aType, rhs.m_aType) &&
           EqualsUtils.equals (m_sName, rhs.m_sName) &&
           EqualsUtils.equals (m_aInit, rhs.m_aInit);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ())
                            .append (m_aType)
                            .append (m_sName)
                            .append (m_aInit)
                            .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("type", m_aType)
                            .append ("name", m_sName)
                            .appendIfNotNull ("init", m_aInit)
                            .toString ();
  }
}
