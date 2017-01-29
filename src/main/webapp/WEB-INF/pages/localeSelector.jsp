<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmr" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="online.shop.utils.constants.Attributes"%>
<%@ page  import="online.shop.controller.i18n.LocaleHolder" %>
<%@ page import="online.shop.utils.constants.PagesPaths" %>

<fmt:setLocale value="${sessionScope['locale']}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="${bundleFile}" var="msg"/>

<div align="right">
    <c:forEach items="${SUPPORTED_LOCALES}" var="value">
        <c:choose>
            <c:when test="${value eq sessionScope['locale']}">
                <strong>
                        ${value.language}
                </strong>
            </c:when>
            <c:otherwise>
                <a href="?lang=${value.language}">${value.language}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>