<%@ page contentType="text/html;charset=UTF-8" language="java" %>

     <jsp:include page="includes/css.jsp"/>
<jsp:include page="includes/js.jsp"/>

<meta charset="UTF-8"/>
<title>Social Medium</title>

<div class="container">

    <jsp:include page="components/navbar.jsp"/>

    <main class="main-content">

        <jsp:include page="components/sidebar.jsp"/>

        <section id="main_section"></section>

        <section id="contacts_section">
            <jsp:include page="components/contacts.jsp"/>
        </section>
    </main>
</div>

<jsp:include page="modals/create_status.jsp"/>
<jsp:include page="auth/logout.jsp"/>
