package me.wendysa.contactsdemo;

import me.wendysa.contactsdemo.models.*;

/**
 * Main class
 */
public class Main{

  /**
   * A helper for creating a new Contact instance.
   */
  public Contact createContact(String name, String email, Contact.Type contactType) {
    Contact newContact = new Contact(name, email);
    newContact.setType(contactType);
    System.out.println("\nA new contact has been added :\n------------------------------\n " + newContact.toString());
    return newContact;
  }

  /**
   * Entry point of this program.
   */
  public static void main(String []args){
    Main main = new Main();
    main.createContact("Diana Prince", "diana.prince@gmail.com", Contact.Type.FRIEND);
  }
}