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
<jsp:include page="../localeSelector.jsp"/>
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

    <div>
        <form method="post" action="/admin/users/update" class="input">
            <table>
                <tr>
                    <td><input type="hidden" name="id" value="${user.id}"></td>
                </tr>
                <tr>
                    <td><label>name</label></td>
                    <td><input type="text" name ="name" style="width: 150px;" value="${user.name}"></td>
                </tr>
                <tr>
                    <td><label>surname</label></td>
                    <td><input type="text" name ="surname" style="width: 150px;" value="${user.surname}"></td>
                </tr>
                <tr>
                    <td><label>email</label></td>
                    <td><input type="email" name ="email" style="width: 150px;" value="${user.email}"></td>
                </tr>
                <tr>
                    <td><label>password</label></td>
                    <td><input type="password" name ="password" style="width: 150px;" value="${user.password}"></td>
                </tr>
                <tr>
                    <td><label>birthDate</label></td>
                    <td><input type="text" name ="birthDate" style="width: 150px;" value="${user.birthDate.toString()}"></td>
                </tr>
                <tr>
                    <td>
                        <c:if test="${user.role.getRoleName() ne 'user'}">
                            <select name="role">
                                <c:if test="${user.role.getRoleName() eq 'admin'}">
                                    <option selected>admin</option>
                                    <option>manager</option>
                                </c:if>
                                <c:if test="${user.role.getRoleName() eq 'manager'}">
                                    <option>admin</option>
                                    <option selected>manager</option>
                                </c:if>
                            </select>
                        </c:if>
                        <c:if test="${user.role.getRoleName() eq 'user'}">
                            <input name="role" value="user" readonly>
                        </c:if>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Update">
        </form>
    </div>
</body>
</html>
