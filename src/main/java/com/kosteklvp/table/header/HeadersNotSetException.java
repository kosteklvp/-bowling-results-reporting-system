package com.kosteklvp.table.header;

public class HeadersNotSetException extends RuntimeException {

  public HeadersNotSetException() {
    super("Headings are not set.");
  }

}
