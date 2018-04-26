<%@ page isThreadSafe="true" %>

<html>
  <head>
    <title>Using Javabeans in JSP</title>
  </head>

  <body>
    <center>
      <h2>Using Javabeans in JSP</h2>
      <jsp:useBean id="demo" class="me.wendysa.beandemo.DemoBean" />
      <jsp:setProperty name="demo" property="message" value="Hello JSP Bean!" />

      <p>Got message ...</p>
      <jsp:getProperty name="demo" property="message" />
    </center>
  </body>
</html>