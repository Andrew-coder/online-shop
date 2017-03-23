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
<div id="templatemo_footer">
    <p><a href="/"><fmt:message key="shop.home" bundle="${msg}"/></a> | <a href="#"><fmt:message key="shop.products" bundle="${msg}"/></a> | <a href="#"><fmt:message key="shop.about" bundle="${msg}"/></a> | <a href="#"><fmt:message key="shop.faq" bundle="${msg}"/></a> | <a href="#"><fmt:message key="shop.checkout" bundle="${msg}"/></a> | <a href="#"><fmt:message key="shop.contact" bundle="${msg}"/></a>
    </p>

    Copyright © 2017 <a href="#"><fmt:message key="shop.owner" bundle="${msg}"/></a>

</div>