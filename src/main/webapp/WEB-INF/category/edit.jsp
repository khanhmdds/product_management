<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <title>Edit</title>
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
                    <strong><i class="fa-solid fa-ban"></i></strong>
                        ${requestScope.message}
                </div>
            </div>
        </c:if>

        <!-- Recent Sales Start -->

        <div class="container-fluid pt-4 px-4">
            <div class="bg-secondary text-center rounded p-4">
                <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
                    <div class="col-12">
                        <div class="bg-secondary rounded p-4 p-sm-5 my-4 mx-3">
                            <form method="post" action="/category?action=edit">
                                <div class="d-flex align-items-center justify-content-between mb-3">
                                    <h3 class="text-primary">Edit categories</h3>
                                </div>
                                <div class="form-group row" style="display:none">
                                    <input class="form-control" id="id" name="id" type="text"
                                           value="${requestScope.category.getId()}">
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control" id="name" name="name"
                                           placeholder="Nhập tên bạn muốn sửa"
                                           value="${requestScope.category.getName()}">
                                    <label for="floatingInput">Name</label>
                                </div>
                                <button type="submit" class="btn btn-primary py-3 w-100 mb-4"  onclick="return confirm('Are you sure editing this product?')">Edit</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </form>
        <jsp:include page="/WEB-INF/layout/scriptHtml.jsp"></jsp:include>
    </div>
</div>
</body>

</html>