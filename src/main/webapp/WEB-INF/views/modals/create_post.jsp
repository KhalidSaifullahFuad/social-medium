<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal.css">

<div class="modal-container create-post-modal">
    <div class="modal card">
        <div class="modal-header">
            <h3 class="title">Create Post</h3>
            <div class="modal-close"><i class="uil uil-times"></i></div>
        </div>

        <div class="modal-body">
            <form:form action="${pageContext.request.contextPath}/status/store" method="post" modelAttribute="status" enctype="multipart/form-data">

                <div class="post-header">
                    <div class="profile-photo">
                        <img src="assets/images/profile.jpg" alt="profile thumb">
                    </div>

                    <div class="post-info">
                        <div class="user-name">Khalid Saifullah Fuad</div>

                        <div class="user-info">
                            <select name="privacy" id="">
                                <option value="public">Public</option>
                                <option value="connections">Connections</option>
                                <option value="only-me">Only Me</option>
                            </select>
                        </div>
                    </div>
                </div>

                <form:textarea id="post-text" placeholder="What's happening?" path="statusText"></form:textarea>

                <div class="post-photos"></div>

                <div class="footer">
                    <div class="add-post-attachment">
                        <div class="attachment-label">Add to your post</div>
                        <label for="images"><i class="uil uil-scenery"></i></label>
                        <input type="file" id="images" name="images" accept="image/*" multiple>
                    </div>

                    <button type="submit" class="btn btn-post" >Post</button>
                </div>

            </form:form>
        </div>
    </div>
</div>