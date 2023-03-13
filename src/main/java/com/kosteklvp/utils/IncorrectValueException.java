package com.kosteklvp.utils;

public class IncorrectValueException extends RuntimeException {

  public IncorrectValueException() {
    super("Incorrect value. Value has to be in <0, 9> range.");
  }

}
