package com.kosteklvp.table.row;

import java.util.List;

import com.kosteklvp.converter.Converter;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "instance")
public class RowCreator {

  private final Converter<List<String>, List<Row>> rowConverter;
  private final List<String> lines;

  public List<Row> getAllRows() {
    return rowConverter.convert(lines);
  }

}
