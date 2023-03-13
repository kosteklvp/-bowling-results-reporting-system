package com.kosteklvp.calculator;

import com.kosteklvp.bowling.PlayerPlay;
import com.kosteklvp.bowling.roll.Roll;
import com.kosteklvp.bowling.roll.RollUtils;

public class ScoreCalculator {

  public int calculate(PlayerPlay playerPlay) {
    int score = 0;

    Roll prepreviousRoll = null;
    Roll previousRoll = null;

    for (Roll roll : playerPlay.getAllTrueRolls()) {
      if (isTriplePoints(previousRoll, prepreviousRoll)) {
        score += 3 * roll.getNumberOfKnockedPins();
      } else if (isDoublePoints(previousRoll, prepreviousRoll)) {
        score += 2 * roll.getNumberOfKnockedPins();
      } else {
        score += roll.getNumberOfKnockedPins();
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
