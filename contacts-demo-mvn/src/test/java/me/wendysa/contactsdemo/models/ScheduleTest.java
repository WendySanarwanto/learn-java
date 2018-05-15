package me.wendysa.contactsdemo.models;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;

import me.wendysa.contactsdemo.models.*;

public class ScheduleTest {
  private final String title = "2nd week of May 2018's schedule";
  private final Date beginDate = new Date(2018, 5, 12);
  private final Date endDate = new Date(2018, 5, 13);
  private final int id = 100;
  private List<Contact> participants;
  private final String description = "Test schedule.";
  private final String organiser = "Wendy Sanarwanto";

  @Test
  public void testScheduleProperties() {
    Schedule schedule = 
        new Schedule(beginDate, endDate, participants, description, organiser, title);
    schedule.setId(id);

    assertEquals(beginDate, schedule.getBeginDate());
    assertEquals(endDate, schedule.getEndDate());
    assertArrayEquals(participants.toArray(), schedule.getParticipants().toArray());
    assertEquals(description, schedule.getDescription());
    assertEquals(organiser, schedule.getOrganiser());
    assertEquals(title, schedule.getTitle());
    assertEquals(id, schedule.getId());
  }

  @Test
  public void testScheduleDefaultStringOutput() {
    Schedule schedule = new Schedule(null, null, participants, null, null, null);
    schedule.setId(id);
    schedule.setBeginDate(beginDate);
    schedule.setEndDate(endDate);
    schedule.setDescription(description);
    schedule.setOrganiser(organiser);
    schedule.setTitle(title);
/*
------------------
2nd week of May 2018's schedule

Start        : 12 Jun 3918, @ 12:00 AM
End          : 13 Jun 3918, @ 12:00 AM
Participants : Diana Prince, Steve Rogers
Organiser    : Wendy Sanarwanto
Description  :
Test schedule.
*/
    String outputString = schedule.toString();
    // System.out.println("[DEBUG] - <testScheduleDefaultStringOutput> outputString = "+ outputString);
    assertThat(outputString, containsString(title));
    assertThat(outputString, containsString(organiser));
    assertThat(outputString, containsString(description));
  }

  @Before
  public void runBeforeTestMethod() {
    Contact dianaContact = new Contact("Diana Prince", "diana.prince@gmail.com");
    Contact steveContact = new Contact("Steve Rogers", "steve.rogers@gmail.com");
    participants = Arrays.asList(new Contact[] { dianaContact, steveContact });
  }

} 