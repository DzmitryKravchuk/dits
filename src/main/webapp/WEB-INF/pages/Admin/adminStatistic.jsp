<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="<c:url value="/goHomeAdmin" />">Статистика по тесту</a><br>
<a href="<c:url value="/goHomeAdmin" />">Статистика по вопросам</a><br>
<a href="<c:url value="/goHomeAdmin" />">Статистика пользователей</a><br>
<form action="/goHomeAdmin">
    <input type="submit" value="Назад">
</form>
</body>
</html>
