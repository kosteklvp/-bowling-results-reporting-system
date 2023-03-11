package com.kosteklvp.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PointsConverterTest {

  PointsConverter converter = new PointsConverter();

  @ParameterizedTest
  @MethodSource("stringsWithoutDigitsProvider")
  void returnsEmptyListIfThereAreNoDigitsInInputString(String input) {
    var points = converter.convert(input);

    assertThat(points).isEmpty();
  }

  static Stream<String> stringsWithoutDigitsProvider() {
    return Stream.of(
        ",,,,,,,,,,,,,,,,,,",
        "",
        "   ",
        " ",
        "one,one,one,one,two,ten",
        " - ",
        " | ",
        ",");
  }

  @ParameterizedTest
  @MethodSource("stringsWithNegativeDigitsProvider")
  void returnPositiveDigitsFromAString(String input) {

    var points = converter.convert(input);

    assertThat(points)
        .isNotEmpty()
        .allMatch(d -> d >= 0);
  }

  static Stream<String> stringsWithNegativeDigitsProvider() {
    return Stream.of(
        "1, -2, 1, 2, -1, 2, -1, 2, -1, -2, -1, 2, 1, 2, 1, 2, 1, 2, 1, 2",
        "9, -1, 9, -1, 9, -1, 9, -1, 9, -1, 9, -1, 9, -1, 9, -1, 9, -1, 9, -1, 9",
        "-4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4",
        "-0, -1, -2, -3, -4, -5, -6, -4, -7, -3, -8, -2, -9, -1, -10",
        "-0, -1, -2, -3, -4, -5, -6, -4, -7, -3, -8, -2, -9, 1, 10, 0, 0, 1, 2, 3");
  }

  @ParameterizedTest
  @MethodSource("stringInputAndResultProvider")
  void returnsListOfSeparatedDigitsFromAString(String input, List<Integer> expectedResult) {

    var points = converter.convert(input);

    assertThat(points).hasSameElementsAs(expectedResult);
  }

  static Stream<Arguments> stringInputAndResultProvider() {
    return Stream.of(
        arguments("1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2",
            List.of(1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2)),
        arguments("9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9",
            List.of(9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9)),
        arguments("4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4",
            List.of(4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4)),
        arguments("0, 1, 2, 3, 4, 5, 6, 4, 7, 3, 8, 2, 9, 1, 10, 0, 0, 1, 2, 3",
            List.of(0, 1, 2, 3, 4, 5, 6, 4, 7, 3, 8, 2, 9, 1, 10, 0, 0, 1, 2, 3)),
        arguments("-0, -1, -2, -3, -4, -5, -6, -4, -7, -3, -8, -2, -9, -1, -10",
            List.of(0, 1, 2, 3, 4, 5, 6, 4, 7, 3, 8, 2, 9, 1, 10)),
        arguments("4 , 4 - 4 | 4 ? 4 / 4 . 4 : 4 ; 4 ' 4  4 - 4 _ 4 + 4 x 4 * 4 ( 4 ) 4 > 4 < 4",
            List.of(4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4)),
        arguments("9, one, 19, two, 29, three, 39, four, 49, a, b, c, 59break69, wet, 79, big, 89, spurs, 99",
            List.of(9, 19, 29, 39, 49, 59, 69, 79, 89, 99)));
  }

}
