/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2011 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.phloc.html.js.builder.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.CharsetEncoder;

import com.phloc.commons.charset.CharsetManager;
import com.phloc.html.js.builder.JSCodeModel;
import com.phloc.html.js.builder.JSDefinedClass;
import com.phloc.html.js.builder.JSFormatter;
import com.phloc.html.js.builder.JSPackage;

/**
 * Receives generated code and writes to the appropriate storage.
 * 
 * @author Kohsuke Kawaguchi (kohsuke.kawaguchi@sun.com)
 */
public abstract class AbstractCodeWriter
{
  /**
   * Encoding to be used by the writer. Null means platform specific encoding.
   * 
   * @since 2.5
   */
  protected String m_sEncoding = null;

  /**
   * Called by CodeModel to store the specified file. The callee must allocate a
   * storage to store the specified file.
   * <p>
   * The returned stream will be closed before the next file is stored. So the
   * callee can assume that only one OutputStream is active at any given time.
   * 
   * @param pkg
   *        The package of the file to be written.
   * @param fileName
   *        File name without the path. Something like "Foo.java" or
   *        "Bar.properties"
   */
  public abstract OutputStream openBinary (JSPackage pkg, String fileName) throws IOException;

  /**
   * Called by CodeModel to store the specified file. The callee must allocate a
   * storage to store the specified file.
   * <p>
   * The returned stream will be closed before the next file is stored. So the
   * callee can assume that only one OutputStream is active at any given time.
   * 
   * @param pkg
   *        The package of the file to be written.
   * @param fileName
   *        File name without the path. Something like "Foo.java" or
   *        "Bar.properties"
   */
  public Writer openSource (final JSPackage pkg, final String fileName) throws IOException
  {
    final OutputStreamWriter bw = m_sEncoding != null
                                                     ? new OutputStreamWriter (openBinary (pkg, fileName), m_sEncoding)
                                                     : new OutputStreamWriter (openBinary (pkg, fileName));

    // create writer
    try
    {
      return new UnicodeEscapeWriter (bw)
      {
        // can't change this signature to Encoder because
        // we can't have Encoder in method signature
        private final CharsetEncoder encoder = CharsetManager.getCharsetFromName (bw.getEncoding ()).newEncoder ();

        @Override
        protected boolean requireEscaping (final int ch)
        {
          // control characters
          if (ch < 0x20 && " \t\r\n".indexOf (ch) == -1)
            return true;
          // check ASCII chars, for better performance
          if (ch < 0x80)
            return false;

          return !encoder.canEncode ((char) ch);
        }
      };
    }
    catch (final Throwable t)
    {
      return new UnicodeEscapeWriter (bw);
    }
  }

  /**
   * Called by CodeModel at the end of the process.
   */
  public abstract void close () throws IOException;

  public void build (final JSCodeModel aCodeModel) throws IOException
  {
    // avoid concurrent modification exception
    for (final JSPackage pkg : aCodeModel.getAllPackages ())
    {
      // write classes
      for (final JSDefinedClass c : pkg.getAllClasses ())
      {
        final Writer bw = new BufferedWriter (openSource (pkg, c.name () + ".js"));
        final JSFormatter f = new JSFormatter (new PrintWriter (bw));
        f.write (c);
        f.close ();
      }
    }
    close ();
  }
}
