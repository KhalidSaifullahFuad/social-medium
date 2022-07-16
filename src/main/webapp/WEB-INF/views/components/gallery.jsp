<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="photo-gallery">
    <div class="gallery-grid card">
        <c:forEach var="status" items="${statusList}">
            <c:forEach var="photo" items="${status.statusAttachmentList()}">
                <a href="">
                    <img src="/attachment/${photo.getId()}" class="gallery-image" alt="${photo.getAttachmentName()}">
                </a>
            </c:forEach>
        </c:forEach>
    </div>
</div>