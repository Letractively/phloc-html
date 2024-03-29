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
package com.phloc.html;

import javax.annotation.concurrent.Immutable;

import com.phloc.commons.annotations.PresentForCodeCoverage;

/**
 * See http://dev.w3.org/html5/markup/common-models.html#common-models
 * 
 * @author Philip Helger
 */
@Immutable
public final class CHTMLContentModel
{
  public static final int VALUE_METADATA = CHTMLContentKind.VALUE_METADATA;
  public static final int VALUE_FLOW = CHTMLContentKind.VALUE_FLOW;
  public static final int VALUE_PHRASING = CHTMLContentKind.VALUE_PHRASING;

  @SuppressWarnings ("unused")
  @PresentForCodeCoverage
  private static final CHTMLContentModel s_aInstance = new CHTMLContentModel ();

  private CHTMLContentModel ()
  {}
}
