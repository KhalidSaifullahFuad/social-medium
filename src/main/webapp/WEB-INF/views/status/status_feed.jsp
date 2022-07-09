<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<div class="news-feed">

    <div id="create_post"></div>

    <div class="posts">
        <c:forEach var="status" items="${statusList}">
            <div class="post card">
                <div class="post-header">
                    <div class="profile-photo">
                        <img src="${pageContext.request.contextPath}/images/profile.jpg" alt="profile-photo">
                    </div>

                    <div class="user-info">
                        <div class="user-name">Khalid Saifullah Fuad</div>

                        <div class="post-info">
                            <span class="time" title="Sunday, 16 April 2022 at 12:40 PM">16 April</span>
                            <span class="location">${status.getLocation().getLocationName()}</span>
                            <span class="privacy">${status.getPrivacy()}</span>
                        </div>
                    </div>

                    <div class="post-option"><i class="uil uil-ellipsis-h"></i></div>
                </div>

                <div class="post-body">
                    <div class="post-text">Want to perfect as the Perfect Number &#x1F601.</div>
                    <c:if test="${status.getStatusAttachmentList().size() != 0}">
                        <div class="post-images">
                            <c:forEach var="attachment" items="${status.getStatusAttachmentList()}">
                                <img src="/attachment/${attachment.getId()}" alt="post photo" loading="">
                            </c:forEach>
                        </div>
                    </c:if>
                </div>

                <div class="post-footer">
                    <div class="post-engagement">
                        <div class="likes">
                            <i class="uil uil-heart"></i>
                            <span class="like-count">0</span>
                        </div>

                        <div class="comments">
                            <i class="uil uil-comment-alt"></i>
                            <span class="comment-count">0</span>
                        </div>

                        <div class="shares">
                            <i class="uil uil-share-alt"></i>
                            <span class="share-count">0</span>
                        </div>

                        <div class="bookmarks">
                            <i class="uil uil-bookmark"></i>
                            <span class="bookmark-count">0</span>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>