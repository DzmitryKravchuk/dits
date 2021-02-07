<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Questions Page</title>
    <link href="<c:url value="/resources/mystyle.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="mycont">
    <h1>Edit test: ${testName}</h1>
    <form action="/saveQuestion">
        <input type="hidden" name="testName" value="${testName}">

        <input type="text" required placeholder="Добавить новый вопрос" id="question" name="question">

        <input type="submit" value="Отправить">
    </form>


    <table>
        <tr>
            <th>Вопрос</th>
            <th>Варианты ответа</th>
            <th>Список литературы</th>
            <th>Ссылки</th>
            <th>Редактирование</th>
        </tr>

        <c:forEach items="${questions}" var="question">
            <tr>
                <td>${question.description}</td>
                <td><c:forEach items="${question.answerSet}" var="answer">
                    ${answer.description}<br></c:forEach></td>
                <td><c:forEach items="${question.literatureSet}" var="literature">
                    ${literature.description}<br></c:forEach></td>
                <td><c:forEach items="${question.literatureSet}" var="literature">
                    ${literature.link.link}<br></c:forEach></td>
                <td><a href="/editQuestion/${question.questionId}">edit</a></td>
            </tr>

        </c:forEach>
    </table>

</div>
<form action="/goHomeTutor">
    <input type="submit" value="Home page">
</form>
</body>
</html>