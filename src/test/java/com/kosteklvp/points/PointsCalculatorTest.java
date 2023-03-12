package com.kosteklvp.points;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.kosteklvp.converter.PlayerPlay;

class PointsCalculatorTest {

  PointsCalculator calculator = new PointsCalculator();
  IntsToFramesConverter intsToFramesConverter = new IntsToFramesConverter();

  @ParameterizedTest
  @MethodSource("com.kosteklvp.data.DataFactory#pointsAndResultProvider")
  void calculateResultOfPlayerPlay(int expectedResult, List<Integer> points) {
    PlayerPlay playerPlay = PlayerPlay.create("Lucas Perez", intsToFramesConverter.convert(points));

    int result = calculator.calculateResult(playerPlay);

    assertThat(result).isEqualTo(expectedResult);
  }

}
