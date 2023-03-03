<!DOCTYPE html>
<html>
<head>
    <title>iMarket</title>
</head>
<body>
<h1>iMarket</h1><hr>
<h4>Sign up</h4>
<form action="/registration" method="post">
    Name: <input type="text" name="name"><br><br>
    Email: <input type="email" name="email"><br><br>
    Phone: <input type="text" name="phoneNumber"><br><br>
    Password: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Registr"/>
</form>
<#if error??>
    <h2 style="color: red;">${error}</h2>
</#if>
</body>
</html>