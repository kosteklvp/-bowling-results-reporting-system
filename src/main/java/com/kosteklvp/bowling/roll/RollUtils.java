package com.kosteklvp.bowling.roll;

import static com.kosteklvp.bowling.roll.RollType.NONE;
import static com.kosteklvp.bowling.roll.RollType.SPARE;
import static com.kosteklvp.bowling.roll.RollType.STRIKE;
import static java.util.Objects.nonNull;
import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class RollUtils {

  public static boolean isSpare(Roll roll) {
    return nonNull(roll) && SPARE.equals(roll.getType());
  }

  public static boolean isStrike(Roll roll) {
    return nonNull(roll) && STRIKE.equals(roll.getType());
  }

  public static boolean isNone(Roll roll) {
    return nonNull(roll) && NONE.equals(roll.getType());
  }

  public static boolean isTenthFrame(Roll roll) {
    return nonNull(roll) && (RollType.TENTH_FRAME_SPARE.equals(roll.getType()) || RollType.TENTH_FRAME_STRIKE.equals(roll.getType()));
  }

  public static List<Roll> getTrueRolls(List<Roll> rolls) {
    return rolls.stream()
        .filter(roll -> nonNull(roll) && !RollUtils.isNone(roll))
        .toList();
  }

}
