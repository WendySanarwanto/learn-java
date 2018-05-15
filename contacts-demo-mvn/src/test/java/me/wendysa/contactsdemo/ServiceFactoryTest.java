package me.wendysa.contactsdemo;

import junit.framework.*;
import static org.junit.Assert.*;

import me.wendysa.contactsdemo.ServiceFactory;
import me.wendysa.contactsdemo.contracts.*;
import me.wendysa.contactsdemo.services.*;

public class ServiceFactoryTest extends TestCase {

  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public ServiceFactoryTest(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(ServiceFactoryTest.class);
  }

  /**
   * Get ContactService instance for MySql.
   */
  public void testGetContactServiceInstanceForMySql() {
    IContactBehaviour contactBehaviour = ServiceFactory.getContactService(ServiceFactory.MYSQL_STORAGE_TYPE);
    assertNotNull(contactBehaviour);

    ContactService contactServiceInstance = (ContactService) contactBehaviour;
    assertNotNull(contactServiceInstance);
  }

  /**
   * Get ContactService instance.
   */
  public void testGetContactServiceInstance() {
    IContactBehaviour contactBehaviour = ServiceFactory.getContactService("");
    assertNotNull(contactBehaviour);

    ContactService contactServiceInstance = (ContactService) contactBehaviour;
    assertNotNull(contactServiceInstance);
  }

  /**
   * Get ScheduleService instance.
   */
  public void testGetScheduleServiceInstance() {
    ISchedulable schedulable = ServiceFactory.getScheduleService("");
    assertNotNull(schedulable);

    ScheduleService scheduleServiceInstance = (ScheduleService) schedulable;
    assertNotNull(schedulable);
  }

  /**
   * Get ScheduleService instance for MySql.
   */
  public void testGetScheduleServiceInstanceForMySql() {
    ISchedulable schedulable = ServiceFactory.getScheduleService(ServiceFactory.MYSQL_STORAGE_TYPE);
    assertNotNull(schedulable);

    ScheduleService scheduleServiceInstance = (ScheduleService) schedulable;
    assertNotNull(schedulable);
  }  

}