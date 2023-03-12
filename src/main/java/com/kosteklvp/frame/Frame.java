package com.kosteklvp.frame;

import com.kosteklvp.roll.Roll;

public interface Frame {

  Roll getFirstRoll();

  Roll getSecondRoll();

  default Roll getThirdRoll() {
    return null;
  }

}
