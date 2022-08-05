<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../includes/css.jsp"/>
<jsp:include page="../includes/js.jsp"/>

<fmt:setBundle basename="messages" />
<fmt:message key="message.demoUser" var="user" />
<fmt:message key="message.badCredentials" var="worngUserAndPass" />
<fmt:message key="message.usernameRequired" var="noUser" />
<fmt:message key="message.passwordRequired" var="noPass" />
<fmt:message key="message.nameAndPassRequired" var="noUserAndPass" />
<fmt:message key="message.loginError" var="loginErr" />
<fmt:message key="message.logoutSuccess" var="logoutSucc" />

<title>LogIn</title>

<form:form cssClass="login-form card" method="post" action="${pageContext.request.contextPath}/auth/login-process">

    <c:if test="${param.error!=null}">
        <div class="alert alert-danger">${worngUserAndPass}</div>
    </c:if>

    <c:if test="${demoUser!=null}">
        <div class="alert alert-primary">${user}</div>
    </c:if>

    <c:if test="${param.logout!=null}">
        <div class="alert alert-primary">${logoutSucc}</div>
    </c:if>

    <sec:authorize access="isAuthenticated()">
        <div class="alert alert-primary">${loginErr}</div>
    </sec:authorize>


    <label for="username" class="hidden">Username</label>
    <input type="text" id="username" name="username" placeholder="Username or Email">

    <label for="password" class="hidden">Password</label>
    <input type="password" id="password" name="password" placeholder="Password">

    <button type="button" class="btn btn-primary btn-sign-in">Sign in</button>

    <div class="goto-signup">
        <div>Not registered?</div>
        <button type="button" class="btn btn-link link-sign-up">Sign Up</button>
    </div>
</form:form>

<jsp:include page="signup.jsp"/>

<script>
    $(document).ready(() => {
        let setAlert = (message) => {
            if(!$(".alert").length) $('.login-form').prepend("<div class='alert'></div>");
            $('.alert').addClass("alert-danger").text(message);
        };

        $(".btn-sign-in").on("click", () => {
            let username = $("#username").val();
            let password = $("#password").val();

            if(username === '' && password === ''){
                $('#username, #password').addClass('error');
                setAlert("${noUserAndPass}.");
            }else if(username === ''){
                $('#username').addClass('error');
                setAlert("${noUser}");

            }else if(password === ''){
                $('#password').addClass('error');
                setAlert("${noPass}");
            }else{
                $('.login-form').submit();
            }
        });

		$(".link-sign-up").on("click", () => {
			$(".sign-up-modal").addClass("show");
		});

		$(document).delegate(".modal-close, .btn-cancel", "click", () => {
			$(".modal-container").removeClass("show");
		});
    });
</script>