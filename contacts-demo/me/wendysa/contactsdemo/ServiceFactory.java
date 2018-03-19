package me.wendysa.contactsdemo;

import me.wendysa.contactsdemo.contracts.*;
import me.wendysa.contactsdemo.models.Contact;
import me.wendysa.contactsdemo.services.*;
import me.wendysa.contactsdemo.repositories.ContactMysqlRepository;
import me.wendysa.contactsdemo.repositories.ContactRepository;
import me.wendysa.contactsdemo.repositories.ScheduleRepository;

public class ServiceFactory {
  public static final String MYSQL_STORAGE_TYPE = "mysql";
  public static final String JDBC_LOCAL_URL = "jdbc:mysql://localhost:3306/contacts-demo?user=root&password=test123&useSSL=false";

  public static IContactBehaviour getContactService(String storageType){
    IRepository<Contact> contactRepository = null;

    if (MYSQL_STORAGE_TYPE.equals(storageType)) {
      contactRepository = new ContactMysqlRepository(JDBC_LOCAL_URL);
    } else {
      contactRepository = new ContactRepository();
    }
    return new ContactService(contactRepository);
  }

  // public static ISchedulable getScheduleService(String storageType) {
  public static ISchedulable getScheduleService() {
    return new ScheduleService (new ScheduleRepository());
  }
}