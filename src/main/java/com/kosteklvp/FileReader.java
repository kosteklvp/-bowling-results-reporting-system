package com.kosteklvp;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {

  private final String pathToFile;

  private FileReader(String path) {
    this.pathToFile = path;
  }

  public List<String> readAllLines() {
    List<String> lines = Files.readAllLines(Paths.get(pathToFile), encoding);
  }

  public static FileReader of(String path) {
    FileReader fileReader = new FileReader(path);

    return fileReader;
  }

}
