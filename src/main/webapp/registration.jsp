<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: olesi
  Date: 8/4/2018
  Time: 10:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h2>Registration page</h2>
<c:if test="${not empty errorMessage}">
    <h2>${errorMessage}</h2>
</c:if>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <table>
        <tr>
            <th>Email</th>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <th>Password</th>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <th>Confirm password</th>
            <td><input type="password" name="confirmPassword"></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <button type="submit">Registration</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
