<%--
  Created by IntelliJ IDEA.
  User: Fuad
  Date: 19-Jun-22
  Time: 09:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<form class="" method="post" action="${pageContext.request.contextPath}/logout">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    <button class="btn btn-primary" type="submit">Log Out</button>
</form>
</body>
</html>
