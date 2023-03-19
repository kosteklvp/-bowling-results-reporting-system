package com.kosteklvp.bowling

import static org.assertj.core.api.Assertions.assertThat

import com.kosteklvp.bowling.frame.NormalFrame
import com.kosteklvp.bowling.frame.TenthFrame
import com.kosteklvp.bowling.roll.Roll

import spock.lang.Specification
import spock.lang.Unroll

class PlayerPlaySpecification extends Specification {

  def "Sets 'Player Name' on creation"() {
    given: "example 'PlayerName'"
    final def PLAYER_NAME = "Jackson"

    when: "creating 'PlayerPlay'"
    def playerPlay = PlayerPlay.create(PLAYER_NAME, null)

    then: "'PlayerName' is properly set"
    playerPlay.playerName == PLAYER_NAME
  }

  def "Sets 'Frames' on creation"() {
    given: "example 'Frames'"
    final def FRAMES = [
      NormalFrame.strike(),
      NormalFrame.create(0, 4)
    ]

    when: "creating 'PlayerPlay'"
    def playerPlay = PlayerPlay.create("Martinez", FRAMES)

    then: "'Frames' are properly set"
    playerPlay.frames == FRAMES
  }

  @Unroll
  def "Can return all the player's rolls without empty rolls"() {
    given: "example 'PlayerPlay'"
    def playerPlay = PlayerPlay.create("Hugo", frames)

    when: "getting all the player's rolls without empty rolls"
    def allTrueRolls = playerPlay.getAllTrueRolls()

    then: "returns all the player's rolls without empty rolls"
    allTrueRolls == expectedRolls

    where:
    frames                                                                               || expectedRolls
    [NormalFrame.create(0, 1), NormalFrame.strike(), TenthFrame.create(3, 5, null)]      || [Roll.of(0), Roll.of(1), Roll.strike(), Roll.of(3), Roll.of(5)]
    [NormalFrame.create(4, 6), NormalFrame.strike(), TenthFrame.create(3, 5, null)]      || [Roll.of(4), Roll.spareOf(6), Roll.strike(), Roll.of(3), Roll.of(5)]
    [NormalFrame.create(0, 0), NormalFrame.create(0, 10), TenthFrame.create(2, 3, null)] || [Roll.of(0), Roll.of(0), Roll.of(0), Roll.spareOf(10), Roll.of(2), Roll.of(3)]
    [NormalFrame.create(2, 4), NormalFrame.create(0, 0), TenthFrame.create(5, 5, 0)]     || [Roll.of(2), Roll.of(4), Roll.of(0), Roll.of(0), Roll.of(5), Roll.tenthFrameSpareOf(5), Roll.of(0)]
    [NormalFrame.create(9, 0), TenthFrame.create(10, 5, 10)]                             || [Roll.of(9), Roll.of(0), Roll.tenthFrameStrike(), Roll.of(5), Roll.tenthFrameStrike()]
  }

  @Unroll
  def "Can return all the player's rolls as a single-character symbol"() {
    given: "example 'PlayerPlay'"
    def playerPlay = PlayerPlay.create("Frodo", frames)

    when: "getting all the player's rolls without empty rolls"
    def allSymbols = playerPlay.getAllSymbols()

    then: "returns all the player's rolls without empty rolls"
    allSymbols == expectedSymbols

    where:
    frames                                                                               || expectedSymbols
    [NormalFrame.create(0, 1), NormalFrame.strike(), TenthFrame.create(3, 5, null)]      || ['0', '1', ' ', 'X', '3', '5', ' ']
    [NormalFrame.create(4, 6), NormalFrame.strike(), TenthFrame.create(3, 5, null)]      || ['4', '/', ' ', 'X', '3', '5', ' ']
    [NormalFrame.create(0, 0), NormalFrame.create(0, 10), TenthFrame.create(2, 3, null)] || ['0', '0', '0', '/', '2', '3', ' ']
    [NormalFrame.create(2, 4), NormalFrame.create(0, 0), TenthFrame.create(5, 5, 0)]     || ['2', '4', '0', '0', '5', '/', '0']
    [NormalFrame.create(9, 0), TenthFrame.create(10, 5, 10)]                             || ['9', '0', 'X', '5', 'X']
  }
}
