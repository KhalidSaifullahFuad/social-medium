<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<section class=locations">
    <div class="location-list location-gri card">
        <c:forEach var="location" items="${locationList}">
            <div class="location">
                <img src="${pageContext.request.contextPath}/images/location.png" alt="">
                <h3>${location.getLocationName()}</h3>
            </div>
        </c:forEach>
    </div>
</section>