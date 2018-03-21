package me.wendysa.formsubmission;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Main extends HttpServlet{

  private static final String SUBMITTED_CONTACT_VIEW = 
    "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n" + 
    "<html>\n"+
      "<head><title>%s</title></head>" +
      "<body bgcolor = \"#f0f0f0\">\n" +
        "<h1 align=\"center\">%s</h1>\n" +
        "<ul>\n" +
          "<li><b>Name&nbsp;</b>: %s</li>" +
          "<li><b>Email</b>: %s</li>" +
          "<li><b>Type&nbsp;</b>: %s</li>" +
        "</ul>\n" +
        "<p>" +          
          "<a href=\"ContactEntry.html\">"  +
            "<span>Back to Entry form.</span>"  +
          "</a>" +
        "</p>" +
      "</body>" +
    "</html>\n";

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
      // Set response content type
      response.setContentType("text/html");

      // The logic goes here
      String title = "Submitted Contact";
      PrintWriter out = response.getWriter();
      String name = request.getParameter("name");
      name = name == null ? "-" : name;
      String email = request.getParameter("email");
      email = email == null ? "-" : email;
      String type = request.getParameter("type");
      type = type == null ? "-" : type;

      String html = String.format(SUBMITTED_CONTACT_VIEW, title, title, name, email, type );
      out.println(html);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      doGet(request, response);
  }

}
