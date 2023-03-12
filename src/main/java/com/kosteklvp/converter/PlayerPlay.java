package com.kosteklvp.converter;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.kosteklvp.frame.Frame;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO opisac co to jest
@NoArgsConstructor(access = PRIVATE)
@Setter(PRIVATE)
@Getter
public class PlayerPlay {

  private String playerName;

  private List<Frame> frames;

  public static PlayerPlay create(String playerName, List<Frame> frames) {
    PlayerPlay playerPlay = new PlayerPlay();
    playerPlay.setPlayerName(playerName);
    playerPlay.setFrames(frames);

    return playerPlay;
  }

}
