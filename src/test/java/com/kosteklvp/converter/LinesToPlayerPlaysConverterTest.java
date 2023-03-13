package com.kosteklvp.converter;

import static com.kosteklvp.bowling.BowlingUtils.MAX_NUMBER_OF_ROLLS;
import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.kosteklvp.bowling.BowlingUtils;
import com.kosteklvp.bowling.PlayerPlay;
import com.kosteklvp.data.DataFactory;
import com.kosteklvp.exception.IncorrectNumberInSequenceException;
import com.kosteklvp.exception.IncorrectNumberOfNumbersException;

class LinesToPlayerPlaysConverterTest {

  private final Converter<List<String>, List<PlayerPlay>> converter = LinesToPlayerPlaysConverter.instance();

  @Test
  void setsPlayersNames() {
    var playerPlays = converter.convert(DataFactory.linesProvider().toList());

    assertThat(playerPlays)
        .isNotEmpty()
        .allMatch(playerPlay -> nonNull(playerPlay.getPlayerName()) && !playerPlay.getPlayerName().isBlank());
  }

  @Test
  void setsListOfFrames() {
    var playerPlays = converter.convert(DataFactory.linesProvider().toList());

    assertThat(playerPlays)
        .isNotEmpty()
        .allMatch(playerPlay -> nonNull(playerPlay.getFrames()) && !playerPlay.getFrames().isEmpty());
  }

  @Test
  void throwsRuntimeExceptionIfThereIsTooManyNumbersInASequence() {
    List<String> tooManyNumbersInSequence = List.of("Jacek Pantera", StringUtils.repeat("2, ", MAX_NUMBER_OF_ROLLS + 1));

    Assertions.assertThrows(IncorrectNumberOfNumbersException.class,
        () -> converter.convert(tooManyNumbersInSequence));
  }

  @Test
  void throwsRuntimeExceptionIfThereIsTooLittleNumbersInASequence() {
    List<String> tooManyNumbersInSequence = List.of("Jacek Pantera", StringUtils.repeat("2, ", BowlingUtils.MIN_NUMBER_OF_ROLLS - 1));

    Assertions.assertThrows(IncorrectNumberOfNumbersException.class,
        () -> converter.convert(tooManyNumbersInSequence));
  }

  @Test
  void throwsRuntimeExceptionIfThereIsWrongNumberInASequence() {
    List<String> incorrectNumberInSequence = List.of("Jacek Pantera", "11, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1");

    Assertions.assertThrows(IncorrectNumberInSequenceException.class,
        () -> converter.convert(incorrectNumberInSequence));
  }

}
