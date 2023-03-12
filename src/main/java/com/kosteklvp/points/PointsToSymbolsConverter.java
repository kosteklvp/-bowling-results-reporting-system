package com.kosteklvp.points;

import java.util.ArrayList;
import java.util.List;

import com.kosteklvp.bowling.BowlingUtils;
import com.kosteklvp.bowling.BowlingUtils.Punctation;
import com.kosteklvp.converter.Converter;
import com.kosteklvp.utils.Utils;

public class PointsToSymbolsConverter implements Converter<List<Integer>, List<Character>> {

  @Override
  public List<Character> convert(List<Integer> points) {
    List<Character> symbols = new ArrayList<>(BowlingUtils.MAX_NUMBER_OF_THROWS);

    Integer previousPoint = 0;

    for (Integer point : points) {
      if (point == 0) {
        symbols.add(Punctation.ZERO.getSymbol());
      } else if (point == 10) {
        symbols.add(Punctation.NONE.getSymbol());
        symbols.add(Punctation.STRIKE.getSymbol());
      } else if (previousPoint + point == 10 && symbols.size() % 2 != 0) {
        symbols.add(Punctation.SPARE.getSymbol());
        previousPoint = 0;
      } else {
        symbols.add(Utils.toChar(point));
      }

      previousPoint = point;
    }

    while (symbols.size() < BowlingUtils.MAX_NUMBER_OF_THROWS) {
      symbols.add(' ');
    }

    return symbols;
  }

  // test czy dodaj ostatnią spację

}
