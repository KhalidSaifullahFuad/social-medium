<%--
  Created by IntelliJ IDEA.
  User: Fuad
  Date: 17-May-22
  Time: 08:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
<div class="container-fluid">
    <table class="table table-bordered">
        <thead class="thead">
            <tr>
                <th class="th">Profile Photo</th>
                <th class="th">Id</th>
                <th class="th">Name</th>
                <th class="th">Email</th>
                <th class="th">Password</th>
                <th class="th">Location</th>
            </tr>
        </thead>
        <tbody class="tbody">
            <h4>Users(${userList.size()})</h4>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td class="td"><img src="/images/${user.getAttachment().getAttachmentPath()}" alt="" height="50px" width="50px" style="object-fit: cover"/></td>
                    <td class="td">${user.getId()}</td>
                    <td class="td">${user.getName()}</td>
                    <td class="td">${user.getEmail()}</td>
                    <td class="td">${user.getPassword()}</td>
                    <td class="td">${user.getLocation().getLocationName()}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
