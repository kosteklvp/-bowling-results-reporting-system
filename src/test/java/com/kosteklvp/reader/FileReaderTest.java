package com.kosteklvp.reader;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class FileReaderTest {

  @Nested
  class ReadAllLines {

    @ParameterizedTest
    @EnumSource(ExampleFile.class)
    void doesNotReturnBlankLines(ExampleFile file) throws IOException {
      FileReader reader = FileReader.of(file.getPath());

      List<String> lines = reader.readAllLines();

      assertThat(lines).noneMatch(StringUtils::isBlank);
    }

  }

  // brak plikue exception

  private enum ExampleFile {
    EXAMPLE_1("src/test/resources/examples/example1.txt"),
    EXAMPLE_2("src/test/resources/examples/example2.txt"),
    EXAMPLE_3("src/test/resources/examples/example3.txt"),
    EXAMPLE_4("src/test/resources/examples/example4.txt"),
    EXAMPLE_5("src/test/resources/examples/example5.txt"),
    EXAMPLE_6("src/test/resources/examples/example6.txt");

    private final String path;

    ExampleFile(String path) {
      this.path = path;
    }

    private String getPath() {
      return path;
    }
  }

}
