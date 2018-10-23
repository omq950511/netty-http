<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>登录</title>
        <link rel="stylesheet" type="text/css" href="css/index.css">
    </head>
    <body>
        <div id="content">
            <img id="content-img" src="img/form_logo.png"/>
            <div id="contnt-text">登录</div>
            <div id="content-form">
                <form action="doLogin" method="post">
                    <div id="content-uid">
                        <input class="uid" type="text" name="username" placeholder="请输入用户名"/>
                    </div>
                    <div id="content-pwd">
                        <input class="pwd" type="password" name="password" placeholder="请输入密码"/>
                    </div>
                    <div id="content-signup">
                        <input class="signup" type="submit" value="登录"/>
                    </div>
                </form>
            </div>
            <div id="content-login-description">没有账户？</div>
            <div><a id="content-login-link" href="">注册</a></div>
        </div>
    </body>
    <script type="text/javascript" src="script/jquery/jquery-3.3.1.min.js"></script>
</html>
