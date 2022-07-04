<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<title>LogIn</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" ></script>

<fmt:setBundle basename="messages" />
<fmt:message key="message.badCredentials" var="worngUserAndPass" />
<fmt:message key="message.usernameRequired" var="noUser" />
<fmt:message key="message.passwordRequired" var="noPass" />
<fmt:message key="message.nameAndPassRequired" var="noUserAndPass" />
<fmt:message key="message.loginError" var="loginErr" />
<fmt:message key="message.logoutSuccess" var="logoutSucc" />


<form class="login-form card" method="post" action="${pageContext.request.contextPath}/login-process">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    <c:if test="${param.error!=null}">
        <div class="alert alert-danger">${worngUserAndPass}</div>
    </c:if>

    <c:if test="${param.logout!=null}">
        <div class="alert alert-primary">${logoutSucc}</div>
    </c:if>

    <sec:authorize access="isAuthenticated()">
        <div class="alert alert-primary">${loginErr}</div>
    </sec:authorize>


    <label for="username" class="hidden">Username</label>
    <input type="text" id="username" name="username" placeholder="Username or Email" required="">

    <label for="password" class="hidden">Password</label>
    <input type="password" id="password" name="password" placeholder="Password" required="">

    <button class="btn btn-primary btn-sign-in" type="button">Sign in</button>

    <div class="goto-signup">
        <div>Not registered?</div>
        <a href="${pageContext.request.contextPath}/user/create">Create User</a></div>
    </div>
</form>

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
    });
</script>