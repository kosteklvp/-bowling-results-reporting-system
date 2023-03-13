package com.kosteklvp.exception;

public class IncorrectNumberOfNumbersException extends RuntimeException {

  public IncorrectNumberOfNumbersException(String playerName) {
    super("Incorrect number of numbers for player: " + playerName + ".");
  }

  public IncorrectNumberOfNumbersException() {
    super("Incorrect number of numbers in one of sequences.");
  }

}
