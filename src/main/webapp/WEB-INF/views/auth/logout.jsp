<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal.css">

<div class="modal-container logout-modal">
    <div class="modal card">
        <div class="modal-header">
            <h3 class="title">Logout</h3>
            <div class="modal-close"><i class="uil uil-times"></i></div>
        </div>

        <div class="modal-body">
            <div>Do you want to Logout?</div>

            <form class="" method="post" action="${pageContext.request.contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="footer">
                    <button class="btn btn-cancel" type="reset">Cancel</button>
                    <button class="btn btn-logout" type="submit">Logout</button>
                </div>
            </form>
        </div>
    </div>
</div>
