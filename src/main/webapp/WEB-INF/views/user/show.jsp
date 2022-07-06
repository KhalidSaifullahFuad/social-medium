<%--
  Created by IntelliJ IDEA.
  User: Fuad
  Date: 19-May-22
  Time: 08:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
    Name: ${user.getName()}<br>
    Email: ${user.getEmail()}<br>
<%--    Location: ${user.getLocation().getLocationName()}<br>--%>
<%--    Attachment: <img src=""><br>--%>
    Location: ${user.getLocationName()}<br>
    <c:if test="${user.getImage() != null}">
        Attachment Path: <img src="data:image/png;base64,${user.getImage()}" height="100px" width="100px" style="object-fit: cover"/> <br>
    </c:if>
</body>

<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" ></script>--%>
<%--<script>--%>
<%--    $(document).ready(() => {--%>
<%--        $("img").load(() => {--%>
<%--            $.ajax({--%>
<%--                url: "${pageContext.request.contextPath}/attachment/57",--%>
<%--                success: (data) => $("img").attr("src", "data:image/png;base64,"+ data)--%>
<%--            });--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>
</html>
