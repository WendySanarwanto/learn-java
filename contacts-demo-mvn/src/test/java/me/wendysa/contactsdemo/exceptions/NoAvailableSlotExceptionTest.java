package me.wendysa.contactsdemo.exceptions;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

import me.wendysa.contactsdemo.exceptions.*;

public class NoAvailableSlotExceptionTest {
  @Test
  public void testConstructor() {
    NoAvailableSlotException noAvailableSlotException = new NoAvailableSlotException();
    String expectedErrorMessage = "There is no available slots in the Contacts list.";
    String errorMessage = noAvailableSlotException.getMessage();
    assertEquals(errorMessage, expectedErrorMessage);    
  }  
}