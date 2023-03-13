package com.kosteklvp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.kosteklvp.bowling.Game;
import com.kosteklvp.bowling.PlayerPlay;
import com.kosteklvp.converter.LinesToPlayerPlaysConverter;
import com.kosteklvp.reader.FileReader;
import com.kosteklvp.table.TableCreator;

public class Application {

  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      throw new FileNotFoundException("Path to file needs to be added as a parameter.");
    }

    Application.run(args[0]);
  }

  private static void run(String pathToFile) throws IOException {
    Game game = createGameFromFile(pathToFile);

    printTableOf(game);
  }

  private static Game createGameFromFile(String pathToFile) throws IOException {
    List<String> lines = FileReader.of(pathToFile).readAllLines();

    List<PlayerPlay> playerPlays = LinesToPlayerPlaysConverter.instance().convert(lines);

    return Game.create(playerPlays);
  }

  private static void printTableOf(Game game) {
    System.out.print(TableCreator.of(game).create());
  }

}
