package com.kosteklvp.calculator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.kosteklvp.bowling.PlayerPlay;
import com.kosteklvp.converter.IntsToFramesConverter;

class ScoreCalculatorTest {

  ScoreCalculator calculator = new ScoreCalculator();
  IntsToFramesConverter intsToFramesConverter = new IntsToFramesConverter();

  @ParameterizedTest
  @MethodSource("com.kosteklvp.data.DataFactory#resultsAndListsOfKnockedPinsProvider")
  void calculateScoreOfPlayerPlay(int expectedResult, List<Integer> points) {
    PlayerPlay playerPlay = PlayerPlay.create("Lucas Perez", intsToFramesConverter.convert(points));

    int result = calculator.calculate(playerPlay);

    assertThat(result).isEqualTo(expectedResult);
  }

}
