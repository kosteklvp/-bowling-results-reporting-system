package com.kosteklvp.converter;

import java.util.ArrayList;
import java.util.List;

import com.kosteklvp.bowling.BowlingUtils;
import com.kosteklvp.bowling.BowlingUtils.PointsType;
import com.kosteklvp.utils.Utils;

public class PointsToSymbolsConverter implements Converter<List<Integer>, List<Character>> {

  @Override
  public List<Character> convert(List<Integer> points) {
    List<Character> symbols = new ArrayList<>(BowlingUtils.MAX_NUMBER_OF_ROLLS);

    Integer previousPoint = 0;

    for (Integer point : points) {
      if (point == 0) {
        symbols.add(PointsType.ZERO.getSymbol());
      } else if (point == 10) {
        symbols.add(PointsType.NONE.getSymbol());
        symbols.add(PointsType.STRIKE.getSymbol());
      } else if (previousPoint + point == 10 && symbols.size() % 2 != 0) {
        symbols.add(PointsType.SPARE.getSymbol());
        previousPoint = 0;
      } else {
        symbols.add(Utils.toChar(point));
      }

      previousPoint = point;
    }

    while (symbols.size() < BowlingUtils.MAX_NUMBER_OF_ROLLS) {
      symbols.add(' ');
    }

    return symbols;
  }

  // test czy dodaj ostatnią spację

}
