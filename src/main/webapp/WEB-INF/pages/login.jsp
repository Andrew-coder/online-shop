<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<%@ page import="online.shop.utils.constants.Attributes"%>
<%@ page import="online.shop.controller.i18n.LocaleHolder" %>
<%@ page import="online.shop.utils.constants.PagesPaths" %>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="/css/style.css" />
    </head>
    <body>
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setBundle basename="${bundleFile}" var="msg"/>
    <div class="top-bar">
        <a href="/"><fmt:message key="shop.home" bundle="${msg}"/> </a>
    </div>

        <div class="login-page">
            <div class="form">
                <form class="login-form" method="post" action="/login">
                    <input type="text" name ="login" placeholder="<fmt:message key="placeholder.email" bundle="${msg}"/>"/>
                    <input type="password" name="password" placeholder="<fmt:message key="placeholder.password" bundle="${msg}"/>"/>
                    <button><fmt:message key="shop.login" bundle="${msg}"/></button>
                    <p class="message"><fmt:message key="not.registered" bundle="${msg}"/> <a href="/register"><fmt:message key="create.account" bundle="${msg}"/></a></p>
                </form>
            </div>
        </div>
    </body>
</html>