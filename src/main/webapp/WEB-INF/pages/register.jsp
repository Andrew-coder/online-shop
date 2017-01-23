<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="online.shop.utils.constants.PagesPaths" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>

    <div class="top-bar">
        <a href="/">Home</a>
    </div>
    <table align="center">
        <c:if test="${sessionScope.errors!=null}">
            <c:forEach items="${sessionScope.errors.getErrorsAttributes()}" var="value">
                <tr><td align="center">${sessionScope.errors.getErrors().get(value)}</td></tr>
            </c:forEach>
        </c:if>
    </table>
    <div class="login-page">
        <div class="form">
            <form class="login-form" method="post" action="/register">
                <input type="text" name ="name" placeholder="name"/>
                <input type="text" name ="surname" placeholder="surname"/>
                <input type="text" name ="email" placeholder="email"/>
                <input type="text" name ="birthDate" placeholder="birth date"/>
                <input type="password" name="password" placeholder="password"/>
                <button>register</button>
            </form>
        </div>
    </div>
</body>
</html>