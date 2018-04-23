package me.wendysa.logindemo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import me.wendysa.logindemo.Landing;
import me.wendysa.logindemo.lib.*;

public class Logout extends HttpServlet{
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
    // Get cookies
    CookieService cookieService = new CookieService(response); 
    Cookie[] cookies = request.getCookies();

    if ( (cookies != null) && (cookies.length > 0) ) {
      for(Cookie cookie: cookies){
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        // Remove from AuthToken Storage
        String cookieName = cookie.getName();
        Landing.identityManager.removeAuthToken(cookieName);
      }
    }

    // Redirect to landing page
    response.sendRedirect("Landing");
  }
}