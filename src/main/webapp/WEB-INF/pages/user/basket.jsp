<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<%@ page import="online.shop.utils.constants.Attributes"%>
<%@ page import="online.shop.controller.i18n.LocaleHolder" %>
<%@ page import="online.shop.utils.constants.PagesPaths" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Online-shop</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link href="/css/templatemo_style.css" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" type="text/css" href="/css/ddsmoothmenu.css" />

    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/ddsmoothmenu.js">
    </script>

    <script type="text/javascript">
        ddsmoothmenu.init({
            mainmenuid: "top_nav", //menu DIV id
            orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
            classname: 'ddsmoothmenu', //class added to menu's outer DIV
            contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
        })
    </script>

</head>

<body>
<fmt:setLocale value="${sessionScope['locale']}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="${bundleFile}" var="msg"/>
<div id="templatemo_body_wrapper">
    <jsp:include page="../localeSelector.jsp"/>
    <div id="templatemo_wrapper">

        <jsp:include page="/WEB-INF/pages/user/header.jsp" />

        <jsp:include page="/WEB-INF/pages/user/menu.jsp"/>

        <div id="templatemo_main">
            <div id="sidebar" class="float_l">


            </div>
            <div id="content" class="float_r">
                <h1><fmt:message key="shop.shopping.card" bundle="${msg}"/></h1>
                <table width="680px" cellspacing="0" cellpadding="5">
                    <tr bgcolor="#ddd">
                        <th width="220" align="left"><fmt:message key="basket.image" bundle="${msg}"/> </th>
                        <th width="180" align="left"><fmt:message key="basket.description" bundle="${msg}"/> </th>
                        <th width="100" align="center"><fmt:message key="basket.quantity" bundle="${msg}"/> </th>
                        <th width="60" align="right"><fmt:message key="basket.price" bundle="${msg}"/> </th>
                        <th width="60" align="right"><fmt:message key="basket.total" bundle="${msg}"/> </th>
                        <th width="90"> </th>

                    </tr>

                    <form action="/update" method="post">

                            <c:if test="${sessionScope.basket==null || sessionScope.basket.isEmpty()}">
                                <tr><td colspan="6" align="center"><h1><fmt:message key="basket.empty" bundle="${msg}"/></h1></td></tr>
                            </c:if>
                            <c:forEach items="${basket.getGoodsItems()}" var="entry">
                                <tr>
                                    <td><img src="${entry.key.image}" alt="image 1" /></td>
                                    <td>${entry.key.title}</td>
                                    <td align="center"><input id="amount${entry.key.id}" name="amount${entry.key.id}" type="text" value="${entry.value}" style="width: 20px; text-align: right" /> </td>
                                    <td align="right">${entry.key.getRealPrice()} </td>
                                    <td align="right">${entry.value*entry.key.getRealPrice()}</td>
                                    <td align="center"> <a href="/basket/remove?goodsID=${entry.key.id}"><img src="/images/remove_x.gif" alt="remove" /><br /><fmt:message key="basket.remove" bundle="${msg}"/></a> </td>
                                </tr>
                            </c:forEach>
                        <tr>
                            <td colspan="3" align="right"  height="30px"><fmt:message key="basket.have.modified" bundle="${msg}"/> <strong><input type="submit" value="<fmt:message key="basket.update" bundle="${msg}"/>"/></strong></td>
                            <td align="right" style="background:#ddd; font-weight:bold"> <fmt:message key="basket.total" bundle="${msg}"/> </td>
                            <td align="right" style="background:#ddd; font-weight:bold">${basket.getTotalPrice()} </td>
                            <td style="background:#ddd; font-weight:bold"> </td>
                        </tr>
                    </form>
                </table>
                <div style="float:right; width: 215px; margin-top: 20px;">

                    <p><a href="/purchase"><fmt:message key="basket.proceed.checkout" bundle="${msg}"/></a></p>
                    <p><a href="/"><fmt:message key="basket.continue.shopping" bundle="${msg}"/></a></p>

                </div>
            </div>
            <div class="cleaner"></div>
        </div>

        <jsp:include page="/WEB-INF/pages/user/footer.jsp"/>

    </div>
</div>

</body>
</html>