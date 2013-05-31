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
package com.phloc.html.hc.api;

import javax.annotation.Nonnull;

import com.phloc.commons.annotations.Nonempty;

/**
 * An enumeration that can be used in {@link com.phloc.html.hc.html.HCParam}
 * objects to define the used value type.
 * 
 * @author philip
 */
public enum EHCParamValueType implements IHCHasHTMLAttributeValue
{
  DATA ("data"),
  REF ("ref"),
  OBJECT ("object");

  public static final EHCParamValueType DEFAULT = DATA;

  private final String m_sAttrValue;

  private EHCParamValueType (@Nonnull @Nonempty final String sAttrValue)
  {
    m_sAttrValue = sAttrValue;
  }

  @Nonnull
  @Nonempty
  public String getAttrValue ()
  {
    return m_sAttrValue;
  }
}