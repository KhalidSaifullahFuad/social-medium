$(document).ready(function () {

    const getContextPath = () => window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    const loadPage = (...urls) => {
        let element = $('#main_section');
        element.html('');
        urls = urls.flat(1);
        const ctx = getContextPath();

        $.each(urls, function(i, url){
            $.ajax({
                url: ctx + url,
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
    });


    // for post images
    $("#images").on('change', function(){

        for(let file of this.files){
            const reader = new FileReader();
            reader.onload = (e) => {
                $(".post-photos").show().append(`<div class="photo-preview">
                                                        <img src="${e.target.result}" alt="">
                                                        <i class="uil uil-times remove-photo"></i>
                                                    </div>`);
                $("#post-text").addClass("compact");
            }
            reader.readAsDataURL(file);
        }
    });


    $(".post-photos").delegate(".remove-photo", "click", function () {
        $(this).closest(".photo-preview").remove();
        if($(".photo-preview").length === 0){
            $("#post-text").removeClass("compact");
            $(".post-photos").hide();
        }
    });


    $("textarea").each(function () {
        if(!$(this).hasClass("compact")){
            // $(this).setAttribute("style", "height: fit-content;");
            // }else{
            this.setAttribute("style", `height:${this.scrollHeight}px;overflow-y:hidden;`);
        }
    }).on("input", function () {
        this.style.height = "auto";
        this.style.height = `${this.scrollHeight}px`;
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