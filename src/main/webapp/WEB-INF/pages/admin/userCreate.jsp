<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<%@ page import="online.shop.utils.constants.Attributes"%>
<%@ page import="online.shop.controller.i18n.LocaleHolder" %>
<%@ page import="online.shop.utils.constants.PagesPaths" %>
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
<fmt:setLocale value="${sessionScope['locale']}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="${bundleFile}" var="msg"/>
<div class="top-bar">
    <a href="/admin">dashboard</a>
</div>
<jsp:include page="../localeSelector.jsp"/>
<table align="center">
    <c:if test="${requestScope.errors!=null}">
        <c:forEach items="${requestScope.errors.getErrorsAttributes()}" var="value">
            <tr><td align="center">${requestScope.errors.getErrors().get(value)}</td></tr>
        </c:forEach>
    </c:if>
</table>
<jsp:useBean id="user" scope="request" class="online.shop.model.entity.User">
</jsp:useBean>
<div>
    <form method="post" action="/admin/users/create">
        <table>
            <c:if test="${requestScope.errors==null}">
                <tr>
                    <td><label>name</label></td>
                    <td><input type="text" name ="name"></td>
                </tr>
                <tr>
                    <td><label>surname</label></td>
                    <td><input type="text" name ="surname"></td>
                </tr>
                <tr>
                    <td><label>email</label></td>
                    <td><input type="email" name ="email"></td>
                </tr>
                <tr>
                    <td><label>password</label></td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td><label>birthDate</label></td>
                    <td><input type="text" name ="birthDate"></td>
                </tr>
                <tr>
                    <td><label>privileges</label></td>
                    <td>
                        <select name="role">
                            <option>admin</option>
                            <option>manager</option>
                        </select>
                    </td>
                </tr>
            </c:if>

            <c:if test="${requestScope.errors!=null}">
                <tr>
                    <td><label>name</label></td>
                    <td><input type="text" name ="name" value="${requestScope.previousUserName}"></td>
                </tr>
                <tr>
                    <td><label>surname</label></td>
                    <td><input type="text" name ="surname" value="${requestScope.previousUserSurname}"></td>
                </tr>
                <tr>
                    <td><label>email</label></td>
                    <td><input type="email" name ="email" value="${requestScope.previousUserEmail}"></td>
                </tr>
                <tr>
                    <td><label>password</label></td>
                    <td><input type="password" name ="password" value="${requestScope.previousUserPassword}"></td>
                </tr>
                <tr>
                    <td><label>birthDate</label></td>
                    <td><input type="text" name ="birthDate" value="${requestScope.previousUserDate}"></td>
                </tr>
                <tr>
                    <td><label>privileges</label></td>
                    <td>
                        <select name="role">
                            <c:if test="${previousUserRole.toString() eq 'admin'}">
                                <option selected>admin</option>
                                <option>manager</option>
                            </c:if>
                            <c:if test="${previousUserRole.toString() eq 'manager'}">
                                <option selected>admin</option>
                                <option>manager</option>
                            </c:if>
                        </select>
                    </td>
                </tr>
            </c:if>
        </table>
        <button>Create</button>
    </form>
</div>
</body>
</html>