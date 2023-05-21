<%@page import="java.util.ArrayList"%>
<%@page import="hr.algebra.model.Category"%>
<%@page import="java.util.List"%>
<%@page import="hr.algebra.dal.sql.SqlRepository"%>
<%@page import="hr.algebra.dal.sql.DataSourceSingleton"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="hr.algebra.dal.Repository" %>
<%@page import="hr.algebra.dal.RepositoryFactory" %>
<%@page import="hr.algebra.model.Product" %>

<%
    
    Repository repository = RepositoryFactory.getRepository();
    List<Product> products = repository.selectProizvodi();
    
%>
<!DOCTYPE html>
<html lang="en">
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
                        <a class="nav-link active" href="products.jsp">Proizvodi</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="userOrders.jsp"">Prošle narudžbe</a>
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
        <div class="container">

            <div class="card-header my-3">Svi proizvodi</div>

            <div class="row">

                <%                        if (!products.isEmpty()) {
                        for (Product p : products) {
                            if(p.getAmount()!=0){
                
                %>
                <div class="col-md-3 my-3">

                    <div class="card w-100" style="width: 18rem;">
                        <img class="card-img-top" src="<%=p.getImage()%>" alt="Card image cap">
                        <div class="card-body">

                            <h5 class="card-title"><%=p.getProductName()%></h5>
                            <h5 class="price">Cijena: <%=p.getPrice()%> kn</h5>
                            <h6 class="category">Dostupna količina: <%=p.getAmount()%></h6>
                            <div class="mt-3 d-flex justify-content-between">

                                <a href="to-cart?id=<%= p.getId()%>" class="btn btn-primary">Dodaj u kosaricu</a>

                            </div>

                        </div>

                    </div>

                </div>
                <%}}
                    }

                %>

            </div>

        </div>

    </body>

</html>
