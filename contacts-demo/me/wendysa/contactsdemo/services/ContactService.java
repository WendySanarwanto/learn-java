package me.wendysa.contactsdemo.services;

import me.wendysa.contactsdemo.contracts.*;
import me.wendysa.contactsdemo.models.*;
import me.wendysa.contactsdemo.repositories.*;

public class ContactService extends ModelServiceBase<Contact> implements IContactBehaviour {
  private final ContactRepository repository;

  public ContactService(ContactRepository repository) {
    this.repository = repository;
  }

  public Contact createContact(String name, String email, Contact.Type contactType)  {
    Contact newContact = new Contact(name, email);
    newContact.setType(contactType);
    Contact pushedContact = this.repository.push(newContact);
    if (pushedContact != null) {
      this.onContactPushed(pushedContact);
    }
    return pushedContact;
  }

  private void onContactPushed(Contact pushedContact) {
    System.out.println( String.format("\nA new contact has been created :\n------------------------------\n %s", pushedContact.toString()) );
  }
}