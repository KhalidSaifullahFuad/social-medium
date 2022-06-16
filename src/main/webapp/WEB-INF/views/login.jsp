<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LogIn</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
    <form class="login-form card" method="post" action="${pageContext.request.contextPath}/login-process">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <label for="username" class="hidden">Username</label>
        <input type="text" id="username" name="username" placeholder="Username or Email" required="">

        <label for="password" class="hidden">Password</label>
        <input type="password" id="password" name="password" placeholder="Password" required="">

        <button class="btn btn-primary" type="submit">Sign in</button>
        
        <div class="goto-signup">
            <div>Not registered?</div>
            <a href="#">Create User</a></div>
        </div>
    </form>
</body>
</html>