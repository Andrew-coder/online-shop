<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Goods</title>

</head>
<body id="body" style="overflow:hidden;">
    <a href="/admin">Dashboard</a>

    <table width="608" border="2" align="center" cellpadding="3" cellspacing="4">
        <tr>
            <td>ID</td>
            <td>Title</td>
            <td width="50%"></td>
            <td>Update</td>
            <td>Delete</td>
        </tr>
        <c:forEach var="value" items="${goods}">
            <tr>
                <td>${value.id}</td>
                <td>${value.title}</td>
                <td width="50%"></td>
                <td><button>Update</button></td>
                <td><a href="/admin/goods/delete?goodsID="><button>Delete</button></a></td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
