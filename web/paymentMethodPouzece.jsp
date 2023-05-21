<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web šop</title>
    </head>
    <body>
        <div id="login">
        <h3 class="text-center text-white pt-5">Register form</h3>
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" action="pouzece" method="post">
                            <h3 class="text-center text-info">PODACI O DOSTAVI</h3>
                            <div class="form-group">
                                <label for="username" class="text-info">ADRESA</label><br>
                                <input type="text" name="addres_of_user" id="addres" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="username" class="text-info">POŠTANSKI BROJ</label><br>
                                <input type="text" name="postanski_broj" id="product_name" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="username" class="text-info">GRAD</label><br>
                                <input type="text" name="city" id="product_category" class="form-control">
                            </div>
                            </div>
                            <div class="form-group">
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="ZAVRŠI">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
