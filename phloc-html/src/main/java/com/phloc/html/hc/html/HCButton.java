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
import javax.annotation.Nullable;

import com.phloc.commons.text.IPredefinedLocaleTextProvider;
import com.phloc.html.js.IJSCodeProvider;

/**
 * Represents an HTML &lt;button&gt; element with type "button"
 * 
 * @author Philip Helger
 */
public class HCButton extends AbstractHCButton <HCButton>
{
  public HCButton ()
  {}

  public HCButton (@Nonnull final IPredefinedLocaleTextProvider aLabel)
  {
    super (aLabel.getText ());
  }

  public HCButton (@Nullable final String sLabel)
  {
    super (sLabel);
  }

  public HCButton (@Nullable final String sLabel, @Nullable final IJSCodeProvider aOnClick)
  {
    super (sLabel, aOnClick);
  }
}
