package com.kosteklvp.points;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PointsCalculatorTest {

  PointsCalculator calculator = new PointsCalculator();

  @ParameterizedTest
  @MethodSource("com.kosteklvp.data.DataFactory#pointsAndResultProvider")
  void calculateResultOfThrowsSequence(int expectedResult, List<Integer> points) {

    int result = calculator.calculateResult(points);

    assertThat(result).isSameAs(expectedResult);
  }

}
