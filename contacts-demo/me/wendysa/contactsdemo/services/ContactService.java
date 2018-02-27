package me.wendysa.contactsdemo.services;

import me.wendysa.contactsdemo.contracts.*;
import me.wendysa.contactsdemo.exceptions.*;
import me.wendysa.contactsdemo.models.*;

public class ContactService implements IContactBehaviour {
  private Contact[] contactsList;

  public Contact createContact(String name, String email, Contact.Type contactType)  {
    Contact newContact = new Contact(name, email);
    newContact.setType(contactType);
    System.out.println( String.format("\nA new contact has been created :\n------------------------------\n %s", newContact.toString()) );
    return newContact;
  }

  public void keepNewContact(Contact newContact, Contact[] contactsList) throws NoAvailableSlotException {
    // find available slot in contact list:
    for(int i=0; i< contactsList.length; i++){
      if (contactsList[i] == null) {
        contactsList[i] = newContact;
        return;
      }
    }
    throw new NoAvailableSlotException();
  }
}