<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${product.title}</title>
</head>
<body>
<#list images as img>
    <img src="/images/${img.id}" alt="image" height="60px"><br/>
</#list>
<p>Name: ${product.title}</p>
<p>Description: ${product.description}</p>
<p>Price: ${product.price}</p>
<p>City: ${product.city}</p>
<p>Author: ${product.user.email}</p>
<hr>

<form action="/product/delete/${product.id}" method="post">
    <input type="submit" value="Delete">
</form>

</body>
</html>