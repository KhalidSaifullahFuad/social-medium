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
    <title>User</title>
</head>
<body>
    Name: ${user.getName()}<br>
    Email: ${user.getEmail()}<br>
    Location: ${user.getLocation().getLocationName()}<br>
    Attachment Path: ${user.getAttachment().getAttachmentPath()}<br>
</body>
</html>
