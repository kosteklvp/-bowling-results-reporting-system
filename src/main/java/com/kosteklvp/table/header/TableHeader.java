package com.kosteklvp.table.header;

import java.util.Arrays;
import java.util.List;

public enum TableHeader implements Header {
  PLAYER("Player"),
  COLUMN_1(" 1"),
  COLUMN_2(" 2"),
  COLUMN_3(" 3"),
  COLUMN_4(" 4"),
  COLUMN_5(" 5"),
  COLUMN_6(" 6"),
  COLUMN_7(" 7"),
  COLUMN_8(" 8"),
  COLUMN_9(" 9"),
  COLUMN_10("10"),
  COLUMN_11("11"),
  COLUMN_12("12"),
  COLUMN_13("13"),
  COLUMN_14("14"),
  COLUMN_15("15"),
  COLUMN_16("16"),
  COLUMN_17("17"),
  COLUMN_18("18"),
  COLUMN_19("19"),
  COLUMN_20("20"),
  COLUMN_21("21"),
  SCORE("Score");

  private final String label;

  private TableHeader(String label) {
    this.label = label;
  }

  @Override
  public String getLabel() {
    return label;
  }

  public static List<Header> getAll() {
    return Arrays.asList(TableHeader.values());
  }

}
