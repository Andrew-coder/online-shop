<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="online.shop.utils.constants.PagesPaths" %>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="/css/style.css" />
    </head>
    <body>

    <div class="top-bar">
        <a href="/">Home</a>
    </div>

        <div class="login-page">
            <div class="form">
                <form class="login-form" method="post" action="/login">
                    <input type="text" name ="login" placeholder="email"/>
                    <input type="password" name="password" placeholder="password"/>
                    <button>login</button>
                    <p class="message">Not registered? <a href="/register">Create an account</a></p>
                </form>
            </div>
        </div>
    </body>
</html>