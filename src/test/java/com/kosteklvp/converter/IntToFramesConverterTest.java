package com.kosteklvp.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.kosteklvp.bowling.frame.Frame;
import com.kosteklvp.bowling.frame.NormalFrame;
import com.kosteklvp.bowling.frame.TenthFrame;

class IntToFramesConverterTest {

  private static final int NUMBER_OF_FRAMES_IN_A_GAME = 10;

  private final Converter<List<Integer>, List<Frame>> converter = new IntsToFramesConverter();

  @ParameterizedTest
  @MethodSource("com.kosteklvp.data.DataFactory#listsOfKnockedPinsProvider")
  void returnsListOfTenFrames(List<Integer> ints) {
    var frames = converter.convert(ints);

    assertThat(frames).hasSize(NUMBER_OF_FRAMES_IN_A_GAME);
  }

  @ParameterizedTest
  @MethodSource("com.kosteklvp.data.DataFactory#listsOfKnockedPinsProvider")
  void firstNineElementsOfReturnedListAreNormalFrameType(List<Integer> ints) {
    var frames = converter.convert(ints);

    frames.remove(frames.size() - 1);
    assertThat(frames).allMatch(frame -> frame instanceof NormalFrame);
  }

  @ParameterizedTest
  @MethodSource("com.kosteklvp.data.DataFactory#listsOfKnockedPinsProvider")
  void lastElementOfReturnedListIsTenthFrameType(List<Integer> ints) {
    var frames = converter.convert(ints);

    var lastFrame = frames.get(frames.size() - 1);
    assertThat(lastFrame).isInstanceOf(TenthFrame.class);
  }

}
