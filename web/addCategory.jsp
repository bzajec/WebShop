<%@page import="hr.algebra.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%

User auth = (User) request.getSession().getAttribute("auth");
if(auth == null || !auth.isAdmin()){
    response.sendRedirect("login.jsp");
    return;
}

%>

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
                    <li class="nav-item">
                        <a class="nav-link" href="allOrders.jsp">Prošle narudžbe</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="addProduct.jsp">Dodaj proizvod</a>
                    </li>
                    <li class="nav-item active">
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
        
         <div id="add-category">
        <h3 class="text-center text-white pt-5">Register form</h3>
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" action="add-category" method="post">
                            <h3 class="text-center text-info">DODAJ KATEGORIJU</h3>
                            
                            <div class="form-group">
                                <label for="username" class="text-info">NAZIV KATEGORIJE</label><br>
                                <input type="text" name="category_name" id="category_name" class="form-control">
                            </div>
                             <div class="form-group">
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="DODAJ">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
        
    </body>
</html>
