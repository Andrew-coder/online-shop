<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<%@ page import="online.shop.utils.constants.Attributes"%>
<%@ page import="online.shop.controller.i18n.LocaleHolder" %>
<%@ page import="online.shop.utils.constants.PagesPaths" %>
<fmt:setLocale value="${sessionScope['locale']}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="${bundleFile}" var="msg"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Online Store - Products</title>
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
            //customtheme: ["#1c5a80", "#18374a"],
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
                <div class="sidebar_box"><span class="bottom"></span>
                    <h3><fmt:message key="shop.subcategory" bundle="${msg}"/> </h3>
                    <div class="content">
                        <ul class="sidebar_list">

                            <c:forEach items="${subcategories}" var="value">
                                <li><a href="/goods?subcategoryID=${value.id}">${value.title}</a></li>
                            </c:forEach>

                        </ul>
                    </div>
                </div>

            </div>
            <div id="content" class="float_r">


            </div>
            <div class="cleaner"></div>
        </div>

        <jsp:include page="/WEB-INF/pages/user/footer.jsp"/>

    </div>
</div>

</body>
</html>