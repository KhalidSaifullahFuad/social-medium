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

        <section id="main_section"></section>

        <section id="contacts_section">
            <jsp:include page="components/contacts.jsp"/>
        </section>
    </main>
</div>

<jsp:include page="modals/create_post.jsp"/>
<jsp:include page="auth/logout.jsp"/>

<script>
    $(document).ready(function () {
        const loadPage = (...urls) => {
            let element = $('#main_section');
            element.html('');
            urls = urls.flat(1);

            $.each(urls, function(i, url){
                $.ajax({
                    url: '${pageContext.request.contextPath}/' + url,
                    success: (response) => {
                        if (i === 0) {
                            $(element).html(response);
                        } else {
                            $(element).append(response);
                        }

                        if (url !== "user/all")
                            $('#contacts_section').show();
                        if (i > 0 && url === "status/all") {
                            $("#create_post").hide();
                        }
                    }
                });
            });
        };

        let path = $(location).attr('pathname');


        if (path.startsWith("/feed")) {
            loadPage('status/all');
        }


        // Sidebar click events
        $(".menu-item").delegate("a", "click", function () {
            if ($(this).parent().hasClass("active")) return;

            let navId = this.id;

            if(navId !== "logout") {
                $(".menu-item").children().removeClass("active");
                $(this).parent().addClass("active");
            }

            // if (navId === 'people') {
            //     $("#contacts_section").hide();
            //     $('.main-content').addClass('people-section');
            // }else{
            //     $('.main-content').removeClass('people-section');
            // }

            const pages = {
                "home" : "status/all",
                "people" : "user/all",
                "photos" : "attachment/all",
                "location" : "location/all",
                "profile" : ["user/${user.getHandle()}", "status/all"]

            }
            if(pages[navId] !== undefined) {
                loadPage(pages[navId]);
            }
            console.log(pages[navId]);
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
