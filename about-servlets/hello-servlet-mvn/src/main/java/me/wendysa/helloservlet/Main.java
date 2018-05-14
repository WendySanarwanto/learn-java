package me.wendysa.helloservlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Main extends HttpServlet{

  private String message;

  @Override
  public void init() throws ServletException {
    message = "Hello Servlet";
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
      // Set response content type
      response.setContentType("text/html");

      // The logic goes here
      PrintWriter out = response.getWriter();
      out.println("<h1>" + message + "</h1>");
  }

  @Override
  public void destroy() {
    // Do nothing
  }
}