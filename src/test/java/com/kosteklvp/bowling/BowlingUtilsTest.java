package com.kosteklvp.bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class BowlingUtilsTest {

  @Nested
  class IsStrikeTest {

    @Test
    @DisplayName("Returns 'true' if 'numberOfPinsKnockedInRoll' is 10")
    void returnsTrueIfNumberOfPinsKnockedInRollIsTen() {
      var returnedValue = BowlingUtils.isStrike(10);

      assertThat(returnedValue).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Returns 'false' if 'numberOfPinsKnockedInRoll' is not 10")
    @ValueSource(ints = { -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12 })
    void throwsIncorrectValueExceptionWhenIntegerIsMultiDigit(int numberOfPinsKnockedInRoll) {
      var returnedValue = BowlingUtils.isStrike(numberOfPinsKnockedInRoll);

      assertThat(returnedValue).isFalse();
    }

  }

  @Nested
  class IsSpareTest {

    @ParameterizedTest(name = "[{index}] Returns 'true' if sum of ''{0}'' and ''{1}'' is 10")
    @DisplayName("Returns 'true' if sum of 'numberOfPinsKnockedInFirstRoll' and 'numberOfPinsKnockedInSecondRoll' is 10")
    @MethodSource("sparesSequenceProvider")
    void returnsTrueIfSumOfNumbersIsTen(int numberOfPinsKnockedInFirstRoll, int numberOfPinsKnockedInSecondRoll) {
      var returnedValue = BowlingUtils.isSpare(numberOfPinsKnockedInFirstRoll, numberOfPinsKnockedInSecondRoll);

      assertThat(returnedValue).isTrue();
    }

    private static Stream<Arguments> sparesSequenceProvider() {
      return Stream.of(
          arguments(0, 10),
          arguments(1, 9),
          arguments(2, 8),
          arguments(3, 7),
          arguments(4, 6),
          arguments(5, 5),
          arguments(6, 4),
          arguments(7, 3),
          arguments(8, 2),
          arguments(9, 1));
    }

    @Test
    @DisplayName("Returns 'true' if 'numberOfPinsKnockedInFirstRoll' is 10")
    void returnsFalseIfNumberOfPinsKnockedInFirstRollIsTen() {
      var returnedValue = BowlingUtils.isSpare(10, 0);

      assertThat(returnedValue).isFalse();
    }

    @ParameterizedTest(name = "[{index}] Returns 'false' if sum of ''{0}'' and ''{1}'' is not 10")
    @DisplayName("Returns 'false' if sum of 'numberOfPinsKnockedInFirstRoll' and 'numberOfPinsKnockedInSecondRoll' is not 10")
    @MethodSource("notSparesSequenceProvider")
    void returnsFalseIfSumOfNumbersIsNotTen(int numberOfPinsKnockedInFirstRoll, int numberOfPinsKnockedInSecondRoll) {
      var returnedValue = BowlingUtils.isSpare(numberOfPinsKnockedInFirstRoll, numberOfPinsKnockedInSecondRoll);

      assertThat(returnedValue).isFalse();
    }

    private static Stream<Arguments> notSparesSequenceProvider() {
      return Stream.of(
          arguments(0, 0),
          arguments(0, 1),
          arguments(0, 2),
          arguments(0, 3),
          arguments(0, 4),
          arguments(0, 5),
          arguments(0, 6),
          arguments(0, 7),
          arguments(0, 8),
          arguments(0, 9),
          arguments(1, 0),
          arguments(1, 1),
          arguments(1, 2),
          arguments(1, 3),
          arguments(1, 4),
          arguments(1, 5),
          arguments(1, 6),
          arguments(1, 7),
          arguments(1, 8),
          arguments(2, 0),
          arguments(2, 1),
          arguments(2, 2),
          arguments(2, 3),
          arguments(2, 4),
          arguments(2, 5),
          arguments(2, 6),
          arguments(2, 7),
          arguments(3, 0),
          arguments(3, 1),
          arguments(3, 2),
          arguments(3, 3),
          arguments(3, 4),
          arguments(3, 5),
          arguments(3, 6),
          arguments(4, 0),
          arguments(4, 1),
          arguments(4, 2),
          arguments(4, 3),
          arguments(4, 4),
          arguments(4, 5),
          arguments(5, 0),
          arguments(5, 1),
          arguments(5, 2),
          arguments(5, 3),
          arguments(5, 4),
          arguments(6, 0),
          arguments(6, 1),
          arguments(6, 2),
          arguments(6, 3),
          arguments(7, 0),
          arguments(7, 1),
          arguments(7, 2),
          arguments(8, 0),
          arguments(8, 1),
          arguments(9, 0));
    }

  }

}
