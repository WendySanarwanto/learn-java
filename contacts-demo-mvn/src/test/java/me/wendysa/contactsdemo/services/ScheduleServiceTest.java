package me.wendysa.contactsdemo.services;

// import java.util.A;
// import java.util.List;
import java.util.*;
import org.junit.*;

import static org.junit.Assert.*;

import me.wendysa.contactsdemo.ServiceFactory;
import me.wendysa.contactsdemo.models.*;
import me.wendysa.contactsdemo.contracts.*;
import me.wendysa.contactsdemo.services.*;

public class ScheduleServiceTest {
  private ISchedulable scheduleService;
  private IContactBehaviour contactService;
  private Contact dianaContact, steveContact;
  private String beginDate = "2018-05-12 17:00";
  private String endDate = "2018-05-12 23:00";
  private String beginDate2 = "2018-05-14 17:00";
  private String endDate2 = "2018-04-14 23:00";
  private String description = "Test Schedule";
  private String description2 = "Test Schedule #2";
  private String organiser = "Wendy Sanarwanto";
  private String title = "Weekly Meeting";
  private String title2 = "Weekly Meeting #2";
  private List<Contact> participantsList1;
  private List<Contact> participantsList2;

  @Before
  public void initialise() {
    scheduleService = ServiceFactory.getScheduleService("");
    contactService = ServiceFactory.getContactService("");
    dianaContact = contactService.createContact("Diana Prince", "diana.prince@gmail.com", Contact.Type.BUSINESS);
    steveContact = contactService.createContact("Steve Rogers", "steve.rogers@gmail.com", Contact.Type.BUSINESS);
    participantsList1 = Arrays.asList(new Contact[] { dianaContact, steveContact });
    participantsList2 = Arrays.asList(new Contact[] { steveContact });
  }

  @After
  public void tearDown() {
    scheduleService.removeAll();
  }

  @Test
  public void testInstantiateScheduleService() {
    assertNotNull(scheduleService);
  }

  @Test
  public void testCreateNewSchedule() {
    List<Contact> participants = participantsList1;

    Schedule newSchedule = scheduleService.doSchedule(beginDate, endDate, participants, description, organiser, title);
    assertNotNull(newSchedule);

    Schedule[] expectedSchedules = new Schedule[] { newSchedule };
    List<Schedule> schedulesList = scheduleService.getSchedules(null);
    Schedule[] schedules = schedulesList.toArray(new Schedule[schedulesList.size()]);

    assertArrayEquals(schedules, expectedSchedules);
  }

  @Test
  public void testGetSchedulesInDescendingOrder() {
    Schedule firstSchedule = scheduleService.doSchedule(beginDate, endDate, participantsList1, description, organiser, title);
    assertNotNull(firstSchedule);

    Schedule secondSchedule = scheduleService.doSchedule(beginDate2, endDate2, participantsList2, description2, organiser, title2);
    assertNotNull(secondSchedule);

    Schedule[] expectedSchedules = new Schedule[] { secondSchedule, firstSchedule };
    List<Schedule> schedulesList = scheduleService.getSchedules(Optional.of(ISchedulable.SortBy.START_DATE_DESC));
    Schedule[] schedules = schedulesList.toArray(new Schedule[schedulesList.size()]);

    assertArrayEquals(schedules, expectedSchedules);
  }

  @Test 
  public void testGetSchedulesInAscendingOrder() {
    Schedule firstSchedule = scheduleService.doSchedule(beginDate, endDate, participantsList1, description, organiser, title);
    assertNotNull(firstSchedule);

    Schedule secondSchedule = scheduleService.doSchedule(beginDate2, endDate2, participantsList2, description2, organiser, title2);
    assertNotNull(secondSchedule);

    Schedule[] expectedSchedules = new Schedule[] { firstSchedule, secondSchedule };
    List<Schedule> schedulesList = scheduleService.getSchedules(Optional.of(ISchedulable.SortBy.START_DATE_ASC));
    Schedule[] schedules = schedulesList.toArray(new Schedule[schedulesList.size()]);

    assertArrayEquals(schedules, expectedSchedules);
  }

  
  @Test
  public void testGetSchedulesByParticipant() {
    Schedule firstSchedule = scheduleService.doSchedule(beginDate, endDate, participantsList1, description, organiser, title);
    assertNotNull(firstSchedule);

    Schedule secondSchedule = scheduleService.doSchedule(beginDate2, endDate2, participantsList2, description2, organiser, title2);
    assertNotNull(secondSchedule);
    
    List<Schedule> dianaSchedulesList = scheduleService.getSchedulesByParticipant(Optional.of(dianaContact));
    assertNotNull(dianaSchedulesList);
    Schedule[] dianaSchedules = dianaSchedulesList.toArray(new Schedule[dianaSchedulesList.size()]);
    Schedule[] expectedDianaSchedules = new Schedule[] { firstSchedule };
    assertArrayEquals(dianaSchedules, expectedDianaSchedules);

    List<Schedule> steveSchedulesList = scheduleService.getSchedulesByParticipant(Optional.of(steveContact));
    assertNotNull(steveSchedulesList);
    Schedule[] steveSchedules = steveSchedulesList.toArray(new Schedule[steveSchedulesList.size()]);
  }

  @Test
  public void testGetSchedulesByNoParticipant() {
    Schedule firstSchedule = scheduleService.doSchedule(beginDate, endDate, participantsList1, description, organiser, title);
    assertNotNull(firstSchedule);

    Schedule secondSchedule = scheduleService.doSchedule(beginDate2, endDate2, participantsList2, description2, organiser, title2);
    assertNotNull(secondSchedule);
    
    List<Schedule> schedulesList = scheduleService.getSchedulesByParticipant(null);

    assertNotNull(schedulesList);
    Schedule[] expectedSchedules = new Schedule[] { };
    Schedule[] schedules = schedulesList.toArray(new Schedule[schedulesList.size()]);

    assertArrayEquals(schedules, expectedSchedules);
  }
}
