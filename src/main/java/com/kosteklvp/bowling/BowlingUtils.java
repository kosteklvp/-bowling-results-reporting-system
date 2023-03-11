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

}
