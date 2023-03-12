package com.kosteklvp.frame;

public interface FrameWithType extends Frame {

  enum FrameType {
    NONE, SPARE, STRIKE;
  }

  boolean isSpare();

  boolean isStrike();

}
