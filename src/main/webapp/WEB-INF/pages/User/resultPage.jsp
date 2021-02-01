<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Test Result Page</title>
    <link href="<c:url value="/resources/mystyle.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<table>
    <tr>
        <th>Название вопроса</th>
        <th>Правильно</th>
        <th>Рекомендуемая литература</th>
        <th>Ссылки на литературу</th>
    </tr>
    <c:forEach items="${statistic}" var="stat">
        <tr>
            <td>${stat.question.description}</td>
            <td>${stat.correct}</td>

            <td>
                <c:forEach items="${stat.question.literatureSet}" var="literature">
                    ${literature.description}
                    <br>
                </c:forEach>
            </td>

            <td>
                <c:forEach items="${stat.question.literatureSet}" var="literature">
                    <a href="${literature.link.link}"> ${literature.link.link} </a>
                    <br>
                </c:forEach>
            </td>

        </tr>
    </c:forEach>
</table>

<form action="/goHome">
    <input type="submit" value="Home page">
</form>
</body>
</html>
