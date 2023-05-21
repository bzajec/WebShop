<%@page import="hr.algebra.model.User"%>
<%@page import="hr.algebra.model.FinalBill"%>
<%@page import="hr.algebra.dao.FinalBillDao"%>
<%@page import="hr.algebra.model.FinalStavka"%>
<%@page import="hr.algebra.dao.FinalOrderDao"%>
<%@page import="hr.algebra.model.Order"%>
<%@page import="java.util.List"%>
<%@page import="hr.algebra.dao.OrderDao"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="hr.algebra.dal.sql.DataSourceSingleton"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth == null || !auth.isAdmin()){
    response.sendRedirect("login.jsp");
    return;
    }
    
    DataSource ds = DataSourceSingleton.getInstance();
    Connection con = ds.getConnection();
    
    FinalBillDao dao = new FinalBillDao(con);
    
    List<FinalBill> bills = dao.getAllFinalBills();

%>

<!DOCTYPE html>
<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web šop</title>
    </head>
    
    <header class="container">

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Web shop</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="productsAdmin.jsp">Proizvodi<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="allOrders.jsp">Prošle narudžbe</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="addProduct.jsp">Dodaj proizvod</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="addCategory.jsp">Dodaj kategoriju</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="users.jsp">Korisnici</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="categories.jsp">Kategorije</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>

    </header>
    
    <body class="container">
                <input style="text" id="myInputFirstName" onkeyup="myFunctionFirstName()" placeholder="Search first name"></input>
                <input style="text" id="myInputLastName" onkeyup="myFunctionLastName()" placeholder="Search last name"></input>
                <input style="text" id="myInputDate" onkeyup="myFunctionDate()" placeholder="Search date"></input>
        <table class="table" id="myTable">
            <thead>
                <tr>
                    <th scope="col">IDRacun</th>
                    <th scope="col">Ime</th>
                    <th scope="col">Prezime</th>
                    <th scope="col">Nacin placanja</th>
                    <th scope="col">Datum kupovine</th>
                </tr>
            </thead>
            <tbody>
                <%
			if (!bills.isEmpty()) {
				for (FinalBill fB : bills) {
			%>
                <tr>
                    <td><%=fB.getBillID()%></td>
                    <td><%=fB.getCustomerFirstName()%></td>
                    <td><%=fB.getCustomerLastName()%></td>
                    <td><%=fB.getTypeOfPayment()%></td>
                    <td><%=fB.getDateOfPurchase()%></td>
                    <td>
                        <a href="billDetailsAdmin.jsp?id=<%= fB.getBillID()%>" class="btn btn-sm">Više</a>
                    </td>
                </tr>
                <%
			}
			} else {
			out.println("Nema korisnika");
			}
			%>
            </tbody>
        </table>
    </body>
    
    <script>
        
        function myFunctionFirstName() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInputFirstName");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}

    function myFunctionLastName() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInputLastName");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[2];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}

    function myFunctionDate() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInputDate");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[4];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}
        
        
    </script>
</html>
