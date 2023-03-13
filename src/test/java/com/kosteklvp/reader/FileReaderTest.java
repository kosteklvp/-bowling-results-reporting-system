package com.kosteklvp.reader;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import com.kosteklvp.data.DataFactory.ExampleFile;

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

}
