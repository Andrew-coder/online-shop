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

    <jsp:include page="/WEB-INF/pages/menu.jsp"/>

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

      </div>
      <div class="cleaner"></div>
    </div>

    <jsp:include page="/WEB-INF/pages/footer.jsp"/>

  </div>
</div>

</body>
</html>
