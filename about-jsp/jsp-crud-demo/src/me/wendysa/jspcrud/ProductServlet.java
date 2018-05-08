package me.wendysa.jspcrud;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import me.wendysa.jspcrud.models.*;
import me.wendysa.jspcrud.repositories.*;
import me.wendysa.jspcrud.services.JsonUtilService;

public class ProductServlet extends HttpServlet {
  private static final String JDBC_LOCAL_URL = "jdbc:mysql://172.17.0.3:3306/estore?user=root&password=test123&useSSL=false";
  private ProductMySqlRepository productRepo = new ProductMySqlRepository(JDBC_LOCAL_URL);

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
    String rawIsEdit = request.getParameter("isEdit");
    boolean isEdit = rawIsEdit != null && rawIsEdit != "" ? Boolean.parseBoolean(rawIsEdit) : false;
    if (isEdit) {
      doPut(request, response);
      return;
    }

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
  
  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
    try {
      String stringifiedUpdatedRecord = updateRecord(request);
      String rawReturnJson = request.getParameter("returnJson");
      Boolean returnJson = rawReturnJson != null && rawReturnJson != "" ? Boolean.parseBoolean(rawReturnJson) : false;

      if (returnJson) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(stringifiedUpdatedRecord);
      } else {
        response.sendRedirect("main.jsp");
      }
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
      productRepo.remove(id);
  }

  /**
   * A helper for updating a modified product record into database
   */
  private String updateRecord(HttpServletRequest request) 
    throws SQLException, IOException {    
    Product changedProduct = parseRequestAsProduct(request);
    changedProduct = productRepo.update(changedProduct);
    return JsonUtilService.jsonStringify(changedProduct);
  } 

  /**
   * A helper for inserting a new record into database
   */
  private Product insertNewRecord(HttpServletRequest request)
    throws SQLException, IOException {
    Product newProduct = parseRequestAsProduct(request);
    return productRepo.push(newProduct);
  }

  /**
   * A helper for parsing request parameters into Product POJO
   */
  private Product parseRequestAsProduct(HttpServletRequest request){
    String rawId = request.getParameter("id");
    int id = rawId != null && rawId != ""? Integer.parseInt(rawId) : -1;
    String name = request.getParameter("name");
    String description = request.getParameter("description");
    String priceAmount= request.getParameter("priceAmount");
    int amount = Integer.parseInt(priceAmount);
    String priceCurrency = request.getParameter("priceCurrency");

    Product product = new Product();
    product.setName(name);
    product.setDescription(description);
    Price price = new Price();
    price.setAmount(amount);
    price.setCurrency(priceCurrency);
    product.setPrice(price);
    if (id > -1) {
      product.setId(id);
    }
    return product;
  }
}