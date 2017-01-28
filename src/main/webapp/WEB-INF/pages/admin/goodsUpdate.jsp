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
    <jsp:useBean id="goods" scope="request" class="online.shop.model.entity.Goods">
    </jsp:useBean>
    <div>
        <form method="post" action="/goods/update" class="input">
            <table>
                <tr>
                    <td><label>title</label></td>
                    <td><input type="text" value="${goods.title}" style="width: 200px;"></td>
                </tr>
                <tr>
                    <td><label>price</label></td>
                    <td><input type="text" value="${goods.price}"></td>
                </tr>
                <tr>
                    <td><label>goods status</label></td>
                    <td>
                        <select>
                            <c:choose>
                                <c:when test="${goods.goodsStatus.getGoodsStatus() eq 'available'}">
                                    <option id="available" value="available" selected>available</option>
                                    <option id="ends" value="ends">ends</option>
                                    <option id="ended" value="ended">ended</option>
                                </c:when>
                                <c:when test="${goods.goodsStatus.getGoodsStatus() eq 'ends'}">
                                    <option id="available" value="available">available</option>
                                    <option id="ends" value="ends" selected>ends</option>
                                    <option id="ended" value="ended">ended</option>
                                </c:when>
                                <c:when test="${goods.goodsStatus.getGoodsStatus() eq 'ended'}">
                                    <option id="available" value="available">available</option>
                                    <option id="ends" value="ends">ends</option>
                                    <option id="ended" value="ended" selected>ended</option>
                                </c:when>
                            </c:choose>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><label>description</label></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="text"  value="${goods.description}" style="width: 350px; height: 50px;"></td>
                </tr>
            </table>
            <label>select subcategory</label>
            <select >
                <c:forEach items="${requestScope.subcategories}" var="value">
                    <option id="${value.id}" value="${value.title}">${value.title}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Update">
        </form>
    </div>
</body>
</html>
