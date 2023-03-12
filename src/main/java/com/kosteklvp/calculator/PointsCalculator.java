package com.kosteklvp.calculator;

import com.kosteklvp.bowling.PlayerPlay;
import com.kosteklvp.bowling.roll.Roll;
import com.kosteklvp.bowling.roll.RollUtils;

public class PointsCalculator { // TODO dodac interfejs

  public int calculateResult(PlayerPlay playerPlay) {
    int result = 0;

    Roll prepreviousRoll = null;
    Roll previousRoll = null;

    for (Roll roll : playerPlay.getAllTrueRolls()) {
      if (isTriplePoints(previousRoll, prepreviousRoll)) {
        result = result + 3 * roll.getNumberOfKnockedPins();
      } else if (isDoublePoints(previousRoll, prepreviousRoll)) {
        result = result + 2 * roll.getNumberOfKnockedPins();
      } else {
        result = result + roll.getNumberOfKnockedPins();
      }

      prepreviousRoll = previousRoll;
      previousRoll = roll;
    }

    return result;
  }

  private boolean isDoublePoints(Roll previousRoll, Roll prepreviousRoll) {
    return (RollUtils.isSpare(previousRoll) || RollUtils.isStrike(previousRoll)) || RollUtils.isStrike(prepreviousRoll);
  }

  private boolean isTriplePoints(Roll previousRoll, Roll prepreviousRoll) {
    return RollUtils.isStrike(prepreviousRoll) && (RollUtils.isSpare(previousRoll) || RollUtils.isStrike(previousRoll));
  }

}
