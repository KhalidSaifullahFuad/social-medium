<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<div class="profile-photo">
    <c:if test="${user.getAttachment().getId() != null}">
        <img src="attachment/${user.getAttachment().getId()}" alt="profile-photo">
    </c:if>

    <c:if test="${empty user.getAttachment().getId()}">
        <img src="${pageContext.request.contextPath}/images/user.png" alt="profile-photo">
    </c:if>
</div>