<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>iMarket</title>
    <link href="/static/css/nav.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</head>
<body>
<#import "blocks/navbar.ftlh" as n>
<@n.navbar>

</@n.navbar>


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