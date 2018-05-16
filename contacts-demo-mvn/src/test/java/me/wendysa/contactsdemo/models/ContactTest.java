package me.wendysa.contactsdemo.models;

import junit.framework.*;
import me.wendysa.contactsdemo.models.Contact;

import static org.junit.Assert.*;

public class ContactTest extends TestCase {
  private final String name = "John Smith";
  private final String email = "john.smith@gmail.com";
  private final Contact.Type type = Contact.Type.BUSINESS;
  private final int id = 100;

  /**
   * Test Contact's toString method
   */
  public void testContactDefaultStringOutput() {
    Contact contact = createContactTestData();
    String expected = "\nName: "+contact.getName()+"\n"+
                      "Email: "+contact.getEmail()+"\n"+
                      "Type: "+contact.getType().value + "\n";

    assertEquals(contact.toString(), expected);
  }

  /**
   * A helper for creating Contact test data.
   */
  private Contact createContactTestData() {
    Contact contact = new Contact(name, email);
    contact.setId(id);
    contact.setName(name);
    contact.setEmail(email);
    contact.setType(Contact.Type.BUSINESS);

    return contact;
  }
};
