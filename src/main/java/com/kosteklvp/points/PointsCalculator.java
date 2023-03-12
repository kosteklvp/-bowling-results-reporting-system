package com.kosteklvp.points;

import static com.kosteklvp.bowling.BowlingUtils.isSpare;
import static com.kosteklvp.bowling.BowlingUtils.isStrike;
import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

import com.kosteklvp.bowling.BowlingUtils;
import com.kosteklvp.bowling.BowlingUtils.PointsType;
import com.kosteklvp.converter.PlayerPlay;
import com.kosteklvp.frame.Frame;
import com.kosteklvp.frame.FrameWithType;

public class PointsCalculator { // TODO dodac interfejs

  public int calculateResult(PlayerPlay playerPlay) {
    int result = 0;

    FrameWithType prepreviousFrame = null;
    FrameWithType previousFrame = null;

    for (Frame frame : playerPlay.getFrames()) {
      if (isTriplePoints(previousFrame, prepreviousFrame)) {
        result = result + 3 * frame.getPoints();
      } else if (isDoublePoints(previousFrame)) {
        result = result + 2 * frame.getPoints();
      } else {
        result = result + frame.getPoints();
      }

      prepreviousFrame = previousFrame;
      previousFrame = (FrameWithType) frame; // Every previous frame is a FrameWithType.
    }

    List<Character> symbols = new ArrayList<>(BowlingUtils.MAX_NUMBER_OF_ROLLS);

    Integer previousPoint = 0;
    PointsType prepreviousPointsType = null;
    PointsType previousPointsType = null;

    for (Integer point : points) {
      PointsType thisPointsType = PointsType.NONE;

      if (isStrike(point)) {
        thisPointsType = PointsType.STRIKE;
      } else if (isSpare(previousPoint + point, symbols.size() % 2 != 0)) {
        thisPointsType = PointsType.SPARE;
        previousPoint = 0;
      } else {
        thisPointsType = PointsType.NONE;
      }

      if (BowlingUtils.isTripleBonus(previousPointsType, prepreviousPointsType)) {
        result = result + 3 * point;
      } else if (BowlingUtils.isDoubleBonus(previousPointsType)) {
        result = result + 2 * point;
      } else {
        result = result + point;
      }

      prepreviousPointsType = previousPointsType;
      previousPointsType = thisPointsType;
      previousPoint = point;
    }

    return result;
  }

  private boolean isDoublePoints(FrameWithType previousFrame) {
    return nonNull(previousFrame) && (previousFrame.isSpare() || previousFrame.isStrike());
  }

  private boolean isTriplePoints(FrameWithType previousFrame, FrameWithType prepreviousFrame) {
    return nonNull(prepreviousFrame) && prepreviousFrame.isStrike() && isDoublePoints(previousFrame);
  }

}
