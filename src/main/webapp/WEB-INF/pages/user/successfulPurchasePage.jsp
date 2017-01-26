<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Online shop</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link href="/css/templatemo_style.css" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" href="/css/nivo-slider.css" type="text/css" media="screen" />

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
                <h1>Congratulations with the success purchase!</h1>
            </div>

        </div>
    </div>
</body>
</html>
