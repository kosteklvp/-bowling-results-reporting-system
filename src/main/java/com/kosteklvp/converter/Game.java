package com.kosteklvp.converter;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class Game {

  List<PlayerPlay> playerPlays;

  public static Game create(List<PlayerPlay> playerPlays) {
    Game game = new Game();
    game.playerPlays = playerPlays;

    return game;
  }

}
