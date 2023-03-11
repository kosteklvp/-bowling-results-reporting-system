package com.kosteklvp.table.heading;

public class HeadingsNotSetException extends RuntimeException {

  public HeadingsNotSetException() {
    super("Headings are not set.");
  }

}
