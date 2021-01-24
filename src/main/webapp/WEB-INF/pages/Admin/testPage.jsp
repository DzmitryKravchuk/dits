<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test Page</title>
</head>
<body>
<h2>Select Options:</h2>
<form ACTION="/testPage">
    <input type="checkbox" name="id" value="first"> first<BR>
    <input type="checkbox" name="id" value="second"> second<BR>
    <input type="checkbox" name="id" value="third"> third<BR>
    <input type="checkbox" name="id" value="fourth"> fourth<BR>
    <input type="checkbox" name="id" value="fifth"> fifth <BR>
    <input type="submit" value="Submit">
</form>
<%
    String s[] = request.getParameterValues("id");
    if (s != null && s.length != 0) {
        out.println("You have selected the options: ");
        for (int i = 0; i < s.length; i++) {
            out.println(s[i] + "\n");
        }
        out.println("Thank you!");
    }
%>
</body>
</html>
