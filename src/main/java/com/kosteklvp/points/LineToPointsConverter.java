package com.kosteklvp.points;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kosteklvp.converter.Converter;

public class LineToPointsConverter implements Converter<String, List<Integer>> {

  private static final LineToPointsConverter INSTANCE = new LineToPointsConverter();

  @Override
  public List<Integer> convert(String line) {
    Pattern integerPattern = Pattern.compile("\\d+");
    Matcher matcher = integerPattern.matcher(line);

    List<Integer> points = new ArrayList<>();
    while (matcher.find()) {
      points.add(Integer.valueOf(matcher.group()));
    }

    return points;
  }

  public static LineToPointsConverter instance() {
    return INSTANCE;
  }

}
