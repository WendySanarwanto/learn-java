package me.wendysa.logindemo;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.*;
import javax.servlet.http.*;

import me.wendysa.logindemo.lib.*;

public class Landing extends HttpServlet{
  private static final IdentityManager identityManager = new IdentityManager();
  private static final String USER_ID_KEY = "userId";
  private static final String LANDING_VIEW_VISITOR = 
    "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n" + 
    "<html>\n"+
      "<head><title>Welcome to landing page, visitor !</title></head>" +
      "<body bgcolor = \"#f0f0f0\">\n" +
        "<h1 align=\"center\">Welcome to landing page, visitor !</h1>\n" +
        "<hr/>\n" +
        "<p>Please login using valid user id and correct password, if you have registered on our site.</p>\n" +
        "<p>" +
          "<a href=\"/Login.html\">"  +
            "<span>Login</span>"  +
          "</a>" +
        "</p>" +
      "</body>" +
    "</html>\n";
  
  private static final String LANDING_VIEW_AUTHENTICATED = 
    "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n" + 
    "<html>\n"+
      "<head><title>Welcome to landing page, %s !</title></head>" +
      "<body bgcolor = \"#f0f0f0\">\n" +
        "<h1 align=\"center\">Welcome to landing page, %s !</h1>\n" +
        "<hr/>\n" +
        "<p>You are seeing this landing page because you're authenticated our user now.</p>\n" +
        "<p>" +
          "<a href=\"/Logout\">"  +
            "<span>Logout</span>"  +
          "</a>" +
        "</p>" +
      "</body>" +
    "</html>\n";

  private static final int COOKIE_MAX_AGE = 60; // Sixty seconds

  @Override 
  public void init() {
    // Initialise accounts data    
    Landing.identityManager.createAccount("wendy_s", "test123");
    Landing.identityManager.createAccount("diana_p", "123test");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
      // Set response content type
      response.setContentType("text/html");

      // Inspect the cookie, specified by userid
      CookieService cookieService = new CookieService(response); 
      Cookie[] cookies = request.getCookies();
      String userId = request.getParameter(USER_ID_KEY);
      Cookie cookie = cookieService.getCookieByKey(userId, cookies);
      
      // Do we have a valid cookie ?
      PrintWriter out = response.getWriter();
      if (isCookieValid(cookie)) {
        userId = cookie.getName();
        String html = String.format(LANDING_VIEW_AUTHENTICATED, userId, userId);
        // Render landing page for Authenticated visitor
        out.println(html);
      } else {
        // Render landing page for unauthenticated visitor
        out.println(LANDING_VIEW_VISITOR);
      }
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
        // Should the login is failing , redirect back to Login.html. 
        response.sendRedirect("Login.html");
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

  @Override
  public void destroy() {
    // TODO: Cleanup temporary storages
  }

  /**
   * A helper to check the authToken inside cookie, whether it's still valid or not.
   */
  boolean isCookieValid(Cookie cookie) {
    if (cookie == null) {
      return false;
    }

    String authToken = cookie.getValue();
    String userId = cookie.getName();
    return Landing.identityManager.getAuthToken(userId).equals(authToken);
  }
}
