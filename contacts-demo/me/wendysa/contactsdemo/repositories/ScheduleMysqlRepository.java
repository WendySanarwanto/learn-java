package me.wendysa.contactsdemo.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.wendysa.contactsdemo.contracts.IRepository;
import me.wendysa.contactsdemo.models.Contact;
import me.wendysa.contactsdemo.models.Schedule;

public class ScheduleMysqlRepository implements IRepository<Schedule> {
  private final String jdbcUrl;

  private final static int BATCH_SIZE = 1000;
  private final static String INSERT_NEW_SCHEDULE = "INSERT INTO schedule (title, beginDate, endDate, description, organiser) VALUES (?, ?, ?, ?, ?)";
  private final static String INSERT_NEW_SCHEDULE_CONTACT = "INSERT INTO scheduleContact (scheduleId, contactId) VALUES (?, ?)";
  private final static String SELECT_ALL_SCHEDULES = "SELECT id, title, beginDate, endDate, description, organiser FROM schedule";
  private final static String SELECT_SCHEDULE_CONTACT_BY_SCHEDULEID = "SELECT scheduleId, contactId FROM scheduleContact where scheduleId = ?";
  private final static String DELETE_ALL_SCHEDULES = "DELETE FROM schedule";
  private final static String DELETE_ALL_SCHEDULE_CONTACTS = "DELETE FROM scheduleContact";

  private final static String SQLERROR_INSERT_NEW_SCHEDULE = "Creating a new Schedule is failing. %s";

  public ScheduleMysqlRepository(String jdbcUrl) {
    this.jdbcUrl = jdbcUrl;
  }

  @Override
  public Schedule push(Schedule newSchedule) {
    try (Connection connection = DriverManager.getConnection(this.jdbcUrl)) {
      // Execute insert query using Prepared Statement
      PreparedStatement statement = connection.prepareStatement(INSERT_NEW_SCHEDULE, Statement.RETURN_GENERATED_KEYS);

      String title = newSchedule.getTitle();
      Date beginDate = newSchedule.getBeginDate();
      Date endDate = newSchedule.getEndDate();
      String description = newSchedule.getDescription();
      String organiser = newSchedule.getOrganiser();

      // Insert The schedule first (title, beginDate, endDate, description, organiser)
      statement.setString(1, title);
      statement.setDate(2, new java.sql.Date(beginDate.getTime()));
      statement.setDate(3, new java.sql.Date(endDate.getTime()));
      statement.setString(4, description);
      statement.setString(5, organiser);

      int affectedRow = statement.executeUpdate();

      // Throw exception if no row was inserted
      if (affectedRow == 0){
        throw new SQLException(String.format(SQLERROR_INSERT_NEW_SCHEDULE, "No rows affected."));
      }

      // Attempting for retrieving id of the created record
      try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
        if (generatedKeys.next()){
          newSchedule.setId(generatedKeys.getInt(1));
        } else {
          throw new SQLException(String.format(SQLERROR_INSERT_NEW_SCHEDULE, "No ID obtained."));
        }
      }

      // Insert Schedule-Contacts (Participants)
      List<Contact> participants = newSchedule.getParticipants();
      this.createParticipants(newSchedule.getId(), participants, BATCH_SIZE, connection);

    } catch(SQLException sqlErr) {
      sqlErr.printStackTrace();
      // TODO: Do proper thing against the sqlErr.
      return null;
    }

    return newSchedule;
  }

  @Override
  public List<Schedule> getAll() {
    List<Schedule> results = null;

    try (Connection connection = DriverManager.getConnection(this.jdbcUrl)) {
      Statement statement = connection.createStatement();
      try (ResultSet rs = statement.executeQuery(SELECT_ALL_SCHEDULES)) {
        results = new ArrayList<Schedule>();

        while(rs.next()){
          int id = rs.getInt("id");
          String title = rs.getString("title");
          Date beginDate = rs.getDate("beginDate");
          Date endDate = rs.getDate("endDate");
          String description = rs.getString("description");
          String organiser = rs.getString("organiser");
          List<Contact> participants = this.getParticipants(id, connection);

          Schedule schedule = new Schedule(beginDate, endDate, participants, description, organiser, title);

          results.add(schedule);
        }
      }
    } catch(SQLException sqlErr) {
      sqlErr.printStackTrace();
    }

    return results;
  }

  @Override
  public void deleteAll() {
    try (Connection connection = DriverManager.getConnection(this.jdbcUrl)) {
      Statement statement = connection.createStatement();
      statement.executeUpdate(DELETE_ALL_SCHEDULES);
      this.onAllSchedulesDeleted(connection);
    } catch(SQLException sqlErr) {
      sqlErr.printStackTrace();
    }
  }

  private List<Contact> getParticipants(int scheduleId, Connection connection) throws SQLException {
    List<Contact> participants = null;

    try (PreparedStatement statement = connection.prepareStatement(SELECT_SCHEDULE_CONTACT_BY_SCHEDULEID)) {
      statement.setLong(1, scheduleId);
      
      List<Long> contactIds = new ArrayList<Long>();
      try (ResultSet rs = statement.executeQuery()) {
        while(rs.next()) {
          long contactId = rs.getLong(2);
          contactIds.add(contactId);
        }
      }

      if (contactIds.size() > 0) {
        ContactMysqlRepository contactsRepo = new ContactMysqlRepository(this.jdbcUrl);
        participants = contactsRepo.getAllByIds(contactIds);
      }
    }

    return participants;
  }

  private void createParticipants(int scheduleId, List<Contact> contacts, int batchSize, Connection connection) throws SQLException {
    // Instantiate Contacts repository
    try (PreparedStatement statement = connection.prepareStatement(INSERT_NEW_SCHEDULE_CONTACT)) {
      int recordCount = 0;
      for(Contact contact: contacts) {
        statement.setLong(1, scheduleId);
        statement.setInt(2, contact.getId());
        statement.addBatch();

        if ( recordCount % BATCH_SIZE == 0 ) {
          statement.executeBatch();
        }
      }

      // Insert remaining records
      statement.executeBatch();
    } 
  }

  private void onAllSchedulesDeleted(Connection connection) throws SQLException {
    try (Statement statement = connection.createStatement()) {
      statement.executeUpdate(DELETE_ALL_SCHEDULE_CONTACTS);
    }
  }
}