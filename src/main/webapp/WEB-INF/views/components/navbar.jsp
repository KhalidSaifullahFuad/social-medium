<nav class="nav">
    <section class="logo-section">
        <a class="logo focus-visible" href="${pageContext.request.contextPath}/feed">
            <img src="${pageContext.request.contextPath}/images/socially.svg" alt="">
            <div class="text">Socially</div>
        </a>
    </section>

    <section class="toolbar-section">
        <div class="search-bar">
            <i class="uil uil-search"></i>
            <input type="search" name="search" id="search_box" placeholder="Search">
        </div>

        <div class="btn btn-create">Create</div>

        <jsp:include page="profile_photo.jsp"/>
    </section>
</nav>