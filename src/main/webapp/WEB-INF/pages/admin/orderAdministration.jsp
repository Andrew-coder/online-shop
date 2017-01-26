<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="top-bar">
    <a href="/admin">dashboard</a>
</div>

    <form method="post" action="/admin/orders/update">
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
                    <td width="50%"></td>
                    <td>
                        <select>
                            <c:if test="${value.paid==true}">
                                <option selected="selected">paid</option>
                                <option>unpaid</option>
                            </c:if>
                            <c:if test="${value.paid==false}">
                                <option>paid</option>
                                <option selected="selected">unpaid</option>
                            </c:if>
                        </select>
                    </td>
                    <td><a href="/admin/orders/details?orderID=${value.id}"><button>Details</button></a></td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit">
    </form>
</body>
</html>
