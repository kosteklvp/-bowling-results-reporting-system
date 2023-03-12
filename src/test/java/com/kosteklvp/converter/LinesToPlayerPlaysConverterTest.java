package com.kosteklvp.converter;

import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.kosteklvp.bowling.PlayerPlay;
import com.kosteklvp.data.DataFactory;

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

}
