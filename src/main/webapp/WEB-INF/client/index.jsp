<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 16/10/2023
  Time: 10:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>HomePage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

    <!-- title -->
    <title>Shop</title>

    <!-- favicon -->
    <link rel="shortcut icon" type="image/png" href="assets/img/favicon.png">
    <!-- google font -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
    <!-- fontawesome -->
    <link rel="stylesheet" href="assets/css/all.min.css">
    <!-- bootstrap -->
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <!-- owl carousel -->
    <link rel="stylesheet" href="assets/css/owl.carousel.css">
    <!-- magnific popup -->
    <link rel="stylesheet" href="assets/css/magnific-popup.css">
    <!-- animate css -->
    <link rel="stylesheet" href="assets/css/animate.css">
    <!-- mean menu css -->
    <link rel="stylesheet" href="assets/css/meanmenu.min.css">
    <!-- main style -->
    <link rel="stylesheet" href="assets/css/main.css">
    <!-- responsive -->
    <link rel="stylesheet" href="assets/css/responsive.css">
    <style>
        .list-group {
            padding-top: 50px;
            padding-right: 10px;
            padding-left: 10px;
        }

        .list-group-item.active {
            background-color: transparent;
            border: none;
        }

        .row {
            padding-top: 30px;
            padding-right: 50px;
            padding-left: 10px;
        }

        .card {
            margin-bottom: 20px;
            margin-right: 20px;
        }
    </style>
</head>
<body>
    <jsp:include page="nav.jsp"/>

    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-3">
                <div class="list-group">
                    <button type="button" class="list-group-item list-group-item-action active" aria-current="true">
                        Categories
                    </button>
                    <c:forEach items="${categoryList}" var="c">
                        <button type="button" class="list-group-item list-group-item-action">
                            <a href="client?action=selectProductByCategory&cid=${c.id}">${c.name}</a>
                        </button>
                    </c:forEach>
                    <button type="button" class="list-group-item list-group-item-action">
                        <a href="client?action=client">All Category</a>
                    </button>
                </div>
            </div>

            <div class="col-lg-9">
                <div class="row">
                    <c:forEach items="${productList}" var="p">
                        <div class="card" style="width: 18rem;">
                            <img src="${p.getImage()}" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">${p.getTitle()}</h5>
                                <p class="card-text">${p.getDescription()}</p>
                                <a href="cart?action=addToCart&id=${p.id}" class="btn btn-primary">Add To Cart</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
