<%@page import="hr.algebra.model.User"%>
<%@page import="hr.algebra.dao.ProductDao"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.Connection"%>
<%@page import="hr.algebra.dal.sql.DataSourceSingleton"%>
<%@page import="java.util.List"%>
<%@page import="hr.algebra.dal.RepositoryFactory"%>
<%@page import="hr.algebra.model.Product"%>
<%@page import="hr.algebra.model.Product"%>
<%@page import="hr.algebra.dal.Repository"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

User auth = (User) request.getSession().getAttribute("auth");
if(auth == null || !auth.isAdmin()){
    response.sendRedirect("login.jsp");
    return;
}
    
Repository repository = RepositoryFactory.getRepository();
List<Product> products = repository.selectProizvodi();



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
                    <li class="nav-item  active">
                        <a class="nav-link" href="productsAdmin.jsp">Proizvodi<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
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
        
        <div class="container">
            
                <div class="card-header my-3">Svi proizvodi</div>
                
                <div class="row">
                    
                    <% 
                    
                        if(!products.isEmpty()){
                            for(Product p:products){%>
                                <div class="col-md-3 my-3">
                    
                                    <div class="card w-100" style="width: 18rem;">
                                        <img class="card-img-top" src="<%=p.getImage()%>" alt="Card image cap">
                                        <div class="card-body">

                                            <h5 class="card-title"><%=p.getProductName()%></h5>
                                            <h5 class="price">Cijena: <%=p.getPrice()%> kn</h5>
                                            <h6 class="category">Dostupna količina: <%=p.getAmount()%></h6>
                                            <div class="mt-3 d-flex justify-content-center">

                                                <a href="editProduct.jsp?id=<%= p.getId()%>" class="btn btn-sm">Uredi proizvod</a>

                                                <a href="delete-product?id=<%= p.getId()%>" class="btn btn-sm">Obriši proizvod</a>
                                            </div>

                                        </div>

                                    </div>
                                    
                                </div>
                            <%}
                        }

                    %>
                    
                </div>
                
        </div>
        
    </body>
</html>
