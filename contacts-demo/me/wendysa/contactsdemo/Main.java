package me.wendysa.contactsdemo;

import me.wendysa.contactsdemo.exceptions.*;
import me.wendysa.contactsdemo.contracts.*;
import me.wendysa.contactsdemo.models.*;
import me.wendysa.contactsdemo.services.*;

/**
 * Main class
 */
public class Main{
  Contact[] contacts;
  private IContactBehaviour contactService;

  public Main(IContactBehaviour contactService) {
    this.contacts = new Contact[10];
    this.contactService = contactService;
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
    // Note - Generally, we do not instantiate the dependency directly using new. Instead, we usually use DI framework to inject the dependency instance.
    Main main = new Main(new ContactService());

    try {
      Contact newContact = main.contactService.createContact("Diana Prince", "diana.prince@gmail.com", Contact.Type.FRIEND);
      main.keepNewContact(newContact);

      newContact = main.contactService.createContact("Steve Rogers", "steve.rogers@gmail.com", Contact.Type.BUSINESS);
      main.contactService.keepNewContact(newContact, main.contacts);

      main.printContactsList();
    } catch(Exception err) {
      System.out.println(String.format("[ERROR] - Details: \n%s", err));
    }
  }
}