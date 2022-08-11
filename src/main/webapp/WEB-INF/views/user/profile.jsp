<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:set var="attachmentId" value="${user.getAttachment().getId()}"/>
<c:set var="userProfilePhoto" value="${empty attachmentId ? '/images/' : '/attachment/' }${empty attachmentId ? 'user.png' : attachmentId}"/>

<section class="user-profile">
    <div class="user card">
        <img class="user-cover-photo" src="${pageContext.request.contextPath}/images/banner.png" alt="cover photo">
        <img class="user-profile-photo" src="${userProfilePhoto}" alt="profile photo">
        <h3 class="user-name">${user.getName()}</h3>
        <div class="user-handle">@${user.getUsername()}</div>
        <div class="headline"></div>

        <div class="profile-info">
            <div class="follower-count">
                <a href=""><span class="count">100</span> Followers</a>
            </div>
            <div class="post-count">
                <a href=""><span class="count">100</span> Posts</a>
            </div>
            <div class="following-count">
                <a href=""><span class="count">10</span> Following</a>
            </div>
        </div>

        <div class="user-location">
            <i class="uil uil-map-marker"></i>
            <div class="location-name">${user.getLocation().getLocationName()}</div>
        </div>
        <!-- <button class="btn">Follow</button> -->
    </div>

    <section id="post_section"></section>
</section>