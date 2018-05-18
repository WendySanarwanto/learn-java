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
        action="ProductServlet">
        <div class="field">
          <label>Product Name</label>
          <input id="input-name" type="text" name="name" placeholder="Product Name">
        </div>
        <div class="field">
          <label>Description</label>
          <%-- <input id="input-description" type="text" name="description" placeholder="Description"> --%>
          <textarea id="input-description" name="description" rows="4" cols="50"></textarea>
        </div>
        <div class="field">
          <label>Price</label>
          <input id="input-price-amount" type="text" name="priceAmount" placeholder="Amount (e.g. 200)">
          <input id="input-price-currency" type="text" name="priceCurrency" placeholder="Currency (e.g. USD)">
        </div>
        <input id="input-id" type="hidden" name="id" >
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
        <th>Actions</th>
      </tr>
      
      <c:forEach var="row" items="${result.rows}">
      <tr>
        <td><c:out value="${row.id}"/></td>
        <td><c:out value="${row.name}"/></td>
        <%-- <td><c:out value="${row.price}"/></td> --%>
        <td id="col-price-${row.id}">
          <crud:Price>${row.price}</crud:Price>
        </td>
        <td><c:out value="${row.description}"/></td>
        <td>
          <button id="btnDelete" class="ui button" type="button" 
              onclick="onDeleteItemClicked(${row.id})">
              Delete
          </button>
          <button id="btnEdit" class="ui button" type="button"
              onclick="onEditItemClicked(${row.id}, `${row.name}`, `${row.description}`, `col-price-${row.id}`)">
              Edit
          </button>
        </td>
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
        formInsertContainer.style.display = display === 'none' ? 'block' : 'none';
        display = formInsertContainer.style.display;

        const formInsertProduct = document.getElementById("formInsertProduct");
        if (formInsertProduct) {
          formInsertProduct.method = display === 'block' ? 'post' : undefined;
          formInsertProduct.action = 'ProductServlet';
        }
      }

      if (buttonInsert) {
        let label = buttonInsert.innerHTML;
        label === 'Insert' ? label = 'Cancel' : label = 'Insert';
        buttonInsert.innerHTML = label;
      }

      initialiseInputs();
    }

    function onDeleteItemClicked(id) {
      const xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
        const readyState = this.readyState;
        const status = this.status;
        if (readyState ===4) {
          if (status === 200) {
            window.location = "/index.jsp";
          }
        }
        // console.log(`[DEBUG] - <index.jsp.onDeleteItemClicked.onreadystatechange> readyState: `+readyState+`, status: `, status);
      };
      xhttp.open("DELETE", "ProductServlet?id="+id, true);
      xhttp.send(null);
    }

    function onEditItemClicked(id, name, description, priceColId) {
      const formInsertContainer = document.getElementById("formInsertContainer");
      const buttonInsert = document.getElementById("btnInsert");

      if (formInsertContainer) {
        formInsertContainer.style.display = 'block';

        const formInsertProduct = document.getElementById("formInsertProduct");
        if (formInsertProduct) {
          formInsertProduct.method = 'post';
          formInsertProduct.action = 'ProductServlet?isEdit=true&returnJson=false';
        }
      }

      if (buttonInsert) {
        buttonInsert.innerHTML = 'Cancel';
      }      

      const tdPriceCol = document.getElementById(priceColId);
      const price = tdPriceCol ? tdPriceCol.innerText : "0 USD";
      let [priceAmount, priceCurrency] = price.split(' ');

      initialiseInputs(id, name, description, priceAmount, priceCurrency);
    }

    function initialiseInputs(id='', name='', description='', priceAmount='', priceCurrency=''){
      const inputId = document.getElementById('input-id');
      inputId.value = id;
      const inputName = document.getElementById('input-name');
      inputName.value = name;
      const inputDescription = document.getElementById('input-description');
      inputDescription.value = description;
      const inputPriceAmount = document.getElementById('input-price-amount');
      inputPriceAmount.value = priceAmount;
      const inputPriceCurrency = document.getElementById('input-price-currency');
      inputPriceCurrency.value = priceCurrency;
    }
  </script>
</html>