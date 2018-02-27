package me.wendysa.contactsdemo.contracts;

import me.wendysa.contactsdemo.exceptions.*;
import me.wendysa.contactsdemo.models.*;

public interface IContactBehaviour {
  public Contact createContact(String name, String email, Contact.Type contactType);
  public void keepNewContact(Contact newContact, Contact[] contactsList) throws NoAvailableSlotException;
}