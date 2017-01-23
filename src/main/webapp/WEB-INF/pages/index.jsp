<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="online.shop.utils.constants.PagesPaths" %>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>Online shop</title>
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <link href="/css/templatemo_style.css" rel="stylesheet" type="text/css" />

  <link rel="stylesheet" href="/css/nivo-slider.css" type="text/css" media="screen" />

  <link rel="stylesheet" type="text/css" href="/css/ddsmoothmenu.css" />

  <script type="text/javascript" src="../js/jquery.min.js"></script>
  <script type="text/javascript" src="../js/ddsmoothmenu.js">

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

    <jsp:include page="/WEB-INF/pages/header.jsp" />

    <div id="templatemo_menubar">
      <div id="top_nav" class="ddsmoothmenu">
        <ul>
          <li><a href="${PagesPaths.ROOT}" class="selected">Home</a></li>
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
          <h3>Categories</h3>
          <div class="content">
            <ul class="sidebar_list">

              <c:forEach items="${categories}" var="value">
                <li><a href="/subcategory?categoryID=${value.id}">${value.title}</a></li>
              </c:forEach>

            </ul>
          </div>
        </div>

      </div>
      <div id="content" class="float_r">
        <div id="slider-wrapper">
          <div id="slider" class="nivoSlider">
            <img src="/images/slider/02.jpg" alt="" />
            <a href="#"><img src="/images/slider/01.jpg" alt="" title="This is an example of a caption" /></a>
            <img src="/images/slider/03.jpg" alt="" />
            <img src="/images/slider/04.jpg" alt="" title="#htmlcaption" />
          </div>
          <div id="htmlcaption" class="nivo-html-caption">
            <strong>This</strong> is an example of a <em>HTML</em> caption with <a href="#">a link</a>.
          </div>
        </div>
        <script type="text/javascript" src="/js/jquery-1.4.3.min.js"></script>
        <script type="text/javascript" src="/js/jquery.nivo.slider.pack.js"></script>
        <script type="text/javascript">
          $(window).load(function() {
            $('#slider').nivoSlider();
          });
        </script>
        <h1>New Products</h1>
        <div class="product_box">
          <h3>Ut eu feugiat</h3>
          <a href="productdetail.html"><img src="/images/product/01.jpg" alt="Shoes 1" /></a>
          <p>Nulla rutrum neque vitae erat condimentum eget malesuada.</p>
          <p class="product_price">$ 100</p>
          <a href="shoppingcart.html" class="addtocart"></a>
          <a href="productdetail.html" class="detail"></a>
        </div>
        <div class="product_box">
          <h3>Curabitur et turpis</h3>
          <a href="productdetail.html"><img src="/images/product/02.jpg" alt="Shoes 2" /></a>
          <p>Sed congue, erat id congue vehicula. Validate <a href="http://validator.w3.org/check?uri=referer" rel="nofollow">XHTML</a> &amp; <a href="http://jigsaw.w3.org/css-validator/check/referer" rel="nofollow">CSS</a>.</p>
          <p class="product_price">$ 80</p>
          <a href="shoppingcart.html" class="addtocart"></a>
          <a href="productdetail.html" class="detail"></a>
        </div>
        <div class="product_box no_margin_right">
          <h3>Mauris consectetur</h3>
          <a href="productdetail.html"><img src="/images/product/03.jpg" alt="Shoes 3" /></a>
          <p>Morbi non risus vitae est vestibulum tincidunt ac eget metus.</p>
          <p class="product_price">$ 60</p>
          <a href="shoppingcart.html" class="addtocart"></a>
          <a href="productdetail.html" class="detail"></a>
        </div>

        <div class="cleaner"></div>

        <div class="product_box">
          <h3>Proin volutpat</h3>
          <a href="productdetail.html"><img src="/images/product/04.jpg" alt="Shoes 4" /></a>
          <p>Sed semper euismod dolor sit amet interdum. Phasellus in mi eros.</p>
          <p class="product_price">$ 220</p>
          <a href="shoppingcart.html" class="addtocart"></a>
          <a href="productdetail.html" class="detail"></a>
        </div>
        <div class="product_box">
          <h3>Aenean tempus</h3>
          <a href="productdetail.html"><img src="/images/product/05.jpg" alt="Shoes 5" /></a>
          <p>Maecenas porttitor erat quis leo pellentesque molestie.</p>
          <p class="product_price">$ 180</p>
          <a href="shoppingcart.html" class="addtocart"></a>
          <a href="productdetail.html" class="detail"></a>
        </div>
        <div class="product_box no_margin_right">
          <h3>Nulla luctus urna</h3>
          <a href="productdetail.html"><img src="/images/product/06.jpg" alt="Shoes 6" /></a>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
          <p class="product_price">$ 160</p>
          <a href="shoppingcart.html" class="addtocart"></a>
          <a href="productdetail.html" class="detail"></a>
        </div>
      </div>
      <div class="cleaner"></div>
    </div>

    <div id="templatemo_footer">
      <p><a href="#">Home</a> | <a href="#">Products</a> | <a href="#">About</a> | <a href="#">FAQs</a> | <a href="#">Checkout</a> | <a href="#">Contact Us</a>
      </p>

      Copyright © 2017 <a href="#">Andrew Ivanyuk</a> </div>

  </div>
</div>

</body>
</html>
