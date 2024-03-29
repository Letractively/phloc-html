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
package com.phloc.html.hc.api5;

import javax.annotation.Nonnull;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.html.hc.api.IHCHasHTMLAttributeValue;

/**
 * Sandbox allowance?
 * 
 * @author Philip Helger
 */
public enum EHCSandboxAllow implements IHCHasHTMLAttributeValue
{
  ALLOW_FORMS ("allow-forms"),
  ALLOW_SCRIPTS ("allow-scripts"),
  ALLOW_TOP_NAVIGATION ("allow-top-navigation"),
  ALLOW_SAME_ORIGIN ("allow-same-origin");

  private final String m_sAttrValue;

  private EHCSandboxAllow (@Nonnull @Nonempty final String sAttrValue)
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
