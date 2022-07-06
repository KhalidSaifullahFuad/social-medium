<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages" />
<fmt:message key="message.demoUser" var="user" />
<fmt:message key="message.badCredentials" var="worngUserAndPass" />
<fmt:message key="message.usernameRequired" var="noUser" />
<fmt:message key="message.passwordRequired" var="noPass" />
<fmt:message key="message.nameAndPassRequired" var="noUserAndPass" />
<fmt:message key="message.loginError" var="loginErr" />
<fmt:message key="message.logoutSuccess" var="logoutSucc" />