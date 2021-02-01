<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personal Statistic Page</title>
    <link href="<c:url value="/resources/mystyle.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<table>
    <tr>
        <th>Ф.И.О.</th>
        <th>Название теста</th>
        <th>Формулировка вопроса</th>
        <th>Пройдено всего</th>
        <th>Процент правильно отвеченных вопросов</th>
    </tr>
<c:forEach items="${personalStatList}" var="list">
    <tr>
        <td>${list.userName}</td>
        <td>${list.testName}</td>
        <td>${list.questionDescription}</td>
        <td>${list.numberOfAnswer}</td>
        <td>${list.questionRate}</td>
    </tr>

</c:forEach>

</table>
<form action="/goHome">
    <input type="submit" value="Home page">
</form>
</body>
</html>
