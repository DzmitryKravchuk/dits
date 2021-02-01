<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Test Statistic Page</title>
    <link href="<c:url value="/resources/mystyle.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div>
    <table>
        <tr>
            <th>Название теста</th>
            <th>Пройдено всего</th>
            <th>Процент правильно пройденных вопросов</th>
        </tr>
        <c:forEach items="${testStatistic}" var="item">
            <tr>
                <td>${item.name}</td>
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
