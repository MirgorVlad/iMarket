<!DOCTYPE html>
<html>
<head>
    <title>iMarket</title>
</head>
<body>
<h1>iMarket</h1><hr>
<h4>Sign in</h4>
<form action="/login" method="post">
    Email: <input type="email" name="username"><br><br>
    Password: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="login"/>
</form>
<a href="/registration">Registration</a>
</body>
</html>