<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/people.css">

<section class="people">
    <div class="people-list card">

        <c:forEach var="user" items="${userList}">
            <c:set var="attachmentId" value="${user.getAttachment().getId()}"/>
            <c:set var="userProfilePhoto" value="${empty attachmentId ? '/images/' : '/attachment/' }${empty attachmentId ? 'user.png' : attachmentId}"/>

            <div class="user">
                <img class="user-cover-photo" src="${pageContext.request.contextPath}/images/banner.png" alt="cover photo">
                <img class="user-profile-photo" src="${userProfilePhoto}" alt="profile photo">
                <h3 class="user-name">${user.getUsername()}</h3>
                <div class="headline">@${user.getHandle()}</div>
                <div class="btn btn-outline btn-connect btn-follow">Follow</div>
            </div>
        </c:forEach>
        <%--        <div class="user">--%>
        <%--            <img class="user-cover-photo" src="./assets/images/banner.png" alt="">--%>
        <%--            <img class="user-profile-photo" src="./assets/images/profile.jpg" alt="profile">--%>
        <%--            <h3 class="user-name">Khalid Saifullah Fuad</h3>--%>
        <%--            <div class="headline">Student at Southeast University</div>--%>
        <%--            <div class="btn btn-outline btn-connect">Connect</div>--%>
        <%--        </div>--%>
    </div>
</section>