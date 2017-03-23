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
<jsp:include page="../localeSelector.jsp"/>
    <div class="top-bar">
        <a href="/admin">dashboard</a>
    </div>


        <table width="608" border="2" align="center" cellpadding="3" cellspacing="4">
            <form method="post" action="/admin/orders/update">
            <tr>
                <td>>Order id</td>
                <td><input name="orderID" type="text" value="${requestScope.order.id}" readonly></td>
            </tr>
            <tr>
                <td>Customer</td>
                <td>${requestScope.order.user.name}${" "}${requestScope.order.user.surname}</td>
            </tr>
            <tr>
                <td>order date time</td>
                <td>${requestScope.order.orderDate.toString()}</td>
            </tr>
            <tr><td colspan="2">Goods</td><tr>

                <c:forEach items="${requestScope.order.goodsItems}" var="entry">
                    <tr>
                        <td>${entry.key.title}</td>
                        <td>${"x"}${entry.value}</td>
                    </tr>
                </c:forEach>
            <tr>
            <td>Total</td>
            <td>${requestScope.order.totalPrice}</td>
            </tr>
                <tr>
                    <td>status</td>
                    <td>
                        <select name="status">
                        <c:if test="${requestScope.order.paid==true}">
                            <option selected="selected">paid</option>
                            <option>unpaid</option>
                        </c:if>
                        <c:if test="${requestScope.order.paid==false}">
                            <option>paid</option>
                            <option selected="selected">unpaid</option>
                        </c:if>
                        </select>
                    </td>
                </tr>
                <tr><td colspan="2">
                <input type="submit" value="update"></td></tr>
            </form>
        </table>


</body>
</html>