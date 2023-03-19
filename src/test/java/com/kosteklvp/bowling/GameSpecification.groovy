package com.kosteklvp.bowling

import static org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

import com.kosteklvp.bowling.frame.NormalFrame
import com.kosteklvp.bowling.frame.TenthFrame

import spock.lang.Specification

class GameSpecification extends Specification {

  def "Sets 'PlayerPlays' on creation"() {
    given: "example 'PlayerPlays'"
    final def PLAYER_PLAYS = [
      PlayerPlay.create("Johnny", [NormalFrame.create(0, 1), NormalFrame.strike(), TenthFrame.create(3, 5, null)]),
      PlayerPlay.create("Otto", [NormalFrame.create(4, 6), NormalFrame.strike(), TenthFrame.create(3, 5, null)]),
      PlayerPlay.create("Raul", [NormalFrame.create(0, 0), NormalFrame.create(0, 10), TenthFrame.create(2, 3, null)]),
      PlayerPlay.create("Jimenez", [NormalFrame.create(2, 4), NormalFrame.create(0, 0), TenthFrame.create(5, 5, 0)]),
      PlayerPlay.create("Sa", [NormalFrame.create(9, 0), TenthFrame.create(10, 5, 10)])
    ]

    when: "creating 'Game'"
    def game = Game.create(PLAYER_PLAYS)

    then: "'PlayerPlays' are properly set"
    game.playerPlays == PLAYER_PLAYS
  }

}
