package com.kosteklvp.converter;

import static com.kosteklvp.bowling.BowlingUtils.MAX_NUMBER_OF_THROWS;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kosteklvp.bowling.BowlingUtils.Punctation;

public class PointsConverter {

  public static List<Integer> convertToPoints(String pointsString) {
    Pattern integerPattern = Pattern.compile("\\d+");
    Matcher matcher = integerPattern.matcher(pointsString);

    List<Integer> points = new ArrayList<Integer>();
    while (matcher.find()) {
      points.add(Integer.valueOf(matcher.group()));
    }

    return points;
  }

  public static List<Character> convertToSymbols(List<Integer> points) {
    List<Character> symbols = new ArrayList<>(MAX_NUMBER_OF_THROWS);

    Integer previousPoint = 0;

    for (Integer point : points) {
      if (point == 0) {
        symbols.add(Punctation.ZERO.getSymbol());
      } else if (point == 10) {
        symbols.add(Punctation.STRIKE.getSymbol());
        symbols.add(Punctation.NONE.getSymbol());
      } else if (previousPoint + point == 10) {
        symbols.add(Punctation.SPARE.getSymbol());
        previousPoint = 0;
      } else {
        symbols.add(Integer.toString(point).charAt(0)); // TODO do metody
      }

      previousPoint = point;
    }

    return symbols;
  }

}
