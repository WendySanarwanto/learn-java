package me.wendysa.contactsdemo.exceptions;

public class NoAvailableSlotException extends Exception {
  private static final String ERROR_MESSAGE = "There is no available slots in the Contacts list.";
  static final long serialVersionUID = 1L;

  public NoAvailableSlotException() {
    super(ERROR_MESSAGE);
  }
}