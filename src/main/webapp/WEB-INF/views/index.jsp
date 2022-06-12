<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Social Medium</title>

    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

    <!-- --------------------------------------- Container --------------------------------------- -->

    <div class="container">

    <!-- --------------------------------------- Top Navbar --------------------------------------- -->

        <nav class="nav">
            <section class="logo-section">
                <a class="logo focus-visible" href="#"><div>Social Medium</div></a>
            </section>

            <section class="toolbar-section">
                <div class="search-bar">
                    <i class="uil uil-search"></i>
                    <input type="search" name="search" id="search_box" placeholder="Search">
                </div>
                <div class="btn btn-create">Create</div>
                <div class="profile-photo">
                    <img src="/images/user.png" alt="profile-photo">
                </div>
            </section>
        </nav>


    <!-- --------------------------------------- Main Content --------------------------------------- -->

        <main class="main-content">

            <!-- --------------------------------------- Sidebar Section --------------------------------------- -->

            <section class="sidebar">
                <div class="profile card">
                    <div class="profile-photo">
                        <img src="/images/user.png" alt="profile-photo">
                    </div>
                    <div class="profile-info">
                        <div class="user-name">Khalid Saifullah Fuad</div>
                        <div class="user-handle">@khalidsaifullahfuad</div>
                    </div>
                </div>

                <ul class="menu-item card">
                    <li class="active"><a href="#"><i class="uil uil-estate"></i><h3>Home</h3></a></li>
                    <li><a href="#"><i class="uil uil-user-square"></i><h3>People</h3><div class="notification">5</div></a></li>
                    <li><a href="#"><i class="uil uil-scenery"></i><h3>Photos</h3><div class="notification">99</div></a></li>
                    <li><a href="#"><i class="uil uil-map-marker"></i><h3>Location</h3></a></li>
                    <li><a href="#"><i class="uil uil-user"></i><h3>Profile</h3></a></li>
                    <li><a href="#"><i class="uil uil-setting"></i><h3>Setting</h3></a></li>
                </ul>
            </section>

            <!-- --------------------------------------- Feed Section --------------------------------------- -->

            <section class="news-feed">
                <div class="card create-post">
                    <div class="profile-photo">
                        <img src="/images/user.png" alt="profile thumb">
                    </div>

                    <input type="text" placeholder="What's new, Fuad?">

                    <button class="btn">Post</button>
                </div>

                <div class="posts">
<%--                    <div class="post card">--%>
<%--                        <div class="post-header">--%>
<%--                            <div class="profile-photo">--%>
<%--                                <img src="/images/user.png" alt="profile thumb">--%>
<%--                            </div>--%>

<%--                            <div class="user-info">--%>
<%--                                <div class="user-name">Khalid Saifullah Fuad</div>--%>

<%--                                <div class="post-info">--%>
<%--                                    <span class="time" title="Thursday, 2 June 2022 at 11:58 PM">02 June at 11:58 PM</span>--%>
<%--                                    <span class="location">Dhaka</span>--%>
<%--                                    <span class="privacy">Public</span>--%>
<%--                                </div>--%>
<%--                            </div>--%>

<%--                            <div class="post-option"><i class="uil uil-ellipsis-h"></i></div>--%>
<%--                        </div>--%>
<%--                        <div class="post-body">--%>
<%--                            <div class="post-text">Really excited to participate in upcoming 2022 Space App Challenge with @Badhon and @AB Faruki vai. Inshallah we'll achieve something great 💪.--%>
<%--                            </div>--%>
<%--                            <div class="post-images">--%>
<%--                                <img src="./assets/images/285270866_1206032613477060_4532221050095430131_n.jpg" alt="post photo" loading="">--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>

                </div>
            </section>

            <!-- --------------------------------------- Contacts Section --------------------------------------- -->

            <section class="contacts">
                <div class="contact-header">
                    <div class="title">Contacts</div>
                    <span class="num-of-contact">5</span>
                </div>

                <div class="contact-list card">
                    <div class="contact">
                        <div class="profile-photo">
                            <img src="/images/user.png" alt="profile-photo">
                        </div>
                        <div class="profile-info">
                            <div class="user-name">Mahedi Hasan</div>
                            <div class="user-handle">@mahedihasan</div>
                        </div>

                        <div class="profile-option">
                            <i class="uil uil-ellipsis-v"></i>
                        </div>
                    </div>

                    <div class="contact">
                        <div class="profile-photo">
                            <img src="/images/user.png" alt="profile-photo">
                        </div>

                        <div class="user-info">
                            <div class="user-name">Yeasir Arafat</div>
                            <div class="user-handle">@yeasirarafat</div>
                        </div>

                        <div class="profile-option">
                            <i class="uil uil-ellipsis-v"></i>
                        </div>
                    </div>

                    <div class="contact">
                        <div class="profile-photo">
                            <img src="/images/user.png" alt="profile-photo">
                        </div>

                        <div class="user-info">
                            <div class="user-name">Sibgatullah Labib</div>
                            <div class="user-handle">@sigatullahlabib</div>
                        </div>

                        <div class="profile-option">
                            <i class="uil uil-ellipsis-v"></i>
                        </div>
                    </div>

                    <div class="contact">
                        <div class="profile-photo">
                            <img src="/images/user.png" alt="profile-photo">
                        </div>

                        <div class="user-info">
                            <div class="user-name">Saadullah Fardin</div>
                            <div class="user-handle">@saadullahfardin</div>
                        </div>

                        <div class="profile-option">
                            <i class="uil uil-ellipsis-v"></i>
                        </div>
                    </div>

                </div>
            </section>
        </main>

    </div>
</body>

</html>