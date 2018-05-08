package me.wendysa.contactsdemo.repositories;

import java.util.*;

import me.wendysa.contactsdemo.contracts.IRepository;
import me.wendysa.contactsdemo.models.Contact;

public class ContactRepository implements IRepository<Contact> {
  private final Hashtable<String, Contact> storage = new Hashtable<String, Contact>();

  @Override
  public Contact push(Contact newContact) {
    String email = newContact != null ? newContact.getEmail() : null;
    this.storage.put(email, newContact);
    return newContact;
  }

  @Override
  public List<Contact> getAll() {
    // Get storage's iterator
    Enumeration<Contact> contactEnum = this.storage.elements();
    ArrayList<Contact> contactsList = Collections.list(contactEnum);
    // return contactsList.toArray(new Contact[contactsList.size()]);
    return contactsList;
  }

  @Override
  public void deleteAll() {
    this.storage.clear();
  }
}