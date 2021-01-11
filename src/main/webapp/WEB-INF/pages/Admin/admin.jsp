<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="<c:url value="/logout"/>">выйти</a>
<h1>Hello ${user}</h1>

<a href="<c:url value="/createTest" />">Создать тест</a><br>
<a href="<c:url value="/createUser" />">Создать ползователя</a><br>
<a href="<c:url value="/adminStatistic" />">Статистика</a><br>
</body>
</html>
