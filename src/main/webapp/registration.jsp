<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: olesi
  Date: 8/4/2018
  Time: 10:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="Messages"/>

<html>
<head>
    <title>Registration</title>
</head>
<body>

<c:if test="${not empty errorMessage}">
    <h2>${errorMessage}</h2>
</c:if>
<c:import url="header.jsp" charEncoding="utf-8"/>
<br/>

<h2><fmt:message key="title.page.registration"/></h2>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <table>
        <tr>
            <th><fmt:message key="message.email"/> </th>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <th><fmt:message key="message.name"/> </th>
            <td><input type="text" name="userName"/></td>
        </tr>
        <tr>
            <th><fmt:message key="message.password"/> </th>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <th><fmt:message key="message.password.confirm"/></th>
            <td><input type="password" name="confirmPassword"></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <button type="submit"><fmt:message key="title.page.registration"/></button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
