package me.wendysa.contactsdemo.services;

import java.util.Arrays;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

import me.wendysa.contactsdemo.ServiceFactory;
import me.wendysa.contactsdemo.models.Contact;
import me.wendysa.contactsdemo.contracts.*;
import me.wendysa.contactsdemo.services.*;

public class ContactServiceTest {
  private IContactBehaviour contactService;
  private final String name = "Diana Prince";
  private final String email = "diana.prince@gmail.com";
  private final Contact.Type contactType = Contact.Type.FRIEND;

  @Before
  public void initialise() {
    contactService = ServiceFactory.getContactService("");
  }

  @After
  public void tearDown() {
    contactService.removeAll();
  }

  @Test
  public void testInstantiateContactService() {
    assertNotNull(contactService);
  }

  @Test
  public void testCreateNewContact() {
    Contact savedContact = contactService.createContact(name, email, contactType);
    assertNotNull(savedContact);

    Contact[] expectedContacts = new Contact[]{ savedContact };
    List<Contact> contactsList = contactService.getContacts();
    Contact[] contacts = contactsList.toArray(new Contact [contactsList.size()]);

    assertArrayEquals(contacts, expectedContacts);
  }

  @Test
  public void testCreateNullEmailContact() {
    Contact nullContact = contactService.createContact("Wendy Sanarwanto", null, Contact.Type.FRIEND);
    assertNull(nullContact);
  }

  @Test
  public void testCreateNullNameContact() {
    Contact nullContact = contactService.createContact(null, "wendy.sanarwanto@gmail.com", Contact.Type.FRIEND);
    assertNull(nullContact);
  }

  @Test
  public void testCreateNullTypeContact() {
    Contact nullContact = contactService.createContact("Wendy Sanarwanto", "wendy.sanarwanto@gmail.com", null);
    assertNull(nullContact);
  }

};