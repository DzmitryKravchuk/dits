<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Test page</title>
</head>
<body>
<form action="/nextTestPage" method="get">
    <div>${question}</div>
    <br>
    <c:forEach items="${answers}" var="answer">
        <input type="radio" name="chosenAns" value="${answer}">${answer.description}<br>
    </c:forEach>
    <br>
    <input type="submit" value="Следующий">
</form>
<a href="<c:url value="/logout" />">Выйти</a>
</body>
</html>
