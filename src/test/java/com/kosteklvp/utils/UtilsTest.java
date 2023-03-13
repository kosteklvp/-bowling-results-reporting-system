package com.kosteklvp.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class UtilsTest {

  @Nested
  class ToCharTest {

    @ParameterizedTest
    @MethodSource("com.kosteklvp.data.DataFactory#singleDigitIntegersProvider")
    void returnsGivenIntegerAsAChar(int input) {
      char character = Utils.toChar(input);

      assertThat(character).isEqualTo(String.valueOf(input).charAt(0));
    }

    @ParameterizedTest
    @MethodSource("com.kosteklvp.data.DataFactory#multiDigitIntegersProvider")
    void throwsIncorrectValueExceptionWhenIntegerIsMultiDigit(int input) {
      Assertions.assertThrows(IncorrectValueException.class,
          () -> Utils.toChar(input));
    }

  }

  @Nested
  class NonNullTest {

    @Test
    void returnsGivenIntegerAsPrimitiveIfItIsNotNull() {
      Integer integer = Integer.valueOf(123);

      int returnedInteger = Utils.nn(integer);

      assertThat(returnedInteger).isEqualTo(integer);
    }

    @Test
    void returnZeroIfItIsNull() {
      Integer integer = null;

      int returnedInteger = Utils.nn(integer);

      assertThat(returnedInteger).isZero();
    }

  }

}
