<%--
  Created by IntelliJ IDEA.
  User: Fuad
  Date: 19-May-22
  Time: 08:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Create User</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-9">

            <%--@elvariable id="userDto" type="com.fuad.dto.UserDto"--%>
            <form:form action="${pageContext.request.contextPath}/user/store" method="post" modelAttribute="userDto" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="name">Name</label>
                    <form:input type="text" cssClass="form-control" id="name" placeholder="Name" path="name"/>
                    <form:errors path="name" cssClass="text-danger"/>
                </div>

                <div class="form-group">
                    <label for="email">Email address</label>
                    <form:input type="email" cssClass="form-control" id="email" placeholder="Email" path="email"/>
                    <form:errors path="email" cssClass="text-danger"/>
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <form:input type="password" cssClass="form-control" id="password" placeholder="Password" path="password"/>
                    <form:errors path="password" cssClass="text-danger"/>
                </div>

                <form:select cssClass="dropdown" path="location">
                    <form:option selected="true" value="Select Location" disabled="true"/>
                    <form:options items="${locationList}"/>
                </form:select>
                <form:errors path="location" cssClass="text-danger"/>
                <br>

                <input type="file" name="image" accept="image/*"/>

                <button type="submit" class="btn btn-primary">Create</button>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
