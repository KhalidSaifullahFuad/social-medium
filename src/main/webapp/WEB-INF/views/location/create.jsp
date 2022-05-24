<%--
  Created by IntelliJ IDEA.
  User: Fuad
  Date: 08-May-22
  Time: 07:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
    <%--@elvariable id="location" type="com.fuad.model.Location"--%>
    <form:form action="${pageContext.request.contextPath}/location/store" method="post" modelAttribute="location">

        Location:
        <form:input cssClass="form-control" path="locationName"/>
        <br/>

        <button type="submit" class="btn btn-primary">Create</button>
    </form:form>
</body>
</html>
