<%@ page isThreadSafe="true" 
    contentType="utf8" 
    info="Created by Wendy Sanarwanto (wendy.sanarwanto@gmail.com)"
    session="true" %>

<html>
  <head><title>Hello World</title></head>

  <body>
    Hello World! <br/>
    <%
      out.println("Your IP address is " + request.getRemoteAddr());
    %>
    <p>Today's date: <%= (new java.util.Date()).toLocaleString() %></p>
  </body>
</html>