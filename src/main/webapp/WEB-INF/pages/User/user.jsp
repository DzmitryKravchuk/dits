<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="<c:url value="/logout"/>">выйти</a>
<h1>Hello ${user}</h1>

<a href="<c:url value="/chooseTest"/>">Выбор темы и теста</a><br>
<a href="<c:url value="/personalStatistic"/>">Личная статистика</a><br>
</body>
</html>
