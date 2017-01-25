<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Online shop - Check Out</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link href="/css/templatemo_style.css" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" type="text/css" href="/css/ddsmoothmenu.css" />

    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/ddsmoothmenu.js">

    </script>

    <script type="text/javascript">

        ddsmoothmenu.init({
            mainmenuid: "top_nav",
            orientation: 'h',
            classname: 'ddsmoothmenu',
            contentsource: "markup"
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


            </div>
                <c:if test="${requestScope.get(errors) != null}">
                    <table align="center">
                        <c:forEach items="${requestScope.errors.getErrorsAttributes()}" var="value">
                            <tr><td align="center">${requestScope.errors.getErrors().get(value)}</td></tr>
                        </c:forEach>
                    </table>
                </c:if>

                <div class="cleaner h50"></div>
                <h3>SHOPPING CART</h3>


                <c:set var="total" value="${0}"/>
                <c:forEach items="${sessionScope.goods}" var="entry">
                    <c:set var="total" value="${total + entry.key.price*entry.value}" />
                </c:forEach>


                <h4>TOTAL AMOUNT: <strong>${total}</strong></h4>
                <form action="/purchase" method="post">
                    <table style="border:1px solid #CCCCCC;" width="100%">
                        <tr>
                            <td><input type="radio" name="group" checked></td>
                            <td height="80px"> <img src="images/paypal.gif" alt="paypal" /></td>
                            <td width="400px;" style="padding: 0px 20px;">Recommended if you have a PayPal account. Fastest delivery time.
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><input type="radio" name="group"></td>
                            <td  height="80px"><img src="images/2co.gif" alt="paypal" />
                            </td>
                            <td  width="400px;" style="padding: 0px 20px;">2Checkout.com, Inc. is an authorized retailer of goods and services. 2CheckOut accepts customer orders via online checks, Visa, MasterCard, Discover, American Express, Diners, JCB and debit cards with the Visa, Mastercard logo. Validate <a href="http://validator.w3.org/check?uri=referer" rel="nofollow">XHTML</a> &amp; <a href="http://jigsaw.w3.org/css-validator/check/referer" rel="nofollow">CSS</a>.</td>
                            <td><a href="#" class="more">2CHECKOUT</a></td>

                        </tr>
                        <tr>
                            <td><input type="radio" name="group" value="paOnDelivery"></td>
                            <td colspan="3"><p>Pay on delivery</p></td>
                        </tr>
                    </table>
                    <input type="submit" value="Make purchase"/>
                </form>
            </div>
            <div class="cleaner"></div>
        </div>

        <jsp:include page="/WEB-INF/pages/user/footer.jsp"/>

    </div>
</div>

</body>
</html>