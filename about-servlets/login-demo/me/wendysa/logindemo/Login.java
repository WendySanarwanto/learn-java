package me.wendysa.logindemo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import me.wendysa.logindemo.lib.*;

public class Login extends HttpServlet {
  private boolean shouldRedirectToLanding = false;
  private static final String USER_ID_KEY = "userId";
  private static final int COOKIE_MAX_AGE = 1800; // 30 minutes  
  private static final String DEFAULT_VIEW = 
    "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n" + 
    "<html>\n"+
      "<head><title>Welcome to landing page, visitor !</title></head>" +
      "<body bgcolor = \"#f0f0f0\">\n" +
        "<form action = \"Login\" method = \"POST\">" + 
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
  public void init() {
    // Initialise accounts data
    if (Landing.identityManager == null) {
      shouldRedirectToLanding = true;
    }
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
      
    // Set response content type
    response.setContentType("text/html");

    // Redirect to Landing if identityManager has not been created
    if (shouldRedirectToLanding) {
      response.sendRedirect("Landing");
      return;
    }

    // Render default login form
    PrintWriter out = response.getWriter();
    out.println(DEFAULT_VIEW);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      String userId = request.getParameter(USER_ID_KEY);
      userId = userId == null ? "" : userId;
      String password = request.getParameter("password");
      password = password == null ? "" : password;
      
      // Authenticate submitted userId & password
      if (!Landing.identityManager.doAuthenticate(userId, password)) {
        // Should the login is failing , redirect back to Login. 
        response.sendRedirect("Login");
        return;
      } 
      
      // Should the login is success, create a random string 
      //       then put both userId and random string into a Cookie. 
      //       ensure that the cookie is expired in a minute
      String authToken = Landing.identityManager.getAuthToken(userId);

      CookieService cookieService = new CookieService(response); 
      cookieService.keepValueKeyPair(authToken, userId, COOKIE_MAX_AGE);

      // When the login is success, redirect to the Landing servlet with query parameter equal to the random string
      // doGet(request, response);
      response.sendRedirect("Landing?userId="+userId);
  }  
}
