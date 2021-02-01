<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Test page</title>
    <link href="<c:url value="/resources/mystyle.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
    <form action="/nextTestPage" method="get">
        <div class="mycont">
            <h3>${question}</h3>
        </div>
        <br>
        <c:forEach items="${answers}" var="answer">
            <input type="radio" class="choose" name="chosenAns" value="${answer.answerId}">${answer.description}<br>
        </c:forEach>
        <br>
        <div class="mycont">
        <input type="submit" value="Следующий">
        </div>
    </form>

</body>
</html>
