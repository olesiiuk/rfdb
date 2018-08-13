<%--
  Created by IntelliJ IDEA.
  User: olesi
  Date: 8/4/2018
  Time: 10:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
    <h2>Hello, ${sessionScope.get("login")}</h2>
    <form method="post" action="${pageContext.request.contextPath}/logout">
        <button type="submit">Logout</button>
    </form>
</body>
</html>
