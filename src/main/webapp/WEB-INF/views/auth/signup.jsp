<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal.css">

<div class="modal-container sign-up-modal">
    <div class="modal card">
        <div class="modal-header">
            <h3 class="title">Sign Up</h3>
            <div class="modal-close"><i class="uil uil-times"></i></div>
        </div>

        <div class="modal-body">
            <%--@elvariable id="userDto" type="com.fuad.dto.UserDto"--%>
            <form:form cssClass="sign-up-form" action="${pageContext.request.contextPath}/user/store" method="post" modelAttribute="userDto" enctype="multipart/form-data">
                <div class="form-group span">
                    <label for="first_name">First Name</label>
                    <input type="text" id="first_name" class="form-control" placeholder="First Name">
                </div>

                <div class="form-group span">
                    <label for="last_name">Last Name</label>
                    <input type="text" id="last_name" class="form-control" placeholder="Last Name">
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" class="form-control" placeholder="Email">
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" class="form-control" placeholder="Password">
                </div>

                <div class="form-group">
                    <label for="confirm-password">Confirm Password</label>
                    <input type="password" id="confirm-password" class="form-control" placeholder="Confirm Password">
                </div>

                <div class="form-group">
                    <label for="phone">Phone</label>
                    <input type="text" id="phone" class="form-control" placeholder="Phone">
                </div>

                <div class="form-group">
                    <label for="address">Address</label>
                    <input type="text" id="address" class="form-control" placeholder="Address">
                </div>
            </form:form>

            <div class="footer">
                <button class="btn btn-cancel">Cancel</button>
                <button class="btn btn-logout">Logout</button>
            </div>
        </div>
    </div>
</div>