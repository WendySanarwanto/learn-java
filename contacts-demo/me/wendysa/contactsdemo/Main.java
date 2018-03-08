package me.wendysa.contactsdemo;

import java.util.*;

import me.wendysa.contactsdemo.contracts.*;
import me.wendysa.contactsdemo.exceptions.*;
import me.wendysa.contactsdemo.models.Contact;
import me.wendysa.contactsdemo.models.Schedule;

/**
 * Main class
 */
public class Main {
  private IContactBehaviour contactService;
  private ISchedulable scheduleService;

  Main(IContactBehaviour contactService, ISchedulable scheduleService) {
    this.contactService = contactService;
    this.scheduleService = scheduleService;
  }

  Schedule createNewSchedule(String beginDate, String endDate, String description, String organiser,
      String title, List<Contact> participants) throws DoScheduleException {
    Schedule createdSchedule = this.scheduleService.doSchedule(beginDate, endDate, participants, description, organiser,
        title);
    if (createdSchedule == null) {
      throw new DoScheduleException();
    }
    return createdSchedule;
  }

  /**
   * A helper for printing contacts list on command line terminal.
   */
  void printContactsList() {
    System.out.println("\n==================================");
    System.out.println("\nBelow is the current contacts list:\n");
    System.out.println("==================================:\n");

    List<Contact> contacts = this.contactService.getContacts();
    Iterator<Contact> iterator = contacts.iterator();
    
    while(iterator.hasNext()) {
      Contact contact = iterator.next();
      String printedContact = String.format("%s\n----------\n", contact);
      System.out.println(printedContact);
    }

    // NOTE - The usual way we loop a list by using for loop.
    // for (int i = 0; i < contacts.length; i++) {
    //   if (contacts[i] != null) {
    //     String printedContact = String.format("%s\n----------\n", contacts[i]);
    //     System.out.println(printedContact);
    //   }
    // }
  }

  /**
   * A helper for printing out a list of schedules.
   */
  void printSchedules(List<Schedule> schedules) {
    Iterator<Schedule> scheduleIterator = schedules.iterator();

    while(scheduleIterator.hasNext()) {
      // Display formatted schedule
      Schedule schedule = scheduleIterator.next();
      String createdScheduleInfo = String.format("%s", schedule.toString());
      System.out.println(createdScheduleInfo);      
    }
  }

  /**
   * A helper for retrieving all schedules then print them out.
   */
  private void printAllSchedules() {
    System.out.println("Below is the list of meeting schedules with existing contacts:");
    List<Schedule> schedules = scheduleService.getSchedules(Optional.of(ISchedulable.SortBy.START_DATE_DESC));
    printSchedules(schedules);
  }

  private void printSchedulesByParticipant(Contact participant) {
    System.out.println(String.format("\nBelow is the schedule(s) which have '%s' as participant.", participant.getName()));
    List<Schedule> participantSchedules = scheduleService.getSchedulesByParticipant(Optional.of(participant));
    printSchedules(participantSchedules);
  }

  /**
   * Entry point of this program.
   */
  public static void main(String[] args) {
    // Note - Generally, we do not instantiate the dependency directly using new. Instead, we usually use DI framework to inject the dependency instance.
    Main main = new Main(ServiceFactory.getContactService(), ServiceFactory.getScheduleService());

    try {
      Contact dianaContact = main.contactService.createContact("Diana Prince", "diana.prince@gmail.com",
          Contact.Type.FRIEND);

      Contact steveContact = main.contactService.createContact("Steve Rogers", "steve.rogers@gmail.com",
          Contact.Type.BUSINESS);

      Contact nataliaContact = main.contactService.createContact("Natalia Romanova", "natalia.romanova@gmail.com",
          Contact.Type.FRIEND);

      main.printContactsList();

      // System.out.println(String.format("[INFO] - Creating a meeting schedule for %s ...", dianaContact.getName()));

      // Create a schedule with an existing contact
      String beginDate = "2018-04-03 17:00";
      String endDate = "2018-04-03 23:00";
      String description = "\n\tDiana,\n\n\tWe've just collected 3 investment prospects at last week ago. In this saturday, let's meet and discuss about these prospects.\n\n\tCheers.\n";
      String organiser = "Wendy Sanarwanto";
      String title = "Weekly meeting with Diana, discussing about few interesting investment prospects.";
      main.createNewSchedule(beginDate, endDate, description, organiser, title, Arrays.asList(new Contact[] { dianaContact }));

      beginDate = "2018-04-06 09:00";
      endDate = "2018-04-06 10:00";
      description = "\n\tHello Team,\n\n\tLet's have a weekly team meeting to see what our team's progress, challenge, opportunities and impedements that we may have. I see you all there.\n\n\tCheers.\n";
      organiser = "Wendy Sanarwanto";
      title = "Team weekly meeting.";
      main.createNewSchedule(beginDate, endDate, description, organiser, title, Arrays.asList(new Contact[] { dianaContact, steveContact }));

      beginDate = "2018-03-30 19:00";
      endDate = "2018-03-31 02:00";
      description = "\n\tHello Ladies,\n\n\tJust a gentle reminder to you that we have an appointment to meet you all at usual club, in this weekend. So, I'll see you all in there.\n\n\tCheers.\n";
      organiser = "Wendy Sanarwanto";
      title = "Weekend's Hangout.";
      main.createNewSchedule(beginDate, endDate, description, organiser, title, Arrays.asList(new Contact[] { dianaContact, nataliaContact }));

      main.printAllSchedules();

      // Get schedule(s) which have Natalia as participant
      main.printSchedulesByParticipant(nataliaContact);      

    } catch (Exception err) {
      System.out.println(String.format("[ERROR] - Details: %s. Stacktrace: \n", err));
      err.printStackTrace();
    }
  }

}