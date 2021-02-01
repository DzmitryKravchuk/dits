<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link href="<c:url value="/resources/mystyle.css"/>" rel="stylesheet" type="text/css"/>

</head>
<body>
<div class="mycont">
    <p><img src="<c:url value="/resources/devinc.png" />"></p>
    <form action="<c:url value="/login"/>" method="post">

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

        <c:if test="${param.error!=null}">
            <div class="alert alert-danger">
                <p>Invalid username and password</p>
            </div>
        </c:if>
        <c:if test="${param.logout!=null}">
            <div class="alert alert-success">
                <p>You have been logged out successfully</p>
            </div>
        </c:if>


        <div id="login">
            <input type="text" class="css-input" name="username" placeholder="Логин" value="" required>
            <br>
            <input type="password" class="css-input" name="password" placeholder="Пароль" value="" required>
            <br>
            <input type="submit" class="submit-button" value="Войти">
        </div>

    </form>

</div>
</body>
</html>
