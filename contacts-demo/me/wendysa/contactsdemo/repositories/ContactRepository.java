package me.wendysa.contactsdemo.repositories;

import java.util.*;
import me.wendysa.contactsdemo.models.*;

public class ContactRepository {
  private final Hashtable<String, Contact> storage = new Hashtable<String, Contact>();

  public Contact push(Contact newContact) {
    String email = newContact != null ? newContact.getEmail() : null;
    this.storage.put(email, newContact);
    return newContact;
  }
}