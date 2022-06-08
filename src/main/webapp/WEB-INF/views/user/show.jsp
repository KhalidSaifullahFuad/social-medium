<%--
  Created by IntelliJ IDEA.
  User: Fuad
  Date: 19-May-22
  Time: 08:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
    Name: ${user.getName()}<br>
    Email: ${user.getEmail()}<br>
    Location: ${user.getLocationName()}<br>
    Attachment Path: <img src="data:image/png;base64,${user.getImage()}" height="100px" width="100px" style="object-fit: cover"/> <br>
</body>
</html>
