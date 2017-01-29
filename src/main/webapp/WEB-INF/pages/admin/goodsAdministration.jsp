<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<%@ page import="online.shop.utils.constants.Attributes"%>
<%@ page import="online.shop.controller.i18n.LocaleHolder" %>
<%@ page import="online.shop.utils.constants.PagesPaths" %>
<html>
<head>
    <title>Goods</title>

</head>
<body id="body" style="overflow:hidden;">
<fmt:setLocale value="${sessionScope['locale']}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="${bundleFile}" var="msg"/>
    <div class="top-bar">
        <a href="/admin">dashboard</a>
    </div>
    <jsp:include page="../localeSelector.jsp"/>
    <div>
        <table width="608" border="2" align="center" cellpadding="3" cellspacing="4">
            <tr>
                <td>ID</td>
                <td>Title</td>
                <td width="50%"></td>
                <td>Update</td>
            </tr>
            <c:forEach var="value" items="${goods}">
                <tr>
                    <td>${value.id}</td>
                    <td>${value.title}</td>
                    <td width="50%"></td>
                    <td><a href="/admin/goods/update?goodsID=${value.id}"><button>Update</button></a></td>
                </tr>
            </c:forEach>
        </table>
        <div align="center">
        <a href="/admin/goods/create"><button type="button">Create new goods</button></a>
        </div>
    </div>
</body>
</html>
