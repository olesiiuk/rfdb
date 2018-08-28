<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="Messages"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:import url="/userHeader.jsp" charEncoding="utf-8"/>
<div>

    <c:set var="noAvialableCars" value="${noCarsMessage}"/>
    <c:if test="${not empty noAvialableCars}">

        <h2><fmt:message key="message.noAvailableCars"/></h2>
        <form action="${pageContext.request.contextPath}/user/home" method="post">
            <button type="submit"><fmt:message key="title.page.admin.user"/></button>
        </form>
    </c:if>

    <table>
        <tr>
            <th>Price</th>
            <td>${price}</td>
        </tr>
        <tr>
            <th>Time to wait</th>
            <td>${timeToArrive}</td>
        </tr>
        <form action="${pageContext.request.contextPath}/user/confirm" method="post">
            <button type="submit">Confirm Order</button>
        </form>
    </table>
</div>

</body>
</html>
