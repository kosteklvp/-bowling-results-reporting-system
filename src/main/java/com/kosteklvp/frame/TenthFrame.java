package com.kosteklvp.frame;

import static com.kosteklvp.bowling.BowlingUtils.isSpare;
import static com.kosteklvp.bowling.BowlingUtils.isStrike;
import static com.kosteklvp.roll.Roll.of;
import static com.kosteklvp.roll.Roll.tenthFrameSpareOf;
import static com.kosteklvp.roll.Roll.tenthFrameStrike;
import static java.util.Objects.nonNull;
import static lombok.AccessLevel.PRIVATE;

import com.kosteklvp.roll.Roll;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = PRIVATE)
@Setter(PRIVATE)
public class TenthFrame implements Frame {

  private Roll firstRoll;
  private Roll secondRoll;
  private Roll thirdRoll;

  @Override
  public Roll getFirstRoll() {
    return firstRoll;
  }

  @Override
  public Roll getSecondRoll() {
    return secondRoll;
  }

  @Override
  public Roll getThirdRoll() {
    return thirdRoll;
  }

  public static Frame create(Integer numberOfPinsKnockedInFirstRoll,
      Integer numberOfPinsKnockedInSecondRoll, Integer numberOfPinsKnockedInThirdRoll) {
    TenthFrame frame = new TenthFrame();

    if (isStrike(numberOfPinsKnockedInFirstRoll)) {
      frame.setFirstRoll(tenthFrameStrike());
    } else {
      frame.setFirstRoll(of(numberOfPinsKnockedInFirstRoll));
    }

    if (isSpare(numberOfPinsKnockedInFirstRoll, numberOfPinsKnockedInSecondRoll)) {
      frame.setSecondRoll(tenthFrameSpareOf(numberOfPinsKnockedInSecondRoll));
    } else {
      if (isStrike(numberOfPinsKnockedInSecondRoll)) {
        frame.setSecondRoll(tenthFrameStrike());
      } else {
        frame.setSecondRoll(of(numberOfPinsKnockedInSecondRoll));
      }
    }

    if (nonNull(numberOfPinsKnockedInThirdRoll)) {
      if (isSpare(numberOfPinsKnockedInSecondRoll, numberOfPinsKnockedInThirdRoll)) {
        frame.setThirdRoll(tenthFrameSpareOf(numberOfPinsKnockedInThirdRoll));
      } else {
        if (isStrike(numberOfPinsKnockedInThirdRoll)) {
          frame.setThirdRoll(tenthFrameStrike());
        } else {
          frame.setThirdRoll(of(numberOfPinsKnockedInThirdRoll));
        }
      }
    }

    return frame;
  }

}
