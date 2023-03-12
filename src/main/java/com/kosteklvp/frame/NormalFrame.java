package com.kosteklvp.frame;

import static com.kosteklvp.bowling.BowlingUtils.MAX_NUMBER_OF_KNOCKED_PINS;
import static lombok.AccessLevel.PRIVATE;

import com.kosteklvp.bowling.BowlingUtils;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = PRIVATE)
@Setter(PRIVATE)
public class NormalFrame implements FrameWithType {

  private Integer numberOfPinsKnockedInFirstRoll;
  private Integer numberOfPinsKnockedInSecondRoll;
  private FrameType type = FrameType.NONE;

  // TODO null chack
  @Override
  public int getPoints() {
    return numberOfPinsKnockedInFirstRoll + numberOfPinsKnockedInSecondRoll;
  }

  @Override
  public boolean isSpare() {
    return FrameType.STRIKE.equals(type);
  }

  @Override
  public boolean isStrike() {
    return FrameType.STRIKE.equals(type);
  }

  public static NormalFrame create(Integer first, Integer second) {
    NormalFrame frame = new NormalFrame();
    frame.setNumberOfPinsKnockedInFirstRoll(first);
    frame.setNumberOfPinsKnockedInSecondRoll(second);

    if (BowlingUtils.isSpare(first, second)) {
      frame.setType(FrameType.SPARE);
    }

    return frame;
  }

  public static Frame strike() {
    NormalFrame frame = create(null, MAX_NUMBER_OF_KNOCKED_PINS);
    frame.setType(FrameType.STRIKE);

    return frame;
  }

}
