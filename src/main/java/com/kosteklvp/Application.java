package com.kosteklvp;

import java.io.IOException;
import java.util.List;

import com.kosteklvp.points.LineToPointsConverter;
import com.kosteklvp.reader.FileReader;
import com.kosteklvp.table.TableGenerator;
import com.kosteklvp.table.row.Row;
import com.kosteklvp.table.row.RowConverter;
import com.kosteklvp.table.row.RowCreator;

public class Application {

  public static void main(String[] args) {
    try {
      Application.run(args[0]);

    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  private static void run(String pathToFile) throws IOException {
    List<String> lines = FileReader.of(pathToFile).readAllLines();

    List<Row> rows = RowCreator.instance(new RowConverter(LineToPointsConverter.instance()), lines).getAllRows();

    System.out.print(new TableGenerator(rows).generate());
  }

}
