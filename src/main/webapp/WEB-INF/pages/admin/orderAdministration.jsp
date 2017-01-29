<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<%@ page import="online.shop.utils.constants.Attributes"%>
<%@ page import="online.shop.controller.i18n.LocaleHolder" %>
<%@ page import="online.shop.utils.constants.PagesPaths" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fmt:setLocale value="${sessionScope['locale']}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="${bundleFile}" var="msg"/>
<div class="top-bar">
    <a href="/admin">dashboard</a>
</div>
<jsp:include page="../localeSelector.jsp"/>

        <table width="608" border="2" align="center" cellpadding="3" cellspacing="4">
            <tr>
                <td>ID</td>
                <td>Customer</td>
                <td>Total price</td>
                <td width="40%"></td>
                <td>status</td>
                <td>details</td>
            </tr>
            <c:forEach var="value" items="${requestScope.orders}">
                <tr>
                    <td>${value.id}</td>
                    <td>${value.user.name}${" "}${value.user.surname}</td>
                    <td>${value.totalPrice}</td>
                    <td width="40%"></td>
                    <c:if test="${value.paid==true}">
                        <td>paid</td>
                    </c:if>
                    <c:if test="${value.paid==false}">
                        <td>unpaid</td>
                    </c:if>
                    <td><a href="/admin/orders/update?orderID=${value.id}"><button>Details</button></a></td>
                </tr>
            </c:forEach>
        </table>

</body>
</html>
