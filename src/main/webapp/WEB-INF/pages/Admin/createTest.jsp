<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create Test</title>
    <link href="<c:url value="/resources/mystyle.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="mycont">
    <form action="/saveNewTest">
        <p>
            <input type="text" required list="topics" placeholder="Выберите тему" id="topic" name="topic">
            <datalist id="topics" name="topicName">
                <c:forEach items="${topics}" var="topic">
                    <option value="${topic.name}"></option>
                </c:forEach>
            </datalist>
        </p>
        <p>
            <input type="text" required list="tests" placeholder="Выберите тест" id="test" name="test">
            <%--  <input list="tests" required name ="testName"--%>
            <datalist id="tests">
                <c:forEach items="${tests}" var="test">
                    <option value="${test.name}"></option>
                </c:forEach>
            </datalist>
        </p>

        <input type="submit" value="Отправить">
    </form>


    <form action="/goHomeAdmin">
        <input type="submit" value="Назад">
    </form>
</div>
</body>
</html>
