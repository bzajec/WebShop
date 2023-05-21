<%@page import="hr.algebra.model.User"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="hr.algebra.dao.ProductDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="hr.algebra.model.Cart"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.Connection"%>
<%@page import="hr.algebra.dal.sql.DataSourceSingleton"%>
<%@page import="java.util.List"%>
<%@page import="hr.algebra.model.Product"%>
<%@page import="hr.algebra.dal.RepositoryFactory"%>
<%@page import="hr.algebra.dal.RepositoryFactory"%>
<%@page import="hr.algebra.dal.Repository"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    User auth = (User) request.getSession().getAttribute("auth");
if(auth == null || !auth.isAdmin()){
    response.sendRedirect("login.jsp");
    return;
}

    int id = 0;
    try {
        id = Integer.parseInt(request.getParameter("id"));
    } catch (Exception e) {
        response.sendRedirect("editProduct.jsp");
    }
    if (id == 0) {
        response.sendRedirect("editProduct.jsp");
    }
    Product proizvod = new Product();
    DataSource ds = DataSourceSingleton.getInstance();
    Connection con = ds.getConnection();

    Statement stm = con.createStatement();
    List<Product> products = new ArrayList();

    String query = "select * from Proizvod";

    ResultSet rs = stm.executeQuery(query);
    while (rs.next()) {
        products.add(new Product(rs.getInt("IDProizvod"), rs.getString("Naziv"), rs.getInt("KategorijaID"), rs.getString("Boja"), rs.getInt("Cijena"), rs.getInt("Kolicina"), rs.getString("Slika"), rs.getString("Opis")));
    }
    for (Product p : products) {
        if (p.getId() == id) {
            proizvod = p;
        }
    }

%>

<!DOCTYPE html>
<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
                    <li class="nav-item">
                        <a class="nav-link" href="#">Prošle narudžbe</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="addProduct.jsp">Dodaj proizvod</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="addCategory.jsp">Dodaj kategoriju</a>
                    </li>
                </ul>
            </div>
        </nav>

    </header>

    <body>
    <div id="login">
        <h3 class="text-center text-white pt-5">Register form</h3>
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" action="edit-product" method="post">
                            <h3 class="text-center text-info">UREDI PROIZVOD</h3>
                            <div class="form-group">
                                <label for="username" class="text-info">ID PROIVOD</label><br>
                                <input type="text" name="id" id="id" class="form-control" value="<%=proizvod.getId()%>">
                            </div>
                            <div class="form-group">
                                <label for="username" class="text-info">NAZIV</label><br>
                                <input type="text" name="product_name" id="product_name" class="form-control" value="<%=proizvod.getProductName()%>">
                            </div>
                            <div class="form-group">
                                <label for="username" class="text-info">KATEGORIJA ID</label><br>
                                <input type="text" name="product_category" id="product_category" class="form-control" value="<%=proizvod.getCategoryID()%>">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">BOJA</label><br>
                                <input type="text" name="product_color" id="product_color" class="form-control" value="<%=proizvod.getColor()%>">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">CIJENA</label><br>
                                <input type="text" name="product_price" id="product_price" class="form-control" value="<%=proizvod.getPrice()%>">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">KOLIČINA</label><br>
                                <input type="text" name="product_amount" id="product_amount" class="form-control" value="<%=proizvod.getAmount()%>">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">SLIKA</label><br>
                                <input type="text" name="product_picture" id="product_picture" class="form-control" value="<%=proizvod.getImage()%>">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">OPIS</label><br>
                                <input type="text" name="product_description" id="product_description" class="form-control" value="<%=proizvod.getDescription()%>">
                            </div>
                            <div class="form-group">
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="PROMJENI">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
