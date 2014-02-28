package com.phloc.html.markdown.txtmark;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainTest2
{

  /**
   * @param args
   */
  public static void main (final String [] args)
  {
    final String test = "name=value name2=value2 name21='toto' name3=\"value3 value 3\"";
    final Pattern p = Pattern.compile ("(\\w+)=\"*((?<=\")[^\"]+(?=\")|([^\\s]+))\"*");

    // String test = "a0=d235 a1=2314 com1=\"abcd\" com2=\"a b c d\"";
    final Matcher m = p.matcher (test);

    while (m.find ())
    {
      System.err.println (m.group (1));
      System.err.println (m.group (2));
    }
  }

}
