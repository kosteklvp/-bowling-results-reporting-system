package com.kosteklvp.bowling;

public class BowlingUtils {

  public static final int MAX_NUMBER_OF_ROLLS = 21;
  public static final int MAX_NUMBER_OF_KNOCKED_PINS = 10;
  public static final int NUMBER_OF_NORMAL_FRAMES_IN_A_GAME = 9;

  public enum PointsType { // PointType
    NONE(' '),
    ZERO('-'),
    SPARE('/'),
    STRIKE('X');

    private final char symbol;

    PointsType(char symbol) {
      this.symbol = symbol;
    }

    public char getSymbol() {
      return symbol;
    }

  }

  // metoda dla zywklych

  public static boolean isStrike(int numberOfPinsKnockedInRoll) {
    return numberOfPinsKnockedInRoll == MAX_NUMBER_OF_KNOCKED_PINS;
  }

  public static boolean isSpare(int numberOfPinsKnockedInFirstRoll, int numberOfPinsKnockedInSecondRoll) {
    return numberOfPinsKnockedInFirstRoll + numberOfPinsKnockedInSecondRoll == MAX_NUMBER_OF_KNOCKED_PINS &&
        numberOfPinsKnockedInFirstRoll != MAX_NUMBER_OF_KNOCKED_PINS;
  }

}
