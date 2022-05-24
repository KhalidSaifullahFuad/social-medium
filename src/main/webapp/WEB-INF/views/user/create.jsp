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
    <title>User</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-9">

            <%--@elvariable id="userDto" type="com.fuad.dto.UserDto"--%>
            <form:form action="${pageContext.request.contextPath}/user/store" method="post" modelAttribute="userDto">
                <div class="form-group">
                    <label for="name">Name</label>
                    <form:input type="text" cssClass="form-control" id="name" placeholder="Name" path="name"/>
                </div>

                <div class="form-group">
                    <label for="email">Email address</label>
                    <form:input type="email" cssClass="form-control" id="email" placeholder="Email" path="email"/>
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <form:input type="password" cssClass="form-control" id="password" placeholder="Password" path="password"/>
                </div>

                <form:select cssClass="dropdown" path="location">
                    <form:option selected="true" value="Select Location" disabled="true"/>
                    <form:options items="${locationList}"/>
                </form:select>

                <br>

<%--                <form:input type="file" name="profile_photo" placeholder="Photo" path="attachment" capture=""/>--%>

                <button type="submit" class="btn btn-primary">Submit</button>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
