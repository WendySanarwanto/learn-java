package me.wendysa.contactsdemo.exceptions;

public class DoScheduleException extends Exception {
  private static final String ERROR_MESSAGE = "Creating a new schedule is failing.";
  static final long serialVersionUID = 2L;

  public DoScheduleException() {
    super(ERROR_MESSAGE);
  }
}