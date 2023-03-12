package com.kosteklvp.table.row;

import java.util.ArrayList;
import java.util.List;

import com.kosteklvp.converter.Converter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RowConverter implements Converter<List<String>, List<Row>> {

  private final Converter<String, List<Integer>> lineToPointsConverter;

  @Override
  public List<Row> convert(List<String> lines) {
    List<Row> rows = new ArrayList<>();

    for (int i = 0; i < lines.size();) {
      String playerName = lines.get(i++);
      List<Integer> points = lineToPointsConverter.convert(lines.get(i++));

      Row row = TableRow.builder()
          .playerName(playerName)
          .points(points)
          .build();

      rows.add(row);
    }

    return rows;
  }

}
