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
                <td><a href="/admin/goods/update?goodsID=${value.id}"><button>Update</button></a></td>
                <td><button>Delete</button></td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
