package com.kosteklvp.converter;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.kosteklvp.utils.Utils;

class PointsConverterTest {

  @Nested
  class ConvertToPoints {

    @ParameterizedTest
    @MethodSource("stringsWithoutDigitsProvider")
    void returnsEmptyListIfThereAreNoDigitsInInputString(String input) {
      var points = PointsConverter.convertToPoints(input);

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

      var points = PointsConverter.convertToPoints(input);

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

      var points = PointsConverter.convertToPoints(input);

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

  @Nested
  class ConvertToSymbols {

    @Test
    void convertsZerosToDashes() {
      List<Integer> zeros = List.of(0, 0, 0);

      var symbols = PointsConverter.convertToSymbols(zeros);

      var expectedSymbols = List.of('-', '-', '-');
      assertThat(symbols).hasSameElementsAs(expectedSymbols);
    }

    @ParameterizedTest
    @MethodSource("sparePointsProvider")
    void convertsPairsEqualToTenToDashes(List<Integer> points) {

      var symbols = PointsConverter.convertToSymbols(points);

      var expectedSymbols = List.of(Utils.toChar(points.get(0)), '/');
      assertThat(symbols).hasSameElementsAs(expectedSymbols);
    }

    static Stream<List<Integer>> sparePointsProvider() {
      return Stream.of(
          List.of(1, 9),
          List.of(2, 8),
          List.of(3, 7),
          List.of(4, 6),
          List.of(5, 5),
          List.of(6, 4),
          List.of(7, 3),
          List.of(8, 2),
          List.of(9, 1));
    }

    @ParameterizedTest
    @MethodSource("notSparePointsProvider")
    void doesNotConvertPairsEqualToTenToDashesIfTheseAreOddNumbers(List<Integer> points) {

      var symbols = PointsConverter.convertToSymbols(points);

      var expectedSymbols = List.of(Utils.toChar(points.get(0)), Utils.toChar(points.get(1)), Utils.toChar(points.get(2)));
      assertThat(symbols).hasSameElementsAs(expectedSymbols);
    }

    static Stream<List<Integer>> notSparePointsProvider() {
      return Stream.of(
          List.of(8, 1, 9),
          List.of(7, 2, 8),
          List.of(6, 3, 7),
          List.of(5, 4, 6),
          List.of(4, 5, 5),
          List.of(3, 6, 4),
          List.of(2, 7, 3),
          List.of(1, 8, 2));
    }

    @Test
    void convertsTensToXsSymbol() {
      List<Integer> tens = List.of(10, 10, 10);

      var symbols = PointsConverter.convertToSymbols(tens);

      Condition<Character> X = new Condition<>(ca -> ca == 'X', EMPTY);
      assertThat(symbols).haveAtLeast(3, X);
    }

    @Test
    void addAdditionalBlankSymbolAfterXSymbol() {
      List<Integer> tens = List.of(10, 10, 10);

      var symbols = PointsConverter.convertToSymbols(tens);

      var expectedSymbols = List.of('X', ' ', 'X', ' ', 'X', ' ');
      assertThat(symbols).hasSameElementsAs(expectedSymbols);
    }

    @ParameterizedTest
    @MethodSource("pointsAndExpectedSymbolsProvider")
    void returnsPunctuationAsAListOfChars(List<Integer> points, List<Character> expectedSymbols) {

      var symbols = PointsConverter.convertToSymbols(points);

      assertThat(symbols).hasSameElementsAs(expectedSymbols);
    }

    static Stream<Arguments> pointsAndExpectedSymbolsProvider() {
      return Stream.of(
          arguments(List.of(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
              List.of('X', ' ', 'X', ' ', 'X', ' ', 'X', ' ', 'X', ' ', 'X', ' ', 'X', ' ', 'X', ' ', 'X', ' ', 'X', ' ')),

          arguments(List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
              List.of('-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-')),

          arguments(List.of(9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0),
              List.of('9', '-', '9', '-', '9', '-', '9', '-', '9', '-', '9', '-', '9', '-', '9', '-', '9', '-', '9', '-')),

          arguments(List.of(5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5),
              List.of('5', '/', '5', '/', '5', '/', '5', '/', '5', '/', '5', '/', '5', '/', '5', '/', '5', '/', '5', '/')),

          arguments(List.of(7, 2, 9, 0, 9, 0, 9, 0, 9, 1, 9, 1, 10, 10, 8, 0, 7, 1),
              List.of('7', '2', '9', '-', '9', '-', '9', '-', '9', '/', '9', '/', 'X', ' ', 'X', ' ', '8', '-', '7', '1')),

          arguments(List.of(0, 6, 9, 1, 10, 6, 3, 7, 1, 9, 0, 5, 5, 10, 7, 3, 7, 0),
              List.of('-', '6', '9', '/', 'X', ' ', '6', '3', '7', '1', '9', '-', '5', '/', 'X', ' ', '7', '/', '7', '-')));
    }

  }

}
