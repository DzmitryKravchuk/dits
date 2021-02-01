<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>User Statistic Page</title>
    <link href="<c:url value="/resources/mystyle.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div>
    <table>
        <tr>
            <th>Имя пользователя</th>
            <th>Название теста</th>
            <th>Пройдено всего раз</th>
            <th>Процент правильных ответов</th>
        </tr>
        <c:forEach items="${userStatistic}" var="item">
            <tr>
                <td>${item.userName}</td>
                <td>${item.testName}</td>
                <td>${item.numberPassedTimes}</td>
                <td>${item.percentOfRightAnswers}</td>
            </tr>
        </c:forEach>
    </table>
    <form action="/goBackToStatistic">
        <input type="submit" value="Назад">
    </form>
</div>
</body>
</html>
