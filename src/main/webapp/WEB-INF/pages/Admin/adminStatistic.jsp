<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="<c:url value="/statistic/testStatisticPage"/>">Статистика по тесту</a><br>
<a href="<c:url value="/statistic/questionStatisticPage"/>">Статистика по вопросам</a><br>
<a href="<c:url value="/statistic/userStatisticPage"/>">Статистика пользователей</a><br>
<form action="/goHomeAdmin">
    <input type="submit" value="Назад">
</form>
</body>
</html>
