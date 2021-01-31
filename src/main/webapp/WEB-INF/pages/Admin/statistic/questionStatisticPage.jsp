<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>questionStatisticPage</title>
</head>
<body>
<div>
    <table>
        <tr>
            <th>Формулировка вопроса</th>
            <th>Пройдено всего</th>
            <th>Процент правильныо пройденных вопросов</th>
        </tr>
        <c:forEach items="${questionStatistic}" var="item">
            <tr>
                <td>${item.description}</td>
                <td>${item.numberOfAnswers}</td>
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
