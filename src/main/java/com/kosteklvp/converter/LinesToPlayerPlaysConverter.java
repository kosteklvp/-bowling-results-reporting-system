package com.kosteklvp.converter;

import static com.kosteklvp.bowling.BowlingUtils.MAX_NUMBER_OF_KNOCKED_PINS;
import static com.kosteklvp.bowling.BowlingUtils.MAX_NUMBER_OF_ROLLS;
import static com.kosteklvp.bowling.BowlingUtils.MIN_NUMBER_OF_KNOCKED_PINS;
import static com.kosteklvp.bowling.BowlingUtils.MIN_NUMBER_OF_ROLLS;
import static lombok.AccessLevel.PRIVATE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.kosteklvp.bowling.PlayerPlay;
import com.kosteklvp.bowling.frame.Frame;
import com.kosteklvp.exception.IncorrectNumberInSequenceException;
import com.kosteklvp.exception.IncorrectNumberOfNumbersException;

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

      verifyPlayerSequence(playerName, ints);

      List<Frame> frames = intsToFramesConverter.convert(ints);

      playerPlays.add(PlayerPlay.create(playerName, frames));
    }

    return playerPlays;
  }

  private void verifyPlayerSequence(String playerName, List<Integer> ints) {
    verifyNumberOfNumbers(playerName, ints);
    verifyNumbersInSequence(playerName, ints);
  }

  private void verifyNumberOfNumbers(String playerName, List<Integer> ints) {
    if (ints.size() < MIN_NUMBER_OF_ROLLS || ints.size() > MAX_NUMBER_OF_ROLLS) {
      throw new IncorrectNumberOfNumbersException(playerName);
    }
  }

  private void verifyNumbersInSequence(String playerName, List<Integer> ints) {
    ints.stream().filter(number -> number < MIN_NUMBER_OF_KNOCKED_PINS || number > MAX_NUMBER_OF_KNOCKED_PINS).findAny().ifPresent(number -> {
      throw new IncorrectNumberInSequenceException(playerName, number);
    });
  }

  public static LinesToPlayerPlaysConverter instance() {
    return new LinesToPlayerPlaysConverter();
  }

}
