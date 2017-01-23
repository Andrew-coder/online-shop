<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <li><a href="products.html">Products</a>
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
            </div> <!-- end of ddsmoothmenu -->
            <div id="templatemo_search">
                <form action="#" method="get">
                    <input type="text" value=" " name="keyword" id="keyword" title="keyword" onfocus="clearText(this)" onblur="clearText(this)" class="txt_field" />
                    <input type="submit" name="Search" value=" " alt="Search" id="searchbutton" title="Search" class="sub_btn"  />
                </form>
            </div>
        </div> <!-- END of templatemo_menubar -->

        <div id="templatemo_main">
            <div id="sidebar" class="float_l">
                <div class="sidebar_box"><span class="bottom"></span>
                    <h3>Categories</h3>
                    <div class="content">
                        <ul class="sidebar_list">
                            <li class="first"><a href="#">Sed eget purus</a></li>
                            <li><a href="#">Vestibulum eleifend</a></li>
                            <li><a href="#">Nulla odio ipsum</a></li>
                            <li><a href="#">Suspendisse posuere</a></li>
                            <li><a href="#">Nunc a dui sed</a></li>
                            <li><a href="#">Curabitur ac mauris</a></li>
                            <li><a href="#">Mauris nulla tortor</a></li>
                            <li><a href="#">Nullam ultrices</a></li>
                            <li><a href="#">Nulla odio ipsum</a></li>
                            <li><a href="#">Suspendisse posuere</a></li>
                            <li><a href="#">Nunc a dui sed</a></li>
                            <li><a href="#">Curabitur ac mauris</a></li>
                            <li><a href="#">Mauris nulla tortor</a></li>
                            <li><a href="#">Nullam ultrices</a></li>
                            <li class="last"><a href="#">Sed eget purus</a></li>
                        </ul>
                    </div>
                </div>

            </div>
            <div id="content" class="float_r">
                <h1>Shopping Cart</h1>
                <table width="680px" cellspacing="0" cellpadding="5">
                    <tr bgcolor="#ddd">
                        <th width="220" align="left">Image </th>
                        <th width="180" align="left">Description </th>
                        <%--<th width="100" align="center">Quantity </th>--%>
                        <th width="60" align="right">Price </th>
                        <%--<th width="60" align="right">Total </th>--%>
                        <th width="90"> </th>

                    </tr>

                    <c:set var="total" value="${0}"/>
                    <c:forEach items="${sessionScope.goods}" var="value">
                        <tr>
                            <td><img src="/images/no_photo.jpg" alt="image 1" /></td>
                            <td>${value.title}</td>
                            <%--<td align="center"><input id="amount" name="amount" type="text" value="${goods.get(value)}" style="width: 20px; text-align: right" /> </td>--%>
                            <td align="right">${value.price} </td>
                            <%--<td align="right">${goods.get(value)*value.price}</td>--%>
                            <td align="center"> <a href="/basket/remove?goodsID=${value.id}"><img src="/images/remove_x.gif" alt="remove" /><br />Remove</a> </td>
                        </tr>
                        <c:set var="total" value="${total + value.price}" />
                    </c:forEach>

                    <tr>
                        <td colspan="3" align="right"  height="30px">Have you modified your basket? Please click here to <a href="shoppingcart.html"><strong>Update</strong></a>&nbsp;&nbsp;</td>
                        <td align="right" style="background:#ddd; font-weight:bold"> Total </td>
                        <td align="right" style="background:#ddd; font-weight:bold">${total} </td>
                        <td style="background:#ddd; font-weight:bold"> </td>
                    </tr>
                </table>
                <div style="float:right; width: 215px; margin-top: 20px;">

                    <p><a href="${PagesPaths.ParentRoot}purchase">Proceed to checkout</a></p>
                    <p><a href="${PagesPaths.HOME_PATH}">Continue shopping</a></p>

                </div>
            </div>
            <div class="cleaner"></div>
        </div>

        <div id="templatemo_footer">
            <p><a href="${PagesPaths.HOME_PATH}">Home</a> | <a href="#">Products</a> | <a href="#">About</a> | <a href="#">FAQs</a> | <a href="#">Checkout</a> | <a href="#">Contact Us</a>
            </p>

            Copyright © 2017 <a href="#">Andrew Ivanyuk</a>

        </div>

    </div>
</div>

</body>
</html>
