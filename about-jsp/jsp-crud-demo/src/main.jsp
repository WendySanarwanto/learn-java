<%@ page import = "java.io.*, java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ taglib uri="WEB-INF/jsp-crud.tld" prefix="crud" %>

<html>
  <head><title>Welcome to our store, visitor !</title></head>
  <body bgcolor = "#f3f3f3">
    <sql:setDataSource var = "productsList" driver="com.mysql.jdbc.Driver"
      url="jdbc:mysql://172.17.0.3/estore" user="root" password="test123" />

    <sql:query dataSource="${productsList}" var="result">
      SELECT * FROM estore.products
    </sql:query>
    <h1 align="center">Welcome to our store, visitor!</h1>
    <hr />
    <h3>Our products:</h3>
    <br />

    <table border = "1" width = "100%">
      <tr>
        <th>ID</th>
        <th>Product Name</th>
        <th>Price</th>
        <th>Description</th>
      </tr>
      
      <c:forEach var="row" items="${result.rows}">
      <tr>
        <td><c:out value="${row.id}"/></td>
        <td><c:out value="${row.name}"/></td>
        <%-- <td><c:out value="${row.price}"/></td> --%>
        <td>
          <crud:Price>${row.price}</crud:Price>
        </td>
        <td><c:out value="${row.description}"/></td>
      </tr>
      </c:forEach>
    </table>
        
  </body>
</html>