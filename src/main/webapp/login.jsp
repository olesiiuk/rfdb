<%--
  Created by IntelliJ IDEA.
  User: olesi
  Date: 8/5/2018
  Time: 12:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login page</h2>
<form action="${pageContext.request.contextPath}/login" method="post">
    <table>
        <tr>
            <th>Email</th>
            <td><input type="email" name="email"></td>
        </tr>
        <tr>
            <th>Password</th>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td></td>
            <td><button type="submit">Log in</button></td>
        </tr>
    </table>
</form>
</body>
</html>
