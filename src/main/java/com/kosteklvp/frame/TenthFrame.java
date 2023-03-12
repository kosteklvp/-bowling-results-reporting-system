package com.kosteklvp.frame;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class TenthFrame implements Frame {

  private Integer numberOfPinsKnockedInFirstRoll;
  private Integer numberOfPinsKnockedInSecondRoll;
  private Integer numberOfPinsKnockedInThirdRoll;

  @Override
  public int getPoints() {
    return 0;
  }

  public static Frame create(Integer first, Integer second, Integer third) {
    TenthFrame frame = new TenthFrame();
    frame.numberOfPinsKnockedInFirstRoll = first;
    frame.numberOfPinsKnockedInSecondRoll = second;
    frame.numberOfPinsKnockedInThirdRoll = third;

    return frame;
  }
}
