package com.kosteklvp.calculator

import static org.junit.jupiter.api.Assertions.*
import static org.junit.jupiter.params.provider.Arguments.arguments

import org.junit.jupiter.api.Test

import com.kosteklvp.bowling.PlayerPlay
import com.kosteklvp.converter.IntsToFramesConverter

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class ScoreCalculatorSpec extends Specification {

  @Subject
  def calculator = new ScoreCalculator()

  IntsToFramesConverter intsToFramesConverter = new IntsToFramesConverter()

  @Unroll
  def "Calculates the final score = [#expectedResult] from a sequence of knocked pins = #sequenceOfKnockedPins"() {
    given:
    PlayerPlay playerPlay = PlayerPlay.create("Player Name", intsToFramesConverter.convert(sequenceOfKnockedPins))

    when:
    def result = calculator.calculate(playerPlay)

    then:
    result == expectedResult

    where:
    sequenceOfKnockedPins                                             || expectedResult
    [10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10]                  || 300
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]      || 0
    [9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0]      || 90
    [5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5]   || 150
    [7, 2, 9, 0, 9, 0, 9, 0, 9, 1, 9, 1, 10, 10, 8, 0, 7, 1]          || 137
    [0, 6, 9, 1, 10, 6, 3, 7, 1, 9, 0, 5, 5, 10, 7, 3, 7, 0]          || 135
    [1, 9, 0, 9, 9, 0, 6, 0, 1, 5, 7, 0, 8, 2, 7, 0, 9, 0, 10, 10, 0] || 100
    [9, 1, 9, 0, 8, 0, 0, 10, 7, 2, 0, 4, 8, 1, 0, 6, 3, 0, 8, 0]     || 92
    [3, 5, 7, 0, 3, 7, 0, 3, 3, 3, 10, 9, 1, 8, 2, 6, 2, 9, 1, 6]     || 112
    [8, 2, 10, 7, 1, 7, 1, 8, 0, 9, 0, 10, 10, 9, 1, 10, 7, 0]        || 157
    [9, 1, 8, 2, 10, 7, 3, 5, 3, 3, 4, 8, 2, 0, 7, 8, 2, 0, 10, 8]    || 133
    [10, 10, 6, 0, 10, 10, 0, 9, 7, 0, 7, 0, 9, 1, 9, 1, 7]           || 146
    [9, 1, 10, 10, 7, 3, 6, 3, 9, 1, 10, 9, 1, 9, 1, 9, 0]            || 179
    [3, 4, 9, 0, 3, 0, 0, 9, 5, 5, 10, 3, 0, 0, 1, 10, 0, 7]          || 89
    [9, 1, 4, 4, 9, 0, 9, 0, 9, 1, 9, 0, 9, 0, 9, 0, 9, 0, 10, 8, 0]  || 113
    [1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2]      || 30
    [1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 9]   || 118
    [8, 2, 5, 4, 9, 0, 10, 10, 5, 5, 5, 3, 6, 3, 9, 1, 9, 1, 10]      || 149
    [8, 0, 7, 0, 5, 3, 9, 1, 9, 1, 10, 8, 0, 5, 1, 3, 7, 9, 0]        || 122
    [8, 2, 9, 0, 4, 4, 7, 2, 9, 0, 10, 10, 8, 0, 3, 5, 9, 1, 7]       || 133
    [5, 3, 3, 3, 3, 4, 10, 10, 10, 5, 3, 3, 7, 10, 10, 4, 3]          || 163
    [7, 1, 3, 3, 4, 5, 4, 5, 10, 10, 10, 10, 5, 5, 2, 3]              || 154
    [3, 2, 3, 7, 10, 10, 10, 10, 4, 3, 3, 3, 3, 3, 3, 6]              || 154
    [4, 3, 4, 4, 5, 6, 4, 5, 10, 10, 10, 10, 4, 3, 2, 3]              || 148
    [9, 0, 0, 2, 3, 5, 10, 10, 10, 10, 6, 2, 2, 2, 6, 2]              || 143
    [5, 5, 4, 5, 8, 2, 10, 0, 10, 10, 6, 2, 10, 4, 6, 10, 10]         || 169
    [5, 5, 4, 0, 8, 1, 10, 0, 10, 10, 10, 10, 4, 6, 10, 10, 5]        || 186
    [10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 2]                   || 292
    [8, 2, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 7]                 || 287
    [9, 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 2]                 || 271
  }

}
