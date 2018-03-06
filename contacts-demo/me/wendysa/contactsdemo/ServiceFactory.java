package me.wendysa.contactsdemo;

import me.wendysa.contactsdemo.contracts.*;
import me.wendysa.contactsdemo.services.*;
import me.wendysa.contactsdemo.repositories.ContactRepository;
import me.wendysa.contactsdemo.repositories.ScheduleRepository;

public class ServiceFactory {
  public static IContactBehaviour getContactService(){
    return new ContactService(new ContactRepository());
  }

  public static ISchedulable getScheduleService() {
    return new ScheduleService (new ScheduleRepository());
  }
}