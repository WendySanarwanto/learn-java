package me.wendysa.contactsdemo;

import me.wendysa.contactsdemo.exceptions.*;
import me.wendysa.contactsdemo.models.*;

/**
 * Main class
 */
public class Main{
  private Contact[] contacts;

  public Main() {
    this.contacts = new Contact[10];
  }

  /**
   * A helper for creating a new Contact instance.
   */
  public Contact createContact(String name, String email, Contact.Type contactType) {
    Contact newContact = new Contact(name, email);
    newContact.setType(contactType);
    System.out.println( String.format("\nA new contact has been created :\n------------------------------\n %s", newContact.toString()) );
    return newContact;
  }

  /**
   * A helper for keeping a new contact in Contacts list.
   */
  public void keepNewContact(Contact contact) throws NoAvailableSlotException {
    // find available slot in contact list:
    for(int i=0; i< contacts.length; i++){
      if (contacts[i] == null) {
        contacts[i] = contact;
        return;
      }
    }
    throw new NoAvailableSlotException();
  }

  /**
   * A helper for printing contacts list on command line terminal.
   */
  public void printContactsList() {
    System.out.println("\n==================================");
    System.out.println("\nBelow is the current contacts list:\n");
    System.out.println("==================================:\n");
    for(int i=0; i < contacts.length; i++){
      if (contacts[i] != null) {
        String printedContact = String.format("%s\n----------\n", contacts[i]);
        System.out.println(printedContact);
      }
    };
  }

  /**
   * Entry point of this program.
   */
  public static void main(String []args){
    Main main = new Main();
    try {
      Contact newContact = main.createContact("Diana Prince", "diana.prince@gmail.com", Contact.Type.FRIEND);
      main.keepNewContact(newContact);

      newContact = main.createContact("Steve Rogers", "steve.rogers@gmail.com", Contact.Type.BUSINESS);
      main.keepNewContact(newContact);

      main.printContactsList();
    } catch(Exception err) {
      System.out.println(String.format("[ERROR] - Details: \n%s", err));
    }
  }
}