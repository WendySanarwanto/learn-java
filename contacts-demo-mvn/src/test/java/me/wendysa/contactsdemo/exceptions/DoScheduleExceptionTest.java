package me.wendysa.contactsdemo.exceptions;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

import me.wendysa.contactsdemo.exceptions.*;

public class DoScheduleExceptionTest {
  @Test
  public void testConstructor() {
    DoScheduleException doScheduleException = new DoScheduleException();
    String expectedErrorMessage = "Creating a new schedule is failing.";
    String errorMessage = doScheduleException.getMessage();
    assertEquals(errorMessage, expectedErrorMessage);
  }
};
