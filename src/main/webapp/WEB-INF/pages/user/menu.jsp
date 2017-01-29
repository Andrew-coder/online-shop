<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmr" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="online.shop.utils.constants.Attributes"%>
<%@ page  import="online.shop.controller.i18n.LocaleHolder" %>
<%@ page import="online.shop.utils.constants.PagesPaths" %>

<fmt:setLocale value="${sessionScope['locale']}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="${bundleFile}" var="msg"/>

<div id="templatemo_menubar">
    <div id="top_nav" class="ddsmoothmenu">
        <ul>
            <li><a href="/" class="selected"><fmt:message key="shop.home" bundle="${msg}"/></a></li>
            <li><a href="products.html"><fmt:message key="shop.products" bundle="${msg}"/></a>
                <ul>

                </ul>
            </li>
            <li><a href="about.html"><fmt:message key="shop.about" bundle="${msg}"/></a>
                <ul>

                </ul>
            </li>
            <li><a href="faqs.html"><fmt:message key="shop.faq" bundle="${msg}"/></a></li>
            <li><a href="/purchase"><fmt:message key="shop.checkout" bundle="${msg}"/></a></li>
            <li><a href="contact.html"><fmt:message key="shop.contact" bundle="${msg}"/></a></li>
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