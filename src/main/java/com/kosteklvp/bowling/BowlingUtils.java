package com.kosteklvp.bowling;

public class BowlingUtils {

  public static final int MAX_NUMBER_OF_THROWS = 21;

  public enum Punctation {
    NONE(' '),
    ZERO('-'),
    SPARE('/'),
    STRIKE('X');

    private final char symbol;

    Punctation(char symbol) {
      this.symbol = symbol;
    }

    public char getSymbol() {
      return symbol;
    }

  }

  // metoda dla zywklych

  public static boolean isStrike(int points) {
    return points == 10;
  }

  public static boolean isSpare(int points, boolean isEven) {
    return points == 10 && isEven;
  }

  public static boolean isDoubleBonus(Punctation previousPunctuation, Punctation prepreviousPunctuation) {
    return previousPunctuation == Punctation.SPARE ||
        previousPunctuation == Punctation.STRIKE ||
        prepreviousPunctuation == Punctation.STRIKE;
  }

  public static boolean isTripleBonus(Punctation previousPunctuation, Punctation prepreviousPunctuation) {
    return prepreviousPunctuation == Punctation.STRIKE &&
        (previousPunctuation == Punctation.STRIKE || previousPunctuation == Punctation.SPARE);
  }

}
