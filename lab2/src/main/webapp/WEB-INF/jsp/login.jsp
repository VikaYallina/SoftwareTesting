<%--
  Created by IntelliJ IDEA.
  User: Вика
  Date: 24.10.2020
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login</title>
</head>
<body>
    <form:form method="post" id="loginForm" modelAttribute="login" action="loginProcess">
        <table align="center">
            <tr>
                <td><form:label path="username">Username: </form:label></td>
                <td><form:input path="username" id="username" /></td>
            </tr>
            <tr>
                <td><form:label path="password">Password: </form:label></td>
                <td><form:input path="password" id="password" /></td>
            </tr>
            <tr>
                <td></td>
                <td align="left"><form:button name="login">Login</form:button></td>
            </tr>
            <tr></tr>
            <tr>
                <td></td>
                <td>
                    <a href="home.jsp">Home</a>
                </td>
            </tr>
        </table>
    </form:form>
    <table align="center">
        <tr>
            <td style="font-style: italic; color: red;">${message}</td>
        </tr>
    </table>
</body>
</html>
