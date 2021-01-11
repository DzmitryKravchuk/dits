<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <div>
        <c:url var="loginUrl" value="/login"/>
        <form action="${loginUrl}" method="post">
            <c:if test="${param.error!=null}">
                <div class="alert alert-danger">
                    <p>Invalid userName and password.</p>
                </div>
            </c:if>
            <c:if test="${param.logout!=null}">
                <div class="alert alert-success">
                    <p>You have been logged out successfully.</p>
                </div>
            </c:if>
            <div>
                <input type="text" class="login-field" id="userName" name="ssoId" placeholder="Логин" value="required">
            </div>
            <div>
                <input type="password" class="password-field" id="password" name="password" placeholder="Пароль" value="required">
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" class="submit-button" value="Войти">
        </form>
    </div>
</div>
</body>
</html>
