<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="online.shop.utils.constants.Attributes" %>
<div id="templatemo_header">
    <div id="site_title"><h1><a href="#">Online Shop</a></h1></div>
    <div id="header_right">
        <p>
            <c:choose>
                <c:when  test="${sessionScope.user!=null}">
                    <a href="#">My Account</a> | <a href="#">My Cart</a> | <a href="#">Checkout</a> | ${user.name} ${' '} ${user.surname} | <a href="./logout">Log Out</a></p>
                </c:when>
                <c:otherwise>
                    <a href="#">My Account</a> | <a href="#">My Cart</a> | <a href="#">Checkout</a> | <a href="./login">Log In</a></p>
                </c:otherwise>
            </c:choose>
        <p>
            <c:if test="${sessionScope.basket!=null}">
                Shopping Cart: <strong>${basket.getGoodsItems().size()} items</strong> ( <a href="./basket">Show Basket</a> )
            </c:if>
            <c:if test="${sessionScope.basket==null}">
                Shopping Cart: <strong>0 items</strong> ( <a href="./basket">Show Basket</a> )
            </c:if>
        </p>
    </div>
    <div class="cleaner"></div>
</div>