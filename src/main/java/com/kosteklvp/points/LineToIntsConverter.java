package com.kosteklvp.points;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kosteklvp.converter.Converter;

public class LineToIntsConverter implements Converter<String, List<Integer>> {

  @Override
  public List<Integer> convert(String line) {
    Pattern integerPattern = Pattern.compile("\\d+");
    Matcher matcher = integerPattern.matcher(line);

    List<Integer> ints = new ArrayList<>();

    while (matcher.find()) {
      ints.add(Integer.valueOf(matcher.group()));
    }

    return ints;
  }

}
