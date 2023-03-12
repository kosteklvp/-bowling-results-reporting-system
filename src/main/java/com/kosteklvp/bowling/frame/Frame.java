package com.kosteklvp.bowling.frame;

import com.kosteklvp.bowling.roll.Roll;

public interface Frame {

  Roll getFirstRoll();

  Roll getSecondRoll();

  default Roll getThirdRoll() {
    return null;
  }

}
