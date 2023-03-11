package com.kosteklvp.table;

import java.util.List;

import com.google.common.base.CharMatcher;

public class TableRow {

  private String playerName;

  private List<Integer> points;

  private void setPoints(String pointsString) {
    CharMatcher.
  }

  public static TableRow getInstance(String playerName, String pointsString) {
    TableRow tableRow = new TableRow();

  }

}
