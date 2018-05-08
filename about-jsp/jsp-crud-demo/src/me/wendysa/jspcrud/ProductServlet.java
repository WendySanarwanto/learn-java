package me.wendysa.jspcrud;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import me.wendysa.jspcrud.models.*;
import me.wendysa.jspcrud.repositories.*;

public class ProductServlet extends HttpServlet {
  private static final String JDBC_LOCAL_URL = "jdbc:mysql://172.17.0.3:3306/estore?user=root&password=test123&useSSL=false";

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
    try { 
      insertNewRecord(request);
      response.sendRedirect("main.jsp");
    } catch(SQLException | IOException err) {
      getServletContext().log("An exception occured", err);
    }
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
    try{
      deleteRecord(request);
      // response.sendRedirect("main.jsp");
    } catch(SQLException | IOException err) {
      getServletContext().log("An exception occured", err);
    }
  }
  
  /**
   * A helper for deleting a record.
   */
  private void deleteRecord(HttpServletRequest request) 
    throws SQLException, IOException {
      String rawId = request.getParameter("id");
      int id = rawId != null ? Integer.parseInt(rawId) : -1;
      if (id == -1) {
        return;
      }

      ProductMySqlRepository productRepo = new ProductMySqlRepository(JDBC_LOCAL_URL);
      productRepo.remove(id);
  }

  /**
   * A helper for inserting a new record into database
   */
  private Product insertNewRecord(HttpServletRequest request)
    throws SQLException, IOException {
    String name = request.getParameter("name");
    String description = request.getParameter("description");
    String priceAmount= request.getParameter("priceAmount");
    int amount = Integer.parseInt(priceAmount);
    String priceCurrency = request.getParameter("priceCurrency");

    Product newProduct = new Product();
    newProduct.setName(name);
    newProduct.setDescription(description);
    Price price = new Price();
    price.setAmount(amount);
    price.setCurrency(priceCurrency);
    newProduct.setPrice(price);

    ProductMySqlRepository productRepo = new ProductMySqlRepository(JDBC_LOCAL_URL);
    return productRepo.push(newProduct);
  }
}