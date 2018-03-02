package me.wendysa.contactsdemo.contracts;

import java.util.*;
import me.wendysa.contactsdemo.models.*;

public interface IContactBehaviour {
  public Contact createContact(String name, String email, Contact.Type contactType);
  public List<Contact> getContacts();
}