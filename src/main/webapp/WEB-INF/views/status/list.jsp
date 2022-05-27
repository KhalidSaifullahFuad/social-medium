<%--
  Created by IntelliJ IDEA.
  status: Fuad
  Date: 19-May-22
  Time: 08:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Status List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>

<table class="table table-bordered">
    <thead class="thead">
    <tr>
        <th class="th">Id</th>
        <th class="th">Title</th>
        <th class="th">Description</th>
        <th class="th">Privacy</th>
        <th class="th">Location</th>
        <th class="th">Attachment Path</th>
    </tr>
    </thead>
    <tbody class="tbody">
    <c:forEach var="status" items="${statusList}">
        <tr>
            <td class="td">${status.getId()}</td>
            <td class="td">${status.getTitle()}</td>
            <td class="td">${status.getDescription()}</td>
            <td class="td">${status.getPrivacy()}</td>
            <td class="td">${status.getLocation().getLocationName()}</td>
            <td></td>
                <%--                <td class="td">${status.getAttachment().getAttachmentPath()}</td>--%>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>