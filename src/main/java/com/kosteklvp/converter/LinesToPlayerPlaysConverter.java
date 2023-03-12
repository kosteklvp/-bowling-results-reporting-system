package com.kosteklvp.converter;

import static lombok.AccessLevel.PRIVATE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.kosteklvp.bowling.PlayerPlay;
import com.kosteklvp.bowling.frame.Frame;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class LinesToPlayerPlaysConverter implements Converter<List<String>, List<PlayerPlay>> {

  private final Converter<String, List<Integer>> lineToIntsConverter = new LineToIntsConverter();
  private final Converter<List<Integer>, List<Frame>> intsToFramesConverter = new IntsToFramesConverter();

  @Override
  public List<PlayerPlay> convert(List<String> lines) {
    List<PlayerPlay> playerPlays = new ArrayList<>();

    Iterator<String> iterator = lines.iterator();

    while (iterator.hasNext()) {
      String playerName = iterator.next();
      List<Integer> ints = lineToIntsConverter.convert(iterator.next());
      List<Frame> frames = intsToFramesConverter.convert(ints);

      playerPlays.add(PlayerPlay.create(playerName, frames));
    }

    return playerPlays;
  }

  public static LinesToPlayerPlaysConverter instance() {
    return new LinesToPlayerPlaysConverter();
  }

}
