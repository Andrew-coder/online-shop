<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<%@ page import="online.shop.utils.constants.Attributes"%>
<%@ page import="online.shop.controller.i18n.LocaleHolder" %>
<%@ page import="online.shop.utils.constants.PagesPaths" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<fmt:setLocale value="${sessionScope['locale']}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="${bundleFile}" var="msg"/>
<jsp:include page="../localeSelector.jsp"/>
    <div class="top-bar">
        <a href="/admin">dashboard</a>
    </div>

        <div>
        <table width="608" border="2" align="center" cellpadding="3" cellspacing="4">
            <tr>
                <td>ID</td>
                <td>Name</td>
                <td width="50%"></td>
                <td>Update</td>
                <td>Add to blacklist</td>
            </tr>
            <c:forEach var="value" items="${users}">
                <tr>
                    <td>${value.id}</td>
                    <td>${value.name}${" "}${value.surname}</td>
                    <td width="50%"></td>
                    <td><a href="/admin/users/update?userID=${value.id}"><button>Update</button></a></td>
                    <c:choose>
                        <c:when test="${value.role.getRoleName() eq 'user'}">
                            <c:if test="${requestScope.get(value.id.toString())}">
                                <td><a href="/admin/users/remove?userID=${value.id}"><button>remove from blacklist</button></a></td>
                            </c:if>
                            <c:if test="${not requestScope.get(value.id.toString())}">
                                <td><a href="/admin/users/add?userID=${value.id}"><button>add to blacklist</button></a></td>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
        <div align="center">
            <a href="/admin/users/create"><button type="button">Create new user</button></a>
        </div>
    </div>
</body>
</html>