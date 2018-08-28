<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: olesi
  Date: 8/4/2018
  Time: 10:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="Messages"/>

<html>
<head>
    <title>Main Page</title>
</head>
<body>
<c:import url="/userHeader.jsp" charEncoding="utf-8"/>
<br/>

<%--//TODO change for name--%>
<h2><fmt:message key="message.welcome.user"/>${sessionScope.get("name")}</h2>

<div>
    <form action="${pageContext.request.contextPath}/user/findcar" method="post">
        <table>
            <tr>
                <th>From</th>
                <td>
                    <select name="addressFrom">
                        <option></option>
                        <c:forEach var="address" items="${addressList}">
                            <option value="${address.id}">${address.street} ${address.number}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th>To</th>
                <td>
                    <select name="addressTo">
                        <option></option>
                        <c:forEach var="address" items="${addressList}">
                            <option value="${address.id}">${address.street} ${address.number}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th>Type</th>
                <td>
                    <select name="carType">
                        <option value="standard">Standard</option>
                        <option value="premium">Premium</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button type="submit"><fmt:message key="message.findCar"/></button>
                </td>
            </tr>
        </table>

    </form>

</div>

</body>
</html>
