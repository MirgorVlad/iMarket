<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${user.name}</title>
</head>
<body>

<h2>User: ${user.email}</h2>
<p>Name: ${user.name}</p>
<p>Email: ${user.email}</p>
<p>Phone: ${user.phoneNumber}</p>

<h2>Products: </h2>

<#list user.productList as product>
    <p>Name: ${product.title}</p>
    <p>Description: ${product.description}</p>
    <p>Price: ${product.price}</p>
    <p>City: ${product.city}</p>
    <hr>
</#list>


</body>
</html>