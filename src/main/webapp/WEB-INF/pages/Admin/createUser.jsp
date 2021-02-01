<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Create User</title>
    <link href="<c:url value="/resources/mystyle.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="mycont">
    <form:form method="post" action="/createUser" modelAttribute="user">

        <c:forEach items="${roles}" var="role">
            <input type="checkbox" name="chosenRole" value="${role.name}">${role.name}<br>
        </c:forEach>

        <table>
            <tr>
                <td><form:input path="lastName" placeholder="Фамилия" required="true"/></td>
            </tr>
            <tr>
                <td><form:input path="firstName" placeholder="Имя" required="true"/></td>
            </tr>
            <!--tr>
                <td><input placeholder="Отчество"/></td>
            </tr-->
            <tr>
                <td><form:input path="password" placeholder="Пароль" required="true"/></td>
            </tr>
            <tr>
                <td><form:input path="login" placeholder="Логин" required="true"/></td>
            </tr>
            <!--tr>
                <td><input placeholder="Почта"/></td>
            </tr-->
            <tr>
                <td><input type="submit" value="Сохранить пользователя"/></td>
            </tr>
        </table>
    </form:form>
</div>
<script>
    setTimeout(function () {
        document.getElementById("success").style.display = 'none';
    }, 4000)
</script>
<br>
<div class="mycont">
    <form action="/goHomeAdmin">
        <input type="submit" value="Назад">
    </form>
</div>
</body>
</html>
