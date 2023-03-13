package com.kosteklvp.bowling.roll;

enum RollType {
  NONE(' '),
  NORMAL('-'),
  SPARE('/'),
  STRIKE('X'),
  TENTH_FRAME_SPARE('/'),
  TENTH_FRAME_STRIKE('X');

  private final char symbol;

  private RollType(char symbol) {
    this.symbol = symbol;
  }

  public char getSymbol() {
    return symbol;
  }

}
