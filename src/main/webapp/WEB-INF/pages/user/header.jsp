<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmr" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="online.shop.utils.constants.Attributes"%>
<%@ page import="online.shop.controller.i18n.LocaleHolder" %>
<%@ page import="online.shop.utils.constants.PagesPaths" %>

<fmt:setLocale value="${sessionScope['locale']}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="${bundleFile}" var="msg"/>
<div id="templatemo_header">
    <div id="site_title"><h1><a href="#"><fmt:message key="shop" bundle="${msg}"/></a></h1></div>
    <div id="header_right">
        <p>
            <c:choose>
                <c:when  test="${sessionScope.user!=null}">
                    <a href="#"><fmt:message key="shop.account" bundle="${msg}"/></a> | <a href="#"><fmt:message key="shop.card" bundle="${msg}"/></a> | <a href="#"><fmt:message key="shop.checkout" bundle="${msg}"/></a> | ${user.name} ${' '} ${user.surname} | <a href="./logout"><fmt:message key="shop.logout" bundle="${msg}"/></a></p>
                </c:when>
                <c:otherwise>
                    <a href="#"><fmt:message key="shop.account" bundle="${msg}"/></a> | <a href="#"><fmt:message key="shop.card" bundle="${msg}"/></a> | <a href="#"><fmt:message key="shop.checkout" bundle="${msg}"/></a> | <a href="./login"><fmt:message key="shop.login" bundle="${msg}"/></a></p>
                </c:otherwise>
            </c:choose>
        <p>
            <c:if test="${sessionScope.basket!=null}">
                <fmt:message key="shop.shopping.card" bundle="${msg}"/> <strong>${basket.getGoodsItems().size()} <fmt:message key="shop.basket.items" bundle="${msg}"/></strong> ( <a href="./basket"><fmt:message key="shop.show.basket" bundle="${msg}"/></a> )
            </c:if>
            <c:if test="${sessionScope.basket==null}">
                <fmt:message key="shop.shopping.card" bundle="${msg}"/> <strong>0 <fmt:message key="shop.basket.items" bundle="${msg}"/></strong> ( <a href="./basket"><fmt:message key="shop.show.basket" bundle="${msg}"/></a> )
            </c:if>
        </p>
    </div>
    <div class="cleaner"></div>
</div>