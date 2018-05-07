package me.wendysa.jspcrud.repositories;

import java.io.IOException;
import java.sql.*;

import me.wendysa.jspcrud.models.*;
import me.wendysa.jspcrud.services.*;

// import com.fasterxml.jackson.core.JsonProcessingException;

public class ProductMySqlRepository {
  private final String jdbcUrl;

  private final static String INSERT_NEW_PRODUCT = "INSERT INTO estore.products (name, description, price ) VALUES (?, ?, ?)";

  private final static String SQLERROR_INSERT_NEW_PRODUCT = "Creating a new Product is failing. %s";

  public ProductMySqlRepository(String jdbcUrl) {
    this.jdbcUrl = jdbcUrl;
  }

  /**
   * Push product instance into repository.
   */
  public Product push(Product newProduct) 
    throws SQLException, IOException {
    try (Connection connection = DriverManager.getConnection(this.jdbcUrl)){
      // Begin Transaction
      connection.setAutoCommit(false);
      
      // Create initial save point
      Savepoint initialSavePoint = connection.setSavepoint();

      String name = newProduct.getName();
      String description = newProduct.getDescription();
      Price price = newProduct.getPrice();

      // Execute insert query using Prepared Statement
      PreparedStatement statement = connection.prepareStatement(INSERT_NEW_PRODUCT, Statement.RETURN_GENERATED_KEYS);
      
      // Insert product
      statement.setString(1, name);
      statement.setString(2, description);
      String stringifiedJsonPrice = JsonUtilService.jsonStringify(price);
      statement.setString(3, stringifiedJsonPrice);

      int affectedRow = statement.executeUpdate();

      // Throw exception if no row was inserted
      if (affectedRow == 0) {
        throw new SQLException(String.format(SQLERROR_INSERT_NEW_PRODUCT, "No rows affected."));
      }

      // Attempting for retrieving id of the created record
      try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
        if (generatedKeys.next()){
          newProduct.setId(generatedKeys.getInt(1));
        } else {
          throw new SQLException(String.format(SQLERROR_INSERT_NEW_PRODUCT, "No ID obtained."));
        }
      }

      connection.commit();
    } 

    return newProduct;
  }
}