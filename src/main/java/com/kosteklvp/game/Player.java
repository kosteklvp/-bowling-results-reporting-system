package com.kosteklvp.game;

import java.util.List;

public class Player {

  private String name;

  private List<Integer> points;

  private void setName(String name) {
    this.name = name;
  }

  public static Player create(String name, String points) {
    Player player = new Player();
    player.setName(name);

  }

}
