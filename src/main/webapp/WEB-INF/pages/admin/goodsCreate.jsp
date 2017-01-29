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
<table align="center">
    <c:if test="${requestScope.errors!=null}">
        <c:forEach items="${requestScope.errors.getErrorsAttributes()}" var="value">
            <tr><td align="center">${requestScope.errors.getErrors().get(value)}</td></tr>
        </c:forEach>
    </c:if>
</table>
<jsp:useBean id="goods" scope="request" class="online.shop.model.entity.Goods">
</jsp:useBean>
<div>
    <form method="post" action="/admin/goods/create" class="input">
        <table>
            <tr>
                <td><label>title</label></td>
                <td><input type="text" name="title" style="width: 200px;"></td>
            </tr>
            <tr>
                <td><label>price</label></td>
                <td><input type="text" name="price"></td>
            </tr>
            <tr>
                <td><label>goods status</label></td>
                <td>
                    <select name="goodsStatus">
                        <option id="available" value="available">available</option>
                        <option id="ends" value="ends">ends</option>
                        <option id="ended" value="ended">ended</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2"><label>description</label></td>
            </tr>
            <tr>
                <td colspan="2"><input type="text" name="description" style="width: 350px; height: 50px;"></td>
            </tr>
        </table>
        <label>select subcategory</label>
        <select name="subcategory">
            <c:forEach items="${requestScope.subcategories}" var="value">
                <option id="${value.id}" value="${value.title}">${value.title}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Create">
    </form>
</div>
</body>
</html>
