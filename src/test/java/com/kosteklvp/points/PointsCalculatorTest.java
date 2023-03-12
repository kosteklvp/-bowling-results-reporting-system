package com.kosteklvp.points;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.kosteklvp.bowling.PlayerPlay;
import com.kosteklvp.calculator.ScoreCalculator;
import com.kosteklvp.converter.IntsToFramesConverter;

class PointsCalculatorTest {

  ScoreCalculator calculator = new ScoreCalculator();
  IntsToFramesConverter intsToFramesConverter = new IntsToFramesConverter();

  @ParameterizedTest
  @MethodSource("com.kosteklvp.data.DataFactory#resultsAndListsOfKnockedPinsProvider")
  void calculateResultOfPlayerPlay(int expectedResult, List<Integer> points) {
    PlayerPlay playerPlay = PlayerPlay.create("Lucas Perez", intsToFramesConverter.convert(points));

    int result = calculator.calculate(playerPlay);

    assertThat(result).isEqualTo(expectedResult);
  }

}
