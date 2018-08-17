<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: olesi
  Date: 8/5/2018
  Time: 12:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="Messages"/>

<html>
<head>
    <title><fmt:message key="title.page.login"/> </title>
</head>
<body>
<c:import url="guesHeader.jsp" charEncoding="UTF-8"/>
<br/>

<h2><fmt:message key="title.page.login"/></h2>
<form action="${pageContext.request.contextPath}/login" method="post">
    <table>
        <tr>
            <th><fmt:message key="message.email"/></th>
            <td><input type="email" name="email"></td>
        </tr>
        <tr>
            <th><fmt:message key="message.password"/></th>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td></td>
            <td><button type="submit"><fmt:message key="message.login"/></button></td>
        </tr>
    </table>
</form>
</body>
</html>
