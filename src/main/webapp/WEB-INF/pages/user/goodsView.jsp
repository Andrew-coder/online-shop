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
    <title>Shoes Store - Products</title>
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


            </div>
            <div id="content" class="float_r">
                <h1> <fmt:message key="shop.products" bundle="${msg}"/></h1>


                <c:forEach items="${requestScope.goods}" var="value" varStatus="loop">
                    <c:choose>
                        <c:when test="${ (loop.index+1)%3 eq 0}">
                            <div class="product_box no_margin_right">
                                <h3>${value.title}</h3>
                                <h4>${value.goodsStatus}</h4>
                                <a href="productdetail.html"><img src="${value.image}" alt="Shoes 1" /></a>
                                <p> ${value.description} </p>
                                <p class="product_price">${value.getRealPrice()}</p>
                                <a href="basket/add?goodsID=${value.id}" class="addtocart"></a>
                                <a href="/basket/goodsInfo?goodsID=${value.id}" class="detail"></a>
                            </div>
                            <div class="cleaner"></div>
                        </c:when>
                        <c:otherwise>
                            <div class="product_box">
                                <h3>${value.title}</h3>
                                <h4>${value.goodsStatus}</h4>
                                <a href="productdetail.html"><img src="/images/no_photo.jpg" alt="Shoes 1" /></a>
                                <p> ${value.description} </p>
                                <p class="product_price">${value.getRealPrice()}</p>
                                <a href="/basket/add?goodsID=${value.id}" class="addtocart"></a>
                                <a href="/goodsInfo?goodsID=${value.id}" class="detail"></a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>


            </div>
            <div class="cleaner"></div>
        </div>

        <jsp:include page="/WEB-INF/pages/user/footer.jsp"/>

    </div>
</div>

</body>
</html>