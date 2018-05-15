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
   * Create the test case
   *
   * @param testName name of the test case
   */
  public ContactTest(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(ContactTest.class);
  }

  /**
   * Test Contact instance's properties
   */
  public void testContactProperties() {
    Contact contact = createContactTestData();

    assertEquals(id, contact.getId());
    assertEquals(name, contact.getName());
    assertEquals(email, contact.getEmail());
    assertEquals(Contact.Type.BUSINESS, contact.getType());
  }

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
