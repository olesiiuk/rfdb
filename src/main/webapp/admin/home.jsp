<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: olesi
  Date: 8/12/2018
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="Messages"/>

<html>
<head>
    <c:import url="/userHeader.jsp" charEncoding="utf-8"/>
    <title><fmt:message key="title.page.admin.home"/></title>
</head>
<body>
    <h2><fmt:message key="message.welcome.admin"/>${sessionScope.get("login")}</h2>

</body>
</html>
