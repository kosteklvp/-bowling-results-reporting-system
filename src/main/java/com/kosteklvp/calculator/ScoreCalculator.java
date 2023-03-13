package com.kosteklvp.calculator;

import com.kosteklvp.bowling.PlayerPlay;
import com.kosteklvp.bowling.roll.Roll;
import com.kosteklvp.bowling.roll.RollUtils;
import com.kosteklvp.utils.Utils;

public class ScoreCalculator {

  public int calculate(PlayerPlay playerPlay) {
    int score = 0;

    Roll prepreviousRoll = null;
    Roll previousRoll = null;

    for (Roll roll : playerPlay.getAllTrueRolls()) {
      int numberOfKnockedPins = Utils.nn(roll.getNumberOfKnockedPins());

      if (isTriplePoints(previousRoll, prepreviousRoll)) {
        score += 3 * numberOfKnockedPins;
      } else if (isDoublePoints(previousRoll, prepreviousRoll)) {
        score += 2 * numberOfKnockedPins;
      } else {
        score += numberOfKnockedPins;
      }

      prepreviousRoll = previousRoll;
      previousRoll = roll;
    }

    return score;
  }

  private boolean isDoublePoints(Roll previousRoll, Roll prepreviousRoll) {
    return RollUtils.isSpareOrStrike(previousRoll) || RollUtils.isStrike(prepreviousRoll);
  }

  private boolean isTriplePoints(Roll previousRoll, Roll prepreviousRoll) {
    return RollUtils.isStrike(prepreviousRoll) && RollUtils.isSpareOrStrike(previousRoll);
  }

}
