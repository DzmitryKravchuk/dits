<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Question Edit Page</title>
    <link href="<c:url value="/resources/mystyle.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="mycont">
    <h1 name="questionName">${question.description}</h1><br>
    <input type="hidden" name="questionId" value="${question.questionId}">
    <h3>Literature and link</h3>
    <table>
        <tr>
            <th>Название источника</th>
            <th>Ссылка на источник</th>
            <th>Удалить</th>
        </tr>
        <c:forEach items="${question.literatureSet}" var="literature">
            <tr>
                <td>${literature.description}</td>
                <td>${literature.link.link}</td>
                <td>
                    <a href="/deleteLiterature/${literature.literatureId}">delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <form action="/saveNewLiterature">
        <input type="hidden" name="questionId" value="${question.questionId}">
        <input type="text" required placeholder="Добавьте источник" id="literature" name="literature">
        <input type="text" required placeholder="Добавьте ссылку" id="link"
               name="link">

        <input type="submit" value="Отправить">
    </form>

    <h3>Answers</h3>
    <table>
        <tr>
            <th>Ответ</th>
            <th>Правильно</th>
            <th>Удалить</th>
        </tr>
        <c:forEach items="${question.answerSet}" var="answer">
            <tr>
                <td>${answer.description}</td>
                <td>${answer.correct}</td>
                <td>
                    <a href="/deleteAnswer/${answer.answerId}">delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <form action="/saveNewAnswer">
        <input type="hidden" name="questionId" value="${question.questionId}">
        <input type="text" required placeholder="Добавьте вариант ответа" id="answer" name="answer">
        <input type="checkbox" name="correct" value="true">Правильно

        <input type="submit" value="Отправить">
    </form>


</div>


<form action="/backToQuestion4topic/${question.description}">
    <input type="submit" value="Question list page">
</form>
</body>
</html>
