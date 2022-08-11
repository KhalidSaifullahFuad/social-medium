<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<section class="sidebar">
    <sec:authorize access="isAuthenticated()">
        <div class="profile card">
            <jsp:include page="profile_photo.jsp"/>

            <div class="profile-info">
                <div class="user-name">${user.getName()}</div>
                <div class="user-handle">@${user.getUsername()}</div>
            </div>
        </div>
    </sec:authorize>

    <ul class="menu-item card">
        <li class="active"><a href="#" id="home"><i class="uil uil-estate"></i><h3>Home</h3></a></li>
        <li><a href="#" id="people"><i class="uil uil-user-square"></i><h3>People</h3><div class="notification">5</div></a></li>
        <li><a href="#" id="photos"><i class="uil uil-scenery"></i><h3>Photos</h3><div class="notification">99</div></a></li>
        <sec:authorize access="hasRole('ADMIN')">
            <li><a href="#" id="location"><i class="uil uil-map-marker"></i><h3>Location</h3></a></li>
        </sec:authorize>
        <li><a href="#" id="profile"><i class="uil uil-user"></i><h3>Profile</h3></a></li>
        <li><a href="#" id="theme"><i class="uil uil-palette"></i><h3>Theme</h3></a></li>
        <li><a href="#" id="logout"><i class="uil uil-signout"></i><h3>Logout</h3></a></li>
    </ul>
</section>