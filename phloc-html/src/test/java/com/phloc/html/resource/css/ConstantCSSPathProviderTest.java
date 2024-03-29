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
package com.phloc.html.resource.css;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.phloc.commons.mock.PhlocTestUtils;

/**
 * Test class for class {@link ConstantCSSPathProvider}.
 * 
 * @author Philip Helger
 */
public final class ConstantCSSPathProviderTest
{
  @Test
  public void testAll ()
  {
    final ConstantCSSPathProvider pp = new ConstantCSSPathProvider ("a.css");
    assertEquals ("a.css", pp.getCSSItemPath (true));
    assertEquals ("a.min.css", pp.getCSSItemPath (false));
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (pp, new ConstantCSSPathProvider ("a.css"));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (pp, new ConstantCSSPathProvider ("b.css"));
  }
}
