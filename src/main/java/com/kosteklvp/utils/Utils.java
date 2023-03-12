package com.kosteklvp.utils;

import static java.util.Objects.isNull;

public class Utils {

  private Utils() {
  }

  public static char toChar(Integer integer) {
    return Integer.toString(integer).charAt(0);
  }

  /**
   * Return 0, if Integer is null.
   */
  public static int nn(Integer integer) {
    return isNull(integer) ? 0 : integer;
  }

}
