<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="online.shop.model.entity.RoleType" %>
<%@ page import="online.shop.model.entity.User" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h1 align="center">Control Panel</h1>
    <p align="center">Control Panel</p>
    <table width="608" border="2" align="center" cellpadding="3" cellspacing="4">
    <c:choose>
        <c:when test="${user.role.getRoleName() eq 'admin'}">

            <tr>
                <td><div align="center"><a href="#">Categories</a></div></td>
                <td><div align="center"><a href="#">Subcategories</a></div></td>
                <td><div align="center"><a href="/admin/goods">Goods</a></div></td>
            </tr>
            <tr>
                <td><div align="center"><a href="/admin/orders">Orders</a></div></td>
                <td colspan="2" align="center"><div align="center"><a href="/admin/users">Users</a></div></td>
            </tr>
        </c:when>
        <c:when test="${user.role.getRoleName() eq 'manager'}">
            <tr><td><div align="center"><a href="/admin/goods">Goods</a></div></td></tr>
        </c:when>
    </c:choose>
    </table>
</body>
</html>
