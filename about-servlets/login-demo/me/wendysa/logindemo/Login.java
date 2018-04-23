package me.wendysa.logindemo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import me.wendysa.logindemo.lib.*;

public class Login extends HttpServlet {
  private static final String DEFAULT_VIEW = 
    "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n" + 
    "<html>\n"+
      "<head><title>Welcome to landing page, visitor !</title></head>" +
      "<body bgcolor = \"#f0f0f0\">\n" +
        "<form action = \"Landing\" method = \"POST\">" + 
          "<p>" +
            "<label for=\"userId\">User ID&nbsp;&nbsp;:</label>" +
            "<input type = \"text\" name = \"userId\">" +
          "</p>" +
          "<p>" + 
            "<label for=\"password\">Password:</label>" + 
            "<input type = \"password\" name = \"password\" />" +
          "</p>" + 
          "<p>" +
            "<input type = \"submit\" value = \"Submit\" />" + 
          "</p>" +
        "</form>" + 
      "</body>" +
    "</html>\n";

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
      
    // Set response content type
    response.setContentType("text/html");

    // Render default login form
    PrintWriter out = response.getWriter();
    out.println(DEFAULT_VIEW);
  }
}
