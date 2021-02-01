<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose your role</title>
    <link href="<c:url value="/resources/mystyle.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="mycont">
    <c:if test="${roles.contains(\"user\")}">
        <a href="<c:url value="/user" />" role="button">Войти как пользователь</a>
        <br>
    </c:if>

    <c:if test="${roles.contains(\"admin\")}">
        <a href="<c:url value="/admin" />" role="button">Войти как администратор</a>
        <br>
    </c:if>

    <c:if test="${roles.contains(\"tutor\")}">
        <a href="<c:url value="/tutor" />" role="button">Войти как преподаватель</a>
    </c:if>
</div>
</body>
</html>
