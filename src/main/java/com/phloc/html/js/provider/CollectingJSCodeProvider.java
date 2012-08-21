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
package com.phloc.html.js.provider;

import javax.annotation.Nullable;

import com.phloc.html.js.IJSCodeProvider;
import com.phloc.json.IJSON;

public final class CollectingJSCodeProvider extends AbstractCollectingJSCodeProvider <CollectingJSCodeProvider>
{
  public CollectingJSCodeProvider ()
  {}

  public CollectingJSCodeProvider (@Nullable final CharSequence... aCSs)
  {
    super (aCSs);
  }

  public CollectingJSCodeProvider (@Nullable final String... aStrings)
  {
    super (aStrings);
  }

  /**
   * Chain all passed JSCode providers together
   * 
   * @param aProviders
   *        The providers to chain
   */
  public CollectingJSCodeProvider (@Nullable final IJSCodeProvider... aProviders)
  {
    super (aProviders);
  }

  public CollectingJSCodeProvider (@Nullable final IJSON... aJSONs)
  {
    super (aJSONs);
  }
}
