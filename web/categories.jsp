<%@page import="hr.algebra.model.User"%>
<%@page import="hr.algebra.dal.RepositoryFactory"%>
<%@page import="hr.algebra.dal.Repository"%>
<%@page import="hr.algebra.model.Category"%>
<%@page import="java.util.List"%>
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
    Repository repository = RepositoryFactory.getRepository();
    List<Category> categories = repository.selectKategorije();

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
                    <li class="nav-item active">
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
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">ID KATEGORIJA</th>
                    <th scope="col">NAZIV</th>
                    <th scope="col">MOGUĆNOSTI</th>
                </tr>
            </thead>
            <tbody>
                <%
			if (!categories.isEmpty()) {
				for (Category c : categories) {
			%>
                <tr>
                    <td><%=c.getId()%></td>
                    <td><%=c.getCategoryName()%></td>
                    <td>
                       <a href="editCategory.jsp?id=<%= c.getId()%>" class="btn btn-sm">Uredi</a>

                       <a href="delete-category?id=<%= c.getId()%>" class="btn btn-sm">Obriši</a>
                    
                    </td> 
                </tr>
                
                <%
			}
			} else {
			out.println("Nema kategorija");
			}
			%>
                             
            </tbody>
        </table>
    </body>
</html>
