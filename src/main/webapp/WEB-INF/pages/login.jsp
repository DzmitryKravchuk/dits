<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<c:url value="/login"/>" method="post" >

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

<div id="login">
    <input type="text" name="username" placeholder="Логин" value="" required>
    <br>
    <input type="password" name="password" placeholder="Пароль" value="" required>
    <br>
    <input type="submit" class="submit-button" value="Войти">
</div>
</body>
</html>
