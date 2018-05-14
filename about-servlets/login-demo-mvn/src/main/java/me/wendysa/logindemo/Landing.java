package me.wendysa.logindemo;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import me.wendysa.logindemo.lib.*;

public class Landing extends HttpServlet {
  public static final IdentityManager identityManager = new IdentityManager();
  private static final String USER_ID_KEY = "userId";
  private static final String LANDING_VIEW_VISITOR = "<!doctype html public \"-//w3c//dtd html 4.0 "
      + "transitional//en\">\n" + "<html>\n" + "<head><title>Welcome to landing page, visitor !</title></head>"
      + "<body bgcolor = \"#f0f0f0\">\n" + "<h1 align=\"center\">Welcome to landing page, visitor !</h1>\n" + "<hr/>\n"
      + "<p>Please login using valid user id and correct password, if you have registered on our site.</p>\n" + "<p>"
      + "<a href=\"Login\">" + "<span>Login</span>" + "</a>" + "</p>" + "</body>" + "</html>\n";

  private static final String LANDING_VIEW_AUTHENTICATED = "<!doctype html public \"-//w3c//dtd html 4.0 "
      + "transitional//en\">\n" + "<html>\n" + "<head><title>Welcome to landing page, %s !</title></head>"
      + "<body bgcolor = \"#f0f0f0\">\n" + "<h1 align=\"center\">Welcome to landing page, %s !</h1>\n" + "<hr/>\n"
      + "<p>You are seeing this landing page because you're authenticated our user now.</p>\n" + "<p>"
      + "<a href=\"Logout\">" + "<span>Logout</span>" + "</a>" + "</p>" + "</body>" + "</html>\n";

  @Override
  public void init() {
    // Initialise accounts data
    Landing.identityManager.createAccount("wendy_s", "test123");
    Landing.identityManager.createAccount("diana_p", "123test");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Set response content type
    response.setContentType("text/html");

    // Inspect the session, specified by userId
    HttpSession session = request.getSession(true);
    String userId = (String) session.getAttribute(USER_ID_KEY);

    // Do we have valid authToken
    String authToken = null;
    if ((userId != null) && (userId != "")) {
      authToken = (String) session.getAttribute(userId);
    }

    PrintWriter out = response.getWriter();
    if ((authToken == null) || (authToken.trim().equals(""))) {
      // Render landing page for unauthenticated visitor
      out.println(LANDING_VIEW_VISITOR);
    } else {
      String html = String.format(LANDING_VIEW_AUTHENTICATED, userId, userId);
      // Render landing page for Authenticated visitor
      out.println(html);
    }
  }

  @Override
  public void destroy() {
    // TODO: Cleanup temporary storages
  }
}
