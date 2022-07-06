<title>Social Medium</title>
<meta charset="UTF-8"/>

<jsp:include page="includes/taglib.jsp"/>
<jsp:include page="includes/css.jsp"/>
<jsp:include page="includes/js.jsp"/>
<jsp:include page="includes/messages.jsp"/>

<div class="container">

    <jsp:include page="components/navbar.jsp"/>

    <main class="main-content">

        <jsp:include page="components/sidebar.jsp"/>

        <section id="main_section">
            <%--            <jsp:include page="status/status_feed.jsp"/>--%>
        </section>

        <section id="contacts_section">
            <jsp:include page="components/contacts.jsp"/>
        </section>
    </main>
</div>

<script>
    $(document).ready(function () {
        const loadPage = (url, element) => {
            $.ajax({
                url: '${pageContext.request.contextPath}/' + url,
                success: (response) => element.html(response)
            });
        };

        let path = $(location).attr('pathname');


        if (path.startsWith("/feed")) {
            loadPage('status/all', $('#main_section'));
        }




        // Sidebar click events
        $(".menu-item").delegate("a", "click", function () {
            if ($(this).parent().hasClass("active")) return;

            $(".menu-item").children().removeClass("active");
            $(this).parent().addClass("active");

            let navId = this.id;
            if (navId === 'home') {
                loadPage('status/all', $('#main_section'));
                $('#create_post').show();
            } else if (navId === 'people') {
                loadPage('user/all', $('#main_section'));
                $('#contacts_section').hide();
            } else if (navId === "location") {
                loadPage("location/all", $("#main_section"))
            } else if (navId === "profile") {
                loadPage("profile", $("#main_section"))
            }

            if (navId !== "people") {
                $('#contacts_section').show();
            }
        });
    });
</script>
