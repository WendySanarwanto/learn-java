package me.wendysa.contactsdemo.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import me.wendysa.contactsdemo.contracts.IRepository;
import me.wendysa.contactsdemo.models.Contact;

public class ContactMysqlRepository implements IRepository<Contact> {
  private final String jdbcUrl;

  private final static String INSERT_NEW_CONTACT = "INSERT INTO contact (name, email, `type`) VALUES (?, ?, ?)";
  private final static String SELECT_ALL_CONTACTS = "SELECT id, name, email, `type` FROM `contacts-demo`.contact";
  
  private final static String SQLERROR_INSERT_NEW_CONTACT = "Creating a new Contact with { name:%s, email:%s, type: %s } is failing. %s";

  public ContactMysqlRepository(String jdbcUrl) {
    this.jdbcUrl = jdbcUrl;
  }

  /**
   * Insert newContact into database via JDBC 
   */
  public Contact push(Contact newContact) {
    try (Connection connection = DriverManager.getConnection(this.jdbcUrl)) {
      // Execute insert query using Prepared Statement
      PreparedStatement statement = connection.prepareStatement(INSERT_NEW_CONTACT, Statement.RETURN_GENERATED_KEYS);
      
      String name = newContact.getName();
      String email = newContact.getEmail();
      Contact.Type type = newContact.getType();

      statement.setString(1, name);
      statement.setString(2, email);
      statement.setString(3, type.toString());
      
      int affectedRow = statement.executeUpdate();

      // Throw exception if no row was inserted
      if (affectedRow == 0){
        throw new SQLException(String.format(SQLERROR_INSERT_NEW_CONTACT, name, email, type.toString(), "No rows affected."));
      }

      // Attempting for retrieving id of the created record
      try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
        if (generatedKeys.next()){
          newContact.setId(generatedKeys.getInt(1));
        } else {
          throw new SQLException(String.format(SQLERROR_INSERT_NEW_CONTACT, name, email, type.toString(), "No ID obtained."));
        }
      }
    } catch(SQLException sqlErr) {
      sqlErr.printStackTrace();
      // TODO: Do proper thing against the sqlErr.
      return null;
    }

    return newContact;
  }

  public List<Contact> getAll() {
    List<Contact> results = null;

    try (Connection connection = DriverManager.getConnection(this.jdbcUrl)) {
      Statement statement = connection.createStatement();
      try (ResultSet rs = statement.executeQuery(SELECT_ALL_CONTACTS)) {
        results = new ArrayList<Contact>();

        while(rs.next()){
          int id = rs.getInt("id");
          String name = rs.getString("name");
          String email = rs.getString("email");
          String stringType = rs.getString(4);

          Contact contact = new Contact(name, email);
          contact.setId(id);
          contact.setType(Contact.Type.valueOf(stringType));

          results.add(contact);
        }
      }
    } catch(SQLException sqlErr) {
      sqlErr.printStackTrace();
      // TODO: Do proper thing against the sqlErr.
    }

    return results;
  }
};
