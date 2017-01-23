<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="online.shop.utils.constants.PagesPaths" %>
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

<div id="templatemo_body_wrapper">
    <div id="templatemo_wrapper">

        <jsp:include page="/WEB-INF/pages/header.jsp" />

        <div id="templatemo_menubar">
            <div id="top_nav" class="ddsmoothmenu">
                <ul>
                    <li><a href="/">Home</a></li>
                    <li><a href="products.html" class="selected">Products</a>
                        <ul>

                        </ul>
                    </li>
                    <li><a href="about.html">About</a>
                        <ul>

                        </ul>
                    </li>
                    <li><a href="faqs.html">FAQs</a></li>
                    <li><a href="checkout.html">Checkout</a></li>
                    <li><a href="contact.html">Contact Us</a></li>
                </ul>
                <br style="clear: left" />
            </div>
            <div id="templatemo_search">
                <form action="#" method="get">
                    <input type="text" value=" " name="keyword" id="keyword" title="keyword" onfocus="clearText(this)" onblur="clearText(this)" class="txt_field" />
                    <input type="submit" name="Search" value=" " alt="Search" id="searchbutton" title="Search" class="sub_btn"  />
                </form>
            </div>
        </div>

        <div id="templatemo_main">
            <div id="sidebar" class="float_l">
                <div class="sidebar_box"><span class="bottom"></span>
                    <h3>Subategories</h3>
                    <div class="content">
                        <ul class="sidebar_list">

                            <c:forEach items="${subcategories}" var="value">
                                <li><a href="/goods?subcategoryID=${value.id}">${value.title}</a></li>
                            </c:forEach>

                        </ul>
                    </div>
                </div>
                <div class="sidebar_box"><span class="bottom"></span>
                    <h3>Bestsellers </h3>
                    <div class="content">
                        <div class="bs_box">
                            <a href="#"><img src="/images/templatemo_image_01.jpg" alt="image" /></a>
                            <h4><a href="#">Donec nunc nisl</a></h4>
                            <p class="price">$10</p>
                            <div class="cleaner"></div>
                        </div>
                        <div class="bs_box">
                            <a href="#"><img src="/images/templatemo_image_01.jpg" alt="image" /></a>
                            <h4><a href="#">Lorem ipsum dolor sit</a></h4>
                            <p class="price">$12</p>
                            <div class="cleaner"></div>
                        </div>
                        <div class="bs_box">
                            <a href="#"><img src="/images/templatemo_image_01.jpg" alt="image" /></a>
                            <h4><a href="#">Phasellus ut dui</a></h4>
                            <p class="price">$20</p>
                            <div class="cleaner"></div>
                        </div>
                        <div class="bs_box">
                            <a href="#"><img src="/images/templatemo_image_01.jpg" alt="image" /></a>
                            <h4><a href="#">Vestibulum ante</a></h4>
                            <p class="price">$8</p>
                            <div class="cleaner"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="content" class="float_r">
                <h1> Products</h1>

            </div>
            <div class="cleaner"></div>
        </div>

        <div id="templatemo_footer">
            <p><a href="${PagesPaths.HOME_PATH}">Home</a> | <a href="#">Products</a> | <a href="#">About</a> | <a href="#">FAQs</a> | <a href="#">Checkout</a> | <a href="#">Contact Us</a>
            </p>


            Copyright © 2017 <a href="#">Andriy Ivanyuk</a>
        </div>

    </div>
</div>

</body>
</html>