package com.kosteklvp.converter;

import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Game {

  List<PlayerPlay> playerPlays;

  public static Game create(List<PlayerPlay> playerPlays) {
    Game game = new Game();
    game.playerPlays = playerPlays;

    return game;
  }

}
