package me.wendysa.contactsdemo;

import me.wendysa.contactsdemo.exceptions.*;

import me.wendysa.contactsdemo.contracts.*;
import me.wendysa.contactsdemo.models.Contact;
import me.wendysa.contactsdemo.models.Schedule;
import me.wendysa.contactsdemo.repositories.*;
import me.wendysa.contactsdemo.services.ContactService;
import me.wendysa.contactsdemo.services.ScheduleService;;

/**
 * Main class
 */
public class Main{
  Contact[] contacts;
  IContactBehaviour contactService;
  ISchedulable scheduleService;

  public Main(IContactBehaviour contactService, 
              ISchedulable scheduleService) {
    this.contacts = new Contact[10];
    this.contactService = contactService;
    this.scheduleService = scheduleService;
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
    Main main = new Main(new ContactService(), 
                          new ScheduleService(new ScheduleRepository()));

    try {
      Contact dianaContact = main.contactService.createContact("Diana Prince", "diana.prince@gmail.com", Contact.Type.FRIEND);
      main.contactService.keepNewContact(dianaContact, main.contacts);

      Contact steveContact = main.contactService.createContact("Steve Rogers", "steve.rogers@gmail.com", Contact.Type.BUSINESS);
      main.contactService.keepNewContact(steveContact, main.contacts);

      main.printContactsList();

      System.out.println("[INFO] - Creating a meeting schedule with Diana ...");
      // Create a schedule with an existing contact
      String beginDate = "2018-04-03 17:00";
      String endDate = "2018-04-03 23:00";

      String description = "Diana,\n We've just collected 3 investment prospects at last week ago. In this saturday, let's meet and discuss about these prospects.\n\nCheers.\n";
      String organiser = "Wendy Sanarwanto";
      String title = "Weekly meeting with Diana, discussing about few interesting investment prospects.";
      Schedule createdSchedule = main.scheduleService.doSchedule(beginDate, endDate, new Contact[] { dianaContact }, description, organiser, title);
      if (createdSchedule == null) {
        throw new DoScheduleException();
      } else {
        // Display formatted schedule
        String createdScheduleInfo = String.format("%s", createdSchedule.toString());
        System.out.println(createdScheduleInfo);
      }
    } catch(Exception err) {
      System.out.println(String.format("[ERROR] - Details: %s. Stacktrace: \n", err));
      err.printStackTrace();
    }
  }
}