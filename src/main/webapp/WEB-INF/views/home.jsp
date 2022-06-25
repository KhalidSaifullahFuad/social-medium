<%--
  Created by IntelliJ IDEA.
  User: Fuad
  Date: 19-May-22
  Time: 08:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">

            <nav class="navbar navbar-expand-lg bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">Navbar</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="#">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Link</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Link</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

            <sec:authorize access="isAuthenticated()">
                Welcome Back, <sec:authentication property="name"/>
            </sec:authorize>

            <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                <h4>Location</h4>
                <a href="${pageContext.request.contextPath}/location/create" target="_blank">Create Location</a> <br>
                <a href="${pageContext.request.contextPath}/location/list" target="_blank">Location List</a> <br><br>
            </sec:authorize>

            <h4>User</h4>
            <a href="${pageContext.request.contextPath}/user/create" target="_blank">Create User</a> <br>
            <a href="${pageContext.request.contextPath}/user/maintain" target="_blank">User List</a> <br><br>

            <h4>Status</h4>
            <a href="${pageContext.request.contextPath}/status/create" target="_blank">Create Status</a> <br>
            <a href="${pageContext.request.contextPath}/status/list" target="_blank">Status List</a> <br><br>

            <sec:authorize access="isAuthenticated()">
                <a href="${pageContext.request.contextPath}/logout">
                    <button class="btn btn-primary">Logout</button>
                </a>
            </sec:authorize>
        </div>
    </div>
</div>
</body>
</html>
