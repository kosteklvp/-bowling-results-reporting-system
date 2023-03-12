package com.kosteklvp.converter;

import static com.kosteklvp.roll.RollUtils.getTrueRolls;
import static lombok.AccessLevel.PRIVATE;

import java.util.ArrayList;
import java.util.List;

import com.kosteklvp.frame.Frame;
import com.kosteklvp.roll.Roll;

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
    List<Roll> rolls = new ArrayList<>();

    frames.forEach(frame -> {
      rolls.add(frame.getFirstRoll());
      rolls.add(frame.getSecondRoll());
      rolls.add(frame.getThirdRoll());
    });

    return getTrueRolls(rolls);
  }

  public static PlayerPlay create(String playerName, List<Frame> frames) {
    PlayerPlay playerPlay = new PlayerPlay();
    playerPlay.setPlayerName(playerName);
    playerPlay.setFrames(frames);

    return playerPlay;
  }

}
