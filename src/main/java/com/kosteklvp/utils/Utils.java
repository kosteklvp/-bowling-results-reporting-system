package com.kosteklvp.utils;

import static java.util.Objects.isNull;
import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class Utils {

  public static char toChar(Integer integer) {
    return Integer.toString(nn(integer)).charAt(0);
  }

  /**
   * Return 0, if Integer is null.
   */
  public static int nn(Integer integer) {
    return isNull(integer) ? 0 : integer;
  }

}
