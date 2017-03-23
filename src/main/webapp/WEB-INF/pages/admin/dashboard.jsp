<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<%@ page import="online.shop.utils.constants.Attributes"%>
<%@ page import="online.shop.controller.i18n.LocaleHolder" %>
<%@ page import="online.shop.utils.constants.PagesPaths" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<fmt:setLocale value="${sessionScope['locale']}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="${bundleFile}" var="msg"/>
    <jsp:include page="../localeSelector.jsp"/>
    <h1 align="center"><fmt:message key="control.panel" bundle="${msg}"/></h1>
    <p align="center"><fmt:message key="control.panel" bundle="${msg}"/></p>
    <table width="608" border="2" align="center" cellpadding="3" cellspacing="4">
    <c:choose>
        <c:when test="${user.role.getRoleName() eq 'admin'}">

            <tr>
                <td><div align="center"><a href="#"><fmt:message key="control.panel.categories" bundle="${msg}"/></a></div></td>
                <td><div align="center"><a href="#"><fmt:message key="control.panel.subcategories" bundle="${msg}"/></a></div></td>
                <td><div align="center"><a href="/admin/goods"><fmt:message key="control.panel.goods" bundle="${msg}"/></a></div></td>
            </tr>
            <tr>
                <td><div align="center"><a href="/admin/orders"><fmt:message key="control.panel.orders" bundle="${msg}"/></a></div></td>
                <td colspan="2" align="center"><div align="center"><a href="/admin/users"><fmt:message key="control.panel.users" bundle="${msg}"/></a></div></td>
            </tr>
        </c:when>
        <c:when test="${user.role.getRoleName() eq 'manager'}">
            <tr><td><div align="center"><a href="/admin/goods"><fmt:message key="control.panel.goods" bundle="${msg}"/></a></div></td></tr>
        </c:when>
    </c:choose>
    </table>
</body>
</html>
