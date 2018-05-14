package me.wendysa.logindemo;

import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import me.wendysa.logindemo.Landing;
import me.wendysa.logindemo.lib.*;

public class Logout extends HttpServlet{
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    session.invalidate();

    // Cookie[] cookies = request.getCookies();
    // if ( (cookies != null) && (cookies.length > 0)) {
    //   Cookie jsessionIdCookie = 
    //     Arrays.stream(cookies)
    //       .filter(_cookie -> _cookie.getName().equals("JSESSIONID"))
    //       .findFirst()
    //       .orElse(null);
    //   if (jsessionIdCookie != null) {
    //     jsessionIdCookie.setMaxAge(0);
    //     response.addCookie(jsessionIdCookie);
    //   }
    // }

    // Redirect to landing page
    response.sendRedirect("Landing");
  }
}