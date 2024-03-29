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

import javax.annotation.Nonnull;

import com.phloc.commons.annotations.DevelopersNote;
import com.phloc.html.annotations.OutOfBandNode;
import com.phloc.html.js.IJSCodeProvider;
import com.phloc.html.js.builder.jquery.JQuery;
import com.phloc.html.js.provider.UnparsedJSCodeProvider;

/**
 * Regular inline JS script elements with the special semantics, that stuff will
 * be executed on document.ready. This requires jQuery to be present!
 * 
 * @author Philip Helger
 * @see HCScript
 * @see HCScriptFile
 */
@OutOfBandNode
public class HCScriptOnDocumentReady extends HCScript
{
  private final IJSCodeProvider m_aOnDocumentReadyCode;

  public HCScriptOnDocumentReady (@Nonnull final IJSCodeProvider aOnDocumentReadyCode)
  {
    super (JQuery.onDocumentReady (aOnDocumentReadyCode));
    m_aOnDocumentReadyCode = aOnDocumentReadyCode;
  }

  @DevelopersNote ("Handle with care!")
  public HCScriptOnDocumentReady (@Nonnull final String sJSCode)
  {
    this (new UnparsedJSCodeProvider (sJSCode));
  }

  /**
   * @return The contained code, to be executed on document.ready
   */
  @Nonnull
  public IJSCodeProvider getOnDocumentReadyCode ()
  {
    return m_aOnDocumentReadyCode;
  }
}
