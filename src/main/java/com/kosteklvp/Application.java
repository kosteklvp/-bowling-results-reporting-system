package com.kosteklvp;

import java.io.IOException;
import java.util.List;

import com.kosteklvp.reader.FileReader;

public class Application {

  public static void main(String[] args) {
//    Application.run(args[0]);
  }

  private static void run(String pathToFile) throws IOException {

    List<String> lines = FileReader.of(pathToFile).readAllLines();

//    TableGenerator.builder()
//        .headings(TableHeading)
  }

}
