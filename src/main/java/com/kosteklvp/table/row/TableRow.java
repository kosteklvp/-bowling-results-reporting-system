package com.kosteklvp.table.row;

import java.util.List;

import lombok.Builder;

@Builder
public class TableRow implements Row {

  private String playerName;

  private List<Integer> points;

  private Integer result;

  @Override
  public String getPlayerName() {
    return playerName;
  }

//  public List<String> get

//  public List<String>


}
