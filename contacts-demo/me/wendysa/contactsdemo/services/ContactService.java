package me.wendysa.contactsdemo.services;

import java.util.*;

import me.wendysa.contactsdemo.contracts.*;
import me.wendysa.contactsdemo.models.*;

public class ContactService extends ServiceBase<Contact> implements IContactBehaviour {
  public ContactService(IRepository<Contact> repository) {
    super(repository);
  }

  @Override
  public Contact createContact(String name, String email, Contact.Type contactType)  {
    Contact newContact = new Contact(name, email);
    newContact.setType(contactType);
    Contact pushedContact = this.push(newContact);
    if (pushedContact != null) {
      this.onContactPushed(pushedContact);
    }
    return pushedContact;
  }

  @Override
  public List<Contact> getContacts() {
    return this.getAll();
  }

  private void onContactPushed(Contact pushedContact) {
    System.out.println( String.format("\nA new contact has been created :\n------------------------------\n %s", pushedContact.toString()) );
  }

  @Override
  public void removeAll() {
    super.removeAll();
  }
}