<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <title>Create Product</title>
    <jsp:include page="/WEB-INF/layout/headerHtml.jsp"></jsp:include>
</head>

<body>
<div class="container-fluid position-relative d-flex p-0">
    <!-- Spinner Start -->
    <div id="spinner"
         class="show bg-dark position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
        <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>
    <!-- Spinner End -->


    <jsp:include page="/WEB-INF/layout/sidebar.jsp"></jsp:include>


    <!-- Content Start -->
    <div class="content">
        <!-- Navbar Start -->
        <jsp:include page="/WEB-INF/layout/header.jsp"></jsp:include>
        <!-- Navbar End -->
        <!-- Recent Sales Start -->
        <c:if test="${!requestScope.errors.isEmpty()&&requestScope.errors!=null }">
            <c:forEach items="${requestScope.errors}" var="item">
                <div class="alert alert-warning" role="alert">
                    <div class="alert alert-danger alert-dismissible" role="alert">
                        <button class="close" type="button" data-dismiss="alert" aria-label="Close"><span
                                class="mdi mdi-close" aria-hidden="true"></span></button>
                        <div class="icon"><span class="mdi mdi-close-circle-o"></span></div>
                        <div class="message">
                            <span>Error!</span>
                                ${item}
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>


        <c:if test="${requestScope.message!=null}">
            <div class="alert alert-success alert-dismissible" role="alert">
                <button class="close" type="button" data-dismiss="alert" aria-label="Close"><span
                        class="mdi mdi-close" aria-hidden="true"></span></button>
                <div class="icon"><span class="mdi mdi-check"></span></div>
                <div class="message">
                    <strong><i class="fa-brands fa-creative-commons-nd"></i></strong>
                        ${requestScope.message}
                </div>
            </div>
        </c:if>

        <div class="container-fluid pt-4 px-4">
            <div class="bg-secondary text-center rounded p-4">
                <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
                    <div class="col-12">
                        <div class="bg-secondary rounded p-4 p-sm-5 my-4 mx-3">
                            <form method="post" action="/product?action=create">
                                <div class="d-flex align-items-center justify-content-between mb-3">
                                    <h3 class="text-primary">Create products</h3>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control" id="title" name="title"
                                           placeholder="nhap ten san pham...">
                                    <label for="floatingInput">Title</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control" id="image" name="image" placeholder="nhap url image...">
                                    <label for="floatingInput">image:</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="number" class="form-control" id="floatingInput" name="price"
                                           placeholder="Nhập giá sản phẩm">
                                    <label for="floatingInput">Price</label>
                                </div>
                                <div class="form-floating mb-4">
                                    <input type="number" class="form-control" id="floatingPassword" name="quantity"
                                           placeholder="Nh">
                                    <label for="floatingPassword">Quantity</label>
                                </div>
                                <div class="form-floating">
                                    <%--@declare id="floatingtextarea"--%><textarea class="form-control" placeholder="Leave a comment here" name="description"
                                              id="description" style="height: 150px;"></textarea>
                                    <label for="floatingTextarea">Description</label>
                                </div>
                                <div class="d-flex align-items-center justify-content-between mb-4">
                                </div>
                                <div class="mb-3">
                                    <label for="floatingInput" class="form-label">Category</label>
                                    <select name="category">
                                        <c:forEach items="${applicationScope.listCategory}" var="category">
                                            <option value="${category.getId()}">${category.getName()}</option>
                                        </c:forEach>
                                    </select>
                                    <div class="form-text">
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary py-3 w-100 mb-4"  onclick="return confirm('Are you sure adding this product?')">Create</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/layout/scriptHtml.jsp"></jsp:include>
    </div>
</div>

<jsp:include page="/WEB-INF/layout/scriptHtml.jsp"></jsp:include>
</body>

</html>