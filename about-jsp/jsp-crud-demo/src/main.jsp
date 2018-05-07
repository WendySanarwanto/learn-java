<%@ page import = "java.io.*, java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ taglib uri="WEB-INF/jsp-crud.tld" prefix="crud" %>

<html>
  <head><title>Products Maintenance page</title></head>
  <body bgcolor = "#f3f3f3">
    <sql:setDataSource var = "productsList" driver="com.mysql.jdbc.Driver"
      url="jdbc:mysql://172.17.0.3/estore" user="root" password="test123" />

    <sql:query dataSource="${productsList}" var="result">
      SELECT * FROM estore.products
    </sql:query>
    <h1 align="center">Products Maintenance page</h1>
    <hr />
    <h3>Products List</h3>
    <br />

    <div id="formInsertContainer" style="display:none">
      <form id="formInsertProduct" class="ui form" 
        action="/ProductServlet?action=INSERT" method="post">
        <div class="field">
          <label>Product Name</label>
          <input type="text" name="name" placeholder="Product Name">
        </div>
        <div class="field">
          <label>Description</label>
          <input type="text" name="description" placeholder="Description">
        </div>
        <div class="field">
          <label>Price</label>
          <input type="text" name="priceAmount" placeholder="Amount (e.g. 200)">
          <input type="text" name="priceCurrency" placeholder="Currency (e.g. USD)">
        </div>      
        <button class="ui button" type="submit">Submit</button>
      </form>
    </div>

    <button id="btnInsert" class="ui button" type="button" onclick="onInsertButtonClicked()" >Insert</button>

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
  <script>
    function onInsertButtonClicked() {
      const formInsertContainer = document.getElementById("formInsertContainer");
      const buttonInsert = document.getElementById("btnInsert");

      if (formInsertContainer) {
        let display = formInsertContainer.style.display;
        display === 'none' ? display = 'block' : display = 'none';
        formInsertContainer.style.display = display;
      }

      if (buttonInsert) {
        let label = buttonInsert.innerHTML;
        label === 'Insert' ? label = 'Cancel' : label = 'Insert';
        buttonInsert.innerHTML = label;
      }
    }
  </script>
</html>