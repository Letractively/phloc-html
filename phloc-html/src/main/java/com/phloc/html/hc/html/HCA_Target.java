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
package com.phloc.html.hc.html;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.phloc.commons.ValueEnforcer;
import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.html.hc.api.IHCHasHTMLAttributeValue;

/**
 * Represents the value of the "target" attribute of an HTML &lt;a&gt; element
 *
 * @author Philip Helger
 */
@Immutable
public class HCA_Target implements IHCHasHTMLAttributeValue, Serializable
{
  // See http://de.selfhtml.org/html/verweise/definieren.htm#zielfenster
  /** New window */
  public static final HCA_Target BLANK = new HCA_Target ("_blank");
  /** This window */
  public static final HCA_Target SELF = new HCA_Target ("_self");
  /** Parent frame */
  public static final HCA_Target PARENT = new HCA_Target ("_parent");
  /** Out of frames */
  public static final HCA_Target TOP = new HCA_Target ("_top");

  private final String m_sName;

  public HCA_Target (@Nonnull @Nonempty final String sName)
  {
    m_sName = ValueEnforcer.notEmpty (sName, "Name");
  }

  @Nonnull
  @Nonempty
  public String getName ()
  {
    return m_sName;
  }

  @Nonnull
  @Nonempty
  public String getAttrValue ()
  {
    return m_sName;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (super.toString ()).append ("name", m_sName).toString ();
  }

  /**
   * Try to find one of the default targets by name. The name comparison is
   * performed case insensitive.
   *
   * @param sName
   *        The name to check. May not be <code>null</code>.
   * @param aDefault
   *        The default value to be returned in case the name was never found.
   *        May be <code>null</code>.
   * @return The constant link target representing the name or the default
   *         value. May be <code>null</code> if the passed default value is
   *         <code>null</code> and the name was not found.
   */
  @Nullable
  public static HCA_Target getFromName (@Nonnull final String sName, @Nullable final HCA_Target aDefault)
  {
    if (BLANK.getAttrValue ().equalsIgnoreCase (sName))
      return BLANK;
    if (SELF.getAttrValue ().equalsIgnoreCase (sName))
      return SELF;
    if (PARENT.getAttrValue ().equalsIgnoreCase (sName))
      return PARENT;
    if (TOP.getAttrValue ().equalsIgnoreCase (sName))
      return TOP;
    return aDefault;
  }
}
