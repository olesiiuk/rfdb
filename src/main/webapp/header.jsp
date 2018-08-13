<%--
  Created by IntelliJ IDEA.
  User: olesi
  Date: 8/13/2018
  Time: 11:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

    <fmt:setLocale value="${language}"/>
    <fmt:setBundle basename="Messages"/>

</head>
<body>

<form method="post">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
        <option value="ua" ${language == 'ua' ? 'selected' : ''}>Ua</option>
    </select>
</form>

</body>
</html>
