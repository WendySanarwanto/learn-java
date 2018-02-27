package me.wendysa.contactsdemo.services;

import me.wendysa.contactsdemo.contracts.*;
import me.wendysa.contactsdemo.exceptions.*;
import me.wendysa.contactsdemo.models.*;

public class ContactService extends ModelServiceBase<Contact> implements IContactBehaviour {
  private Contact[] contactsList;

  public Contact createContact(String name, String email, Contact.Type contactType)  {
    Contact newContact = new Contact(name, email);
    newContact.setType(contactType);
    return newContact;
  }

  @Override
  public void keepNewModelInstance(Contact newModel, Contact[] modelsList) throws NoAvailableSlotException { 
    super.keepNewModelInstance(newModel, modelsList);
    System.out.println( String.format("\nA new contact has been created :\n------------------------------\n %s", newModel.toString()) );
  }

  public void keepNewContact(Contact newContact, Contact[] contactsList) throws NoAvailableSlotException {
    this.keepNewModelInstance(newContact, contactsList);
  }
}