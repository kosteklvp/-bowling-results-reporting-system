package com.kosteklvp;

import java.io.IOException;
import java.util.List;

import com.kosteklvp.converter.Game;
import com.kosteklvp.converter.PlayerPlay;
import com.kosteklvp.points.LinesToPlayerPlaysConverter;
import com.kosteklvp.reader.FileReader;

public class Application {

  public static void main(String[] args) throws IOException {
    Application.run(args[0]);
  }

  private static void run(String pathToFile) throws IOException {
    Game game = createGameFromFile(pathToFile);

//    List<Row> rows = RowCreator.instance(new RowConverter(LineToIntsConverter.instance()), lines).getAllRows();
//
//    System.out.print(new TableGenerator(rows).generate());
  }

  private static Game createGameFromFile(String pathToFile) throws IOException {
    List<String> lines = FileReader.of(pathToFile).readAllLines();

    List<PlayerPlay> playerPlays = LinesToPlayerPlaysConverter.instance().convert(lines);

    return Game.create(playerPlays);
  }

}
