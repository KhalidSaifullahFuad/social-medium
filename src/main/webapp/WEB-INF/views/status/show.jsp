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
    <title>Status</title>
</head>
<body>
    Title: ${status.getTitle()}<br>
    Description: ${status.getDescription()}<br>
    Location: ${status.getLocation().getLocationName()}<br>
    Privacy: ${status.getPrivacy()}<br>
</body>
</html>
