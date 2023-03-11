package com.kosteklvp.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PointsConverter {

  public List<Integer> convert(String pointsString) {
    Pattern integerPattern = Pattern.compile("\\d+");
    Matcher matcher = integerPattern.matcher(pointsString);

    List<Integer> points = new ArrayList<Integer>();
    while (matcher.find()) {
      points.add(Integer.valueOf(matcher.group()));
    }

    return points;
  }

}
