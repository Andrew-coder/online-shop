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
            contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
        })

    </script>

</head>

<body>

<div id="templatemo_body_wrapper">
    <div id="templatemo_wrapper">

        <jsp:include page="/WEB-INF/pages/user/header.jsp" />

        <jsp:include page="/WEB-INF/pages/user/menu.jsp"/>

        <div id="templatemo_main">
            <div id="sidebar" class="float_l">
                <c:if test="${requestScope.errors!=null}">
                    <h1>You input wrong data</h1>
                </c:if>

            </div>
            <div id="content" class="float_r">
                <h1>Shopping Cart</h1>
                <table width="680px" cellspacing="0" cellpadding="5">
                    <tr bgcolor="#ddd">
                        <th width="220" align="left">Image </th>
                        <th width="180" align="left">Description </th>
                        <th width="100" align="center">Quantity </th>
                        <th width="60" align="right">Price </th>
                        <th width="60" align="right">Total </th>
                        <th width="90"> </th>

                    </tr>
                    <c:set var="total" value="${0}"/>
                    <form action="/update" method="post">
                        <tr>
                            <c:if test="${sessionScope.goods==null}">
                                <td colspan="6" align="center"><h1>Basket is empty</h1></td>
                            </c:if>
                            <c:forEach items="${sessionScope.goods}" var="entry">
                                <td><img src="/images/no_photo.jpg" alt="image 1" /></td>
                                <td>${entry.key.title}</td>
                                <td align="center"><input id="amount${entry.key.id}" name="amount${entry.key.id}" type="text" value="${entry.value}" style="width: 20px; text-align: right" /> </td>
                                <td align="right">${entry.key.price} </td>
                                <td align="right">${entry.value*entry.key.price}</td>
                                <td align="center"> <a href="/basket/remove?goodsID=${entry.key.id}"><img src="/images/remove_x.gif" alt="remove" /><br />Remove</a> </td>
                                <c:set var="total" value="${total + entry.key.price*entry.value}" />
                            </c:forEach>
                        </tr>
                        <tr>
                            <td colspan="3" align="right"  height="30px">Have you modified your basket? Please click here to <strong><input type="submit" value="Update"/></strong></td>
                            <td align="right" style="background:#ddd; font-weight:bold"> Total </td>
                            <td align="right" style="background:#ddd; font-weight:bold">${total} </td>
                            <td style="background:#ddd; font-weight:bold"> </td>
                        </tr>
                    </form>
                </table>
                <div style="float:right; width: 215px; margin-top: 20px;">

                    <p><a href="/purchase">Proceed to checkout</a></p>
                    <p><a href="/">Continue shopping</a></p>

                </div>
            </div>
            <div class="cleaner"></div>
        </div>

        <jsp:include page="/WEB-INF/pages/user/footer.jsp"/>

    </div>
</div>

</body>
</html>
