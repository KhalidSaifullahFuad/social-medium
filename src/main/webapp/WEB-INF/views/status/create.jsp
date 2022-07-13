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

            <%--@elvariable id="status" type="com.fuad.dto.StatusDto"--%>
            <form:form action="${pageContext.request.contextPath}/status/store" method="post" modelAttribute="status" enctype="multipart/form-data">
               <div class="form-group">
                    <label for="statusText">Status</label>
                    <form:textarea cssClass="form-control" path="statusText"/>
                    <form:errors path="statusText" cssClass="text-danger"/>
                </div>

                <form:select cssClass="dropdown" path="location">
                    <form:option selected="true" value="Select Location" disabled="true"/>
                    <form:options items="${locationList}"/>
                </form:select>
                <form:errors path="location" cssClass="text-danger"/>
                <br>

                <form:select cssClass="dropdown" path="privacy">
                    <form:option selected="true" value="Select Privacy" disabled="true"/>
                    <form:options items="${privacyList}"/>
                </form:select>
                <form:errors path="privacy" cssClass="text-danger"/>

                <br>

                <input type="file" name="images" multiple="multiple" accept="image/*"/>

                <button type="submit" class="btn btn-primary">Create</button>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
