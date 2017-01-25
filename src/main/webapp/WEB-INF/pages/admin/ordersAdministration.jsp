<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Goods</title>

</head>
<body id="body" style="overflow:hidden;">
<div class="top-bar">
    <a href="/admin">dashboard</a>
</div>

<table width="608" border="2" align="center" cellpadding="3" cellspacing="4">
    <tr>
        <td width="5%">ID</td>
        <td width="15%">User name</td>
        <td width="5%">Order sum</td>
        <td width="40%"></td>
        <td>Update</td>
    </tr>
    <c:forEach var="value" items="${orders}">
        <c:set var="total" value="${0}"/>
        <tr>
            <td>${value.id}</td>
            <td>${value.name}${" "}${value.surname}</td>
            <c:forEach items="${value.}">

            </c:forEach>
            <td width="50%">${value}</td>
            <td><a href="/admin/orders/update?goodsID=${value.id}"><button>Update</button></a></td>
            <td><button>Delete</button></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
