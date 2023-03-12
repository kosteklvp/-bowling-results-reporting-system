package com.kosteklvp.bowling.frame;

import static com.kosteklvp.bowling.BowlingUtils.isSpare;
import static com.kosteklvp.bowling.roll.Roll.none;
import static com.kosteklvp.bowling.roll.Roll.of;
import static com.kosteklvp.bowling.roll.Roll.spareOf;
import static lombok.AccessLevel.PRIVATE;

import com.kosteklvp.bowling.roll.Roll;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = PRIVATE)
@Setter(PRIVATE)
public class NormalFrame implements Frame {

  private Roll firstRoll;
  private Roll secondRoll;

  @Override
  public Roll getFirstRoll() {
    return firstRoll;
  }

  @Override
  public Roll getSecondRoll() {
    return secondRoll;
  }

  public static NormalFrame create(Integer numberOfPinsKnockedInFirstRoll, Integer numberOfPinsKnockedInSecondRoll) {
    NormalFrame frame = new NormalFrame();
    frame.setFirstRoll(of(numberOfPinsKnockedInFirstRoll));

    if (isSpare(numberOfPinsKnockedInFirstRoll, numberOfPinsKnockedInSecondRoll)) {
      frame.setSecondRoll(spareOf(numberOfPinsKnockedInSecondRoll));
    } else {
      frame.setSecondRoll(of(numberOfPinsKnockedInSecondRoll));
    }

    return frame;
  }

  public static Frame strike() {
    NormalFrame frame = new NormalFrame();
    frame.setFirstRoll(none());
    frame.setSecondRoll(Roll.strike());

    return frame;
  }

}
