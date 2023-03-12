package com.kosteklvp.points;

import static com.kosteklvp.bowling.BowlingUtils.isBonus;
import static com.kosteklvp.bowling.BowlingUtils.isSpare;
import static com.kosteklvp.bowling.BowlingUtils.isStrike;

import java.util.ArrayList;
import java.util.List;

import com.kosteklvp.bowling.BowlingUtils;
import com.kosteklvp.bowling.BowlingUtils.Punctation;

public class PointsCalculator {

  public int calculateResult(List<Integer> points) {
    int result = 0;
    List<Character> symbols = new ArrayList<>(BowlingUtils.MAX_NUMBER_OF_THROWS);

    Integer previousPoint = 0;
    Punctation prepreviousPunctuation = null;
    Punctation previousPunctuation = null;

    for (Integer point : points) {
      Punctation thisPunctuation = Punctation.NONE;

      if (isStrike(point)) {
        thisPunctuation = Punctation.STRIKE;
      } else if (isSpare(previousPoint + point, symbols.size() % 2 != 0)) {
        thisPunctuation = Punctation.SPARE;
        previousPoint = 0;
      } else {
        thisPunctuation = Punctation.NONE;
      }

      result += point;

      if (isBonus(previousPunctuation, prepreviousPunctuation)) {
        result += point;
      }

      prepreviousPunctuation = previousPunctuation;
      previousPunctuation = thisPunctuation;
      previousPoint = point;
    }

    return result;
  }

}
