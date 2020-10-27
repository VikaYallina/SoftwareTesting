<%--
  Created by IntelliJ IDEA.
  User: Вика
  Date: 24.10.2020
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Title</title>
</head>
<body>
<form:form id="regForm" modelAttribute="user" action="registrationProcess" method="POST">

    <table align="center">
        <tr>
            <td><form:label path="username">Username</form:label></td>
            <td><form:input path="username" name="username" id="username" /></td>
            <td><form:errors path="username" /></td>
        </tr>
        <tr>
            <td><form:label path="password">Password</form:label></td>
            <td><form:password path="password" name="password" id="password" /></td>
            <td><form:errors path="password" /></td>
        </tr>
        <tr>
            <td></td>
            <td><form:button id="register" name="register">Register</form:button></td>
        </tr>
        <tr></tr>
        <tr>
            <td></td>
            <td><a href="showLoginPage">Login</a></td>
        </tr>
    </table>
</form:form>
</body>
</html>
