<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Admin Statistic Page</title>
    <link href="<c:url value="/resources/mystyle.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="mycont">
    <a href="<c:url value="/statistic/testStatisticPage"/>">Статистика по тесту</a><br>
    <a href="<c:url value="/statistic/questionStatisticPage"/>">Статистика по вопросам</a><br>
    <a href="<c:url value="/statistic/userStatisticPage"/>">Статистика пользователей</a><br>
    <form action="/goHomeAdmin">
        <input type="submit" value="Назад">
    </form>
</div>
</body>
</html>
