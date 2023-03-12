package com.kosteklvp.bowling;

import static lombok.AccessLevel.PRIVATE;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Iterables;
import com.kosteklvp.bowling.frame.Frame;
import com.kosteklvp.bowling.roll.Roll;
import com.kosteklvp.bowling.roll.RollUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = PRIVATE)
@Setter(PRIVATE)
@Getter
public class PlayerPlay {

  private String playerName;
  private List<Frame> frames;

  public List<Roll> getAllTrueRolls() {
    return RollUtils.getTrueRolls(getAllRolls());
  }

  public List<Roll> getAllRolls() {
    List<Roll> rolls = new ArrayList<>();

    frames.forEach(frame -> {
      rolls.add(frame.getFirstRoll());
      rolls.add(frame.getSecondRoll());
    });

    rolls.add(Iterables.getLast(frames).getThirdRoll());

    return rolls;
  }

  public static PlayerPlay create(String playerName, List<Frame> frames) {
    PlayerPlay playerPlay = new PlayerPlay();
    playerPlay.setPlayerName(playerName);
    playerPlay.setFrames(frames);

    return playerPlay;
  }

}
