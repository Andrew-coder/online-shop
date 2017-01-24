<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="online.shop.utils.constants.Attributes" %>
<html>
<head>
    <title>Title</title>
    <style>
        input{
            display: table;
            font-size:11px;
            padding:4px 2px;
            border:solid 1px #aacfe4;
            width:70px;
            margin:2px 0 20px 10px;
        }
    </style>
</head>
<body>
    <div class="top-bar">
        <a href="/admin">dashboard</a>
    </div>
    <c:if test="${requestScope.get(Attributes.ERRORS).hasErrors()}">
        <div align="top">
            <c:forEach items="${requestScope.get(Attributes.ERRORS).getErrorsAttributes()}" var="value">
                <br>${requestScope.get(Attributes.ERRORS).get(value)}
            </c:forEach>
        </div>
    </c:if>
    <jsp:useBean id="user" scope="request" class="online.shop.model.entity.User">
    </jsp:useBean>
    <div>
        <form method="post" action="/user/update" class="input">
            <table>
                <tr>
                    <td><label>name</label></td>
                    <td><input type="text" value="${user.name}"></td>
                </tr>
                <tr>
                    <td><label>surname</label></td>
                    <td><input type="text" value="${user.surname}"></td>
                </tr>
                <tr>
                    <td><label>email</label></td>
                    <td><input type="text" value="${user.email}"></td>
                </tr>
                <tr>
                    <td><label>password</label></td>
                    <td><input type="text" value="${user.password}"></td>
                </tr>
                <tr>
                    <td><label>birthDate</label></td>
                    <td><input type="text" value="${user.birthDate.year}-${user.birthDate.month}-${user.birthDate.day}"></td>
                </tr>
            </table>
            <input type="submit" value="Update">
        </form>
    </div>
</body>
</html>
