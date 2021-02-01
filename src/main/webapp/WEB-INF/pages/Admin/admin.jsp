<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Home Page</title>
    <link href="<c:url value="/resources/mystyle.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="mycont">
    <a href="<c:url value="/logout"/>">выйти</a>
    <h1>Hello ${user}</h1>

    <a href="<c:url value="/createTest" />">Создать тест</a><br>
    <a href="<c:url value="/createUser" />">Создать пользователя</a><br>
    <a href="<c:url value="/adminStatistic" />">Статистика</a><br>
</div>
</body>
</html>
