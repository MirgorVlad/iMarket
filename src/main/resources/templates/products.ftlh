<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>iMarket</title>
</head>
<body>
<#if user.email??>
    <h2>${user.email}</h2>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="logout">
    </form>

    <#else>
        <a href="/login">Sign in</a>
</#if>

<#if user.isAdmin()>
    <a href="/admin">Admin page</a>
</#if>

    <h2>Products:</h2>
    <h3>Find: </h3>
    <form action="/" method="get">
        Title: <input type="text" name="title">
        <input type="submit" value="Find">
    </form>
    <ul>
    <#list  products as product>
        <li>${product.title}: ${product.price} <a href="/product/${product.id}">more...</a></li>
    </#list>
    </ul>
    <hr>
<#if user.email??>
    <h3>Create new product</h3>
    <form action="/product/create" method="post" enctype="multipart/form-data">

        Title: <input type="text" name="title"><br><br>
        Description: <input type="text" name="description"><br><br>
        Price: <input type="number" name="price"><br><br>
        City: <input type="text" name="city"><br><br>
        Image1: <input type="file" name="file1"><br><br>
        Image2: <input type="file" name="file2"><br><br>
        Image3: <input type="file" name="file3"><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Create">
    </form>
</#if>

</body>
</html>