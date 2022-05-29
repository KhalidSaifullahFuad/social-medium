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
    <title>Create Status</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-9">

            <%--@elvariable id="status" type="com.fuad.model.StatusModel"--%>
            <form:form action="${pageContext.request.contextPath}/status/store" method="post" modelAttribute="status" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="title">Title</label>
                    <form:input type="text" cssClass="form-control" id="title" placeholder="" path="title"/>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <form:textarea cssClass="form-control" path="description"/>
                </div>

                <form:select cssClass="dropdown" path="location">
                    <form:option selected="true" value="Select Location" disabled="true"/>
                    <form:options items="${locationList}"/>
                </form:select>

                <br>

                <form:select cssClass="dropdown" path="privacy">
                    <form:option selected="true" value="Select Privacy" disabled="true"/>
                    <form:options items="${privacyList}"/>
                </form:select>

                <br>

                <input type="file" name="images" multiple="multiple" accept="image/*"/>

                <button type="submit" class="btn btn-primary">Create</button>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
