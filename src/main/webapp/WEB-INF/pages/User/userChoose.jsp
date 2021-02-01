<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chose Test</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
    <link href="<c:url value="/resources/mystyle.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="mycont">
    <form action="/goTest">

        <select id="themes" name="themes">
            <c:forEach items="${topics}" var="topic">
                <option>${topic.name}</option>
            </c:forEach>
        </select>
        <br>
        <select id="tests" name="testName">
            <option>Выберите тест</option>
        </select>

        <br>

        <input type="submit" value="Пройти тестирование">

    </form>

    <script>
        $().ready(function () {
            $("#themes").change(function (event) {
                $.ajax({
                    url: "/userChoose",
                    type: "POST",
                    dataType: "json",
                    data: {topic: $(event.target).val()},
                })
                    .done(function (data) {
                        setTests(data)
                    })
                    .fail(function (xhr, status, error) {
                        alert(xhr.responseText + '|\n' + status + '|\n' + error);
                    });
            });
        });
        var setTests = function (data) {
            $('#tests').find('option').remove();
            $.each(data, function (index, value) {
                $('#tests').append(new Option(value, value));
            });
        };
    </script>
</div>
</body>
</html>
