<html>
<head>
    <title>Home page</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


    <c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

    <fmt:setLocale value="${language}"/>
    <fmt:setBundle basename="Messages"/>
</head>
<body>
<c:import url="guesHeader.jsp" charEncoding="utf-8"/>
<br/>

<h2><fmt:message key="message.welcome"/></h2>
    <a href="registration.jsp"><fmt:message key="message.registration"/> </a>
    <br/>
    <a href="login.jsp"><fmt:message key="message.page.login"/> </a>
</body>
</html>
