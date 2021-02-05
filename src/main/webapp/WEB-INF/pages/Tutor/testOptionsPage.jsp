<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test Options Page</title>
    <link href="<c:url value="/resources/mystyle.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="mycont">
    <form method="get" action="/question4topic">
        <p>
            <select id="testName" name="testName">
                <c:forEach items="${tests}" var="test">
                    <option value="${test.name}">${test.name}</option>
                </c:forEach>
            </select>
        </p>
        <input type="submit" value="К списку вопросов">
    </form>
    <form action="/goHomeTutor">
        <input type="submit" value="Home page">
    </form>
</div>
</body>
</html>
