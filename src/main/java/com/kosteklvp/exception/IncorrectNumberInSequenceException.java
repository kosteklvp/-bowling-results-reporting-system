package com.kosteklvp.exception;

public class IncorrectNumberInSequenceException extends RuntimeException {

  public IncorrectNumberInSequenceException(String playerName, int number) {
    super("Incorrect number '" + number + "' in sequence for player: " + playerName + ".");
  }

}
