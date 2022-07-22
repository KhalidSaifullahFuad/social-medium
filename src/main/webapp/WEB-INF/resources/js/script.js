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

        $(".attachment-label").text(getAddPhotosMsg());
    });

    const getAddPhotosMsg = () => {
        const photoCount = $(".photo-preview").length + 1;

        if(photoCount == 1){
            return "Added 1 photo";
        }else if(photoCount > 1){
            return `Added ${photoCount} photos`;
        }

        return "Add to your post";
    }

    $(".post-photos").delegate(".remove-photo", "click", function () {
        $(this).closest(".photo-preview").remove();
        if($(".photo-preview").length == 0){
            $("#post-text").removeClass("compact");
            $(".post-photos").hide();
        }
        $(".attachment-label").text(getAddPhotosMsg());
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