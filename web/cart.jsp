<%@page import="java.text.DecimalFormat"%>
<%@page import="hr.algebra.dao.ProductDao"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.Connection"%>
<%@page import="hr.algebra.dal.sql.DataSourceSingleton"%>
<%@page import="hr.algebra.dal.RepositoryFactory"%>
<%@page import="hr.algebra.dal.Repository"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="hr.algebra.model.Cart"%>
<%@page import="hr.algebra.model.Product" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);

    DataSource ds = DataSourceSingleton.getInstance();
    Connection con = ds.getConnection();

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct = null;
    if (cart_list != null) {
        ProductDao pD = new ProductDao(con);
        cartProduct = pD.getCartProducts(cart_list);
        double totalPrice = pD.getTotalCartPrice(cart_list);
        request.setAttribute("cart_list", cart_list);
        request.setAttribute("totalPrice", totalPrice);
    }

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
                        <a class="nav-link" href="products.jsp">Proizvodi</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="userOrders.jsp"">Prošle narudžbe</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="cart.jsp">Košarica<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>

    </header>

    <div class="container my-3">
        <div class="d-flex py-3"><h3>Total Price:  ${totalPrice} </h3> <a class="mx-3 btn btn-primary" href="check-out">Check Out</a></div>
        <table class="table table-light">
            <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Category</th>
                    <th scope="col">Price</th>
                    <th scope="col">Buy Now</th>
                    <th scope="col">Cancel</th>
                </tr>
            </thead>
            <tbody>
                <% if (cart_list != null) {

                                            for (Cart c : cartProduct) {%>
                <tr>
                    <td><%=c.getProductName()%></td>
                    <td><%=c.getCategoryID()%></td>
                    <td><%=c.getPrice()%></td>
                    <td>
                        <form action="order-now" method="post" class="form-inline">
                            <input type="hidden" name="id" value="<%= c.getId()%>" class="form-input">
                            <div class="form-group d-flex justify-content-between">
                                <a class="btn bnt-sm btn-incre btn-danger" href="inc-dec?action=dec&id=<%=c.getId()%>"><i class="fas fa-plus-square"></i></a> 
                                <input type="text" name="quantity" class="form-control"  value="<%=c.getQuantity()%>" readonly> 
                                <a class="btn btn-sm btn-decre btn-danger" href="inc-dec?action=inc&id=<%=c.getId()%>"><i class="fas fa-minus-square"></i></a>
                            </div>
                            
                        </form>
                    </td>
                    <td><a href="remove-item?id=<%=c.getId()%>" class="btn btn-sm btn-danger">Remove</a></td>
                </tr>
                <%}

                                            }%>
            </tbody>
        </table>
    </div>
</body>
</html>
