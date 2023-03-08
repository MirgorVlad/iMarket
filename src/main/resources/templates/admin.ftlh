<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin</title>
</head>
<body>

<h1>Administrator</h1>
<h2>All users:</h2>
<table>
    <tr>
        <th>Email</th>
        <th>Phone</th>
        <th>Enable</th>
        <th>Roles</th>
        <th>Ban</th>
        <th>Edit</th>
        <th>Info</th>
    </tr>
    <#list users as user>
        <tr>
            <td>${user.email}</td>
            <td>${user.phoneNumber}</td>
            <td><#if user.enable>Enable<#else>Not enable</#if></td>
            <td><#list user.roleSet as role>${role}</#list></td>
            <td>
                <form action="/admin/ban/${user.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="ban">
                </form></td>
            <td><a href="/admin/user/edit/${user.id}">Edit</a></td>
            <td><a href="user/${user.id}">Info</a></td>
        </tr>
    </#list>
</table>

</body>
</html>