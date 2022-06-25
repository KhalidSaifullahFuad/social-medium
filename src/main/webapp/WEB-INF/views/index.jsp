<title>Social Medium</title>

<meta charset="UTF-8"/>

<link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" ></script>
<script src="${pageContext.request.contextPath}/js/script.js" defer></script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">

    <!--  Top Navbar  -->
    <jsp:include page="components/navbar.jsp"/>

    <main class="main-content">

        <!--  Sidebar Section  -->
        <jsp:include page="components/sidebar.jsp"/>

        <!--  Feed Section  -->
        <jsp:include page="components/status_feed.jsp"/>

        <!--  Contacts Section  -->
        <jsp:include page="components/contacts.jsp"/>
    </main>
</div>
