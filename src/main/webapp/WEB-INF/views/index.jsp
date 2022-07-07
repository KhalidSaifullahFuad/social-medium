<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="includes/taglib.jsp"/>
<jsp:include page="includes/css.jsp"/>
<jsp:include page="includes/js.jsp"/>

<meta charset="UTF-8"/>
<title>Social Medium</title>

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


<jsp:include page="auth/logout.jsp"/>

<script>
    $(document).ready(function () {
        const loadPage = (url, element, type = 'component') => {
            $.ajax({
                url: '${pageContext.request.contextPath}/' + url,
                success: (response) => {
                    if (type === 'component') {
                        $(element).html(response);
                    } else {
                        $(element).append(response);
                    }

                    if (url !== "user/all")
                        $('#contacts_section').show();
                    if (url === "feed") {
                        $.get("home.jsp", function (data) {
                            console.log(data);
                            $("#create_post").html(data);
                        });
                    }
                }
            });
        };

        let path = $(location).attr('pathname');


        if (path.startsWith("/feed")) {
            loadPage('status/all', $('#main_section'));

        }


        // Sidebar click events
        $(".menu-item").delegate("a", "click", function () {
            if ($(this).parent().hasClass("active")) return;

            let navId = this.id;

            if(navId !== "logout") {
                $(".menu-item").children().removeClass("active");
                $(this).parent().addClass("active");
            }
            if (navId === 'home') {
                loadPage('status/all', $('#main_section'));
                $('#create_post').show();
            } else if (navId === 'people') {
                loadPage('user/all', $('#main_section'));
                $('#contacts_section').hide();
            } else if (navId === "location") {
                loadPage("location/all", $("#main_section"))
            } else if (navId === "profile") {
                loadPage("profile", $("#main_section"));
                loadPage("status/all", $("#main_section"), "append");
            } else if (navId === "settings") {
                loadPage("settings", $("#main_section"))
            }
        });

        // for modal
        $(".btn-create").on("click", () => {
            $(".create-post-modal").addClass("show");
        });

        $(document).delegate(".modal-close, .btn-cancel", "click", () => {
            $(".modal-container").removeClass("show");
        });

        $("#logout").on("click", () => {
            $(".logout-modal").addClass("show");
        });
    });
</script>
