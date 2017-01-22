<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="/css/style.css" />
    </head>
    <body>
        <form id="slick-login" method="post" action="/login">
            <label for="username">login:</label><input type="text" name="login" class="placeholder" placeholder="email">
            <label for="password">password:</label><input type="password" name="password" class="placeholder" placeholder="password">
            <input type="submit" value="Log In">
        </form>
    </body>
</html>