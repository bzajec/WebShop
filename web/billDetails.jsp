<%@page import="hr.algebra.model.FinalStavka"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="hr.algebra.dao.FinalOrderDao"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="hr.algebra.dal.sql.DataSourceSingleton"%>
<%@page import="hr.algebra.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

User auth = (User) request.getSession().getAttribute("auth");
if(auth == null){
    response.sendRedirect("login.jsp");
    return;
}
int id = 0;
    try {
        id = Integer.parseInt(request.getParameter("id"));
    } catch (Exception e) {
        response.sendRedirect("userOrders.jsp");
    }
    if (id == 0) {
        response.sendRedirect("userOrders.jsp");
    }
    
    DataSource ds = DataSourceSingleton.getInstance();
    Connection con = ds.getConnection();
    
    FinalOrderDao dao = new FinalOrderDao(con);
    List<FinalStavka> finalOrders = dao.getAllFinalOrders();

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
                        <a class="nav-link" href="products.jsp">Proizvodi</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="userOrders.jsp">Prošle narudžbe</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="cart.jsp">Košarica<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>

    </header>
    
        <body class="container">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">IDRacun</th>
                    <th scope="col">IDStavka</th>
                    <th scope="col">NazivProizvoda</th>
                    <th scope="col">Kolicina</th>
                    <th scope="col">Cijena</th>
                </tr>
            </thead>
            <tbody>
                <%
			if (!finalOrders.isEmpty()) {
				for (FinalStavka fS : finalOrders) {
                                if(fS.getFinalBillID() == Integer.parseInt(request.getParameter("id")))    
                                {
			%>
                <tr>
                    <td><%=fS.getStavkaID()%></td>
                    <td><%=fS.getFinalBillID()%></td>
                    <td><%=fS.getProductName()%></td>
                    <td><%=fS.getQuantity()%></td>
                    <td><%=fS.getTotalPrice()%></td>
                    
                </tr>
                <%}
			}
			} else {
			out.println("Nema korisnika");
			}
			%>
            </tbody>
        </table>
    </body>
</html>
