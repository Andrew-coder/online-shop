<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<%@ page import="online.shop.utils.constants.Attributes"%>
<%@ page import="online.shop.controller.i18n.LocaleHolder" %>
<%@ page import="online.shop.utils.constants.PagesPaths" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>

<fmt:setLocale value="${sessionScope['locale']}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="${bundleFile}" var="msg"/>

    <div class="top-bar">
        <a href="/"><fmt:message key="shop.home" bundle="${msg}"/></a>
    </div>
    <br>
    <table align="center">
        <c:if test="${requestScope.errors!=null}">
            <c:forEach items="${requestScope.errors.getErrorsAttributes()}" var="value">
                <tr><td align="center">${requestScope.errors.getErrors().get(value)}</td></tr>
            </c:forEach>
        </c:if>
    </table>
    <div class="login-page">
        <div class="form">
            <form class="login-form" method="post" action="/register">
            <c:if test="${requestScope.errors!=null}">
                <input type="text" name ="name" placeholder="<fmt:message key="placeholder.name" bundle="${msg}"/>" value="${requestScope.previousUserName}"/>
                <input type="text" name ="surname" placeholder="<fmt:message key="placeholder.surname" bundle="${msg}"/>" value="${requestScope.previousUserSurname}"/>
                <input type="text" name ="email" placeholder="<fmt:message key="placeholder.email" bundle="${msg}"/>" value="${requestScope.previousUserEmail}"/>
                <input type="text" name ="birthDate" placeholder="<fmt:message key="placeholder.birth.date" bundle="${msg}"/>" value="${requestScope.previousUserDate}"/>
                <input type="password" name="password" placeholder="<fmt:message key="placeholder.password" bundle="${msg}"/>" value="${requestScope.previousUserPassword}"/>
                <button><fmt:message key="shop.register" bundle="${msg}"/></button>
            </c:if>
            <c:if test="${requestScope.errors==null}">
                <input type="text" name ="name" placeholder="<fmt:message key="placeholder.name" bundle="${msg}"/>"/>
                <input type="text" name ="surname" placeholder="<fmt:message key="placeholder.surname" bundle="${msg}"/>"/>
                <input type="text" name ="email" placeholder="<fmt:message key="placeholder.email" bundle="${msg}"/>"/>
                <input type="text" name ="birthDate" placeholder="<fmt:message key="placeholder.birth.date" bundle="${msg}"/>"/>
                <input type="password" name="password" placeholder="<fmt:message key="placeholder.password" bundle="${msg}"/>"/>
                <button><fmt:message key="shop.register" bundle="${msg}"/></button>
            </c:if>
            </form>
        </div>
    </div>
</body>
</html>