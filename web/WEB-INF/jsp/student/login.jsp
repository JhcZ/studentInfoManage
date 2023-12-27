<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/12/21 0021
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <base href="http://${header.host}${pageContext.request.contextPath}/student/">
    <title>Student Login</title>
</head>
<body>
<div class="studentLogin">
    <h1>学生登录</h1>
    <form action="login" method="post">
        <label for="studentId">用户名：</label>
        <input type="text" id="studentId" name="studentId"><br>
        <label for="password">密码：</label>
        <input type="password" id="password" name="password"><br>
        验证码：<input type="text" name="inputCode">
        <img src="validCode" id="vCode" onclick="refreshCode()"><br>
        <button type="submit">登 录</button>
    </form>
    &lt;%&ndash;<div>
        <span><a href="book/list">首页</a></span>
        <span><a href="register.do">新顾客注册</a></span>
        <span><a href="../admin/login.do">管理员登录</a></span>
    </div>&ndash;%&gt;
</div>

<script>
    function refreshCode() {
        document.getElementById("vCode").src = "validCode?r=" + Math.random();
    }
</script>
</body>
</html>--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    %>
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap.css">
    <style>
        body {
            background-image: url("<%=basePath%>/img/bg.jpg");
            color: white;
        }
        .loginBox {
            width: 400px;
            height: 300px;
            position: absolute;
            top: 50%;
            left: 50%;
            margin-left: -200px;
            margin-top: -150px;
        }
        #pwdBox {
            margin-top: 25px;
        }
        .loginBtn{
            height: 40px;
            width: 200px;
            text-align: center;
            margin: auto;
        }
        .loginLabel{
            text-align: center;
            font-size: 2em;
        }
    </style>
</head>

<body>
<div class="container">
    <div class="loginBox">
        <form class="form-horizontal" action="login" method="post">
            <div class="loginLabel">
                <label>
                    学生管理系统
                </label>
            </div>
            <div class="form-group" style="margin-top: 20px;">
                <label for="studentId" class="col-sm-2 control-label">账号</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="studentId" id="studentId" placeholder="studentId">
                </div>
            </div>
            <div class="form-group" id="pwdBox">
                <label for="password" class="col-sm-2 control-label">密码</label>
                <div class="col-sm-8">
                    <input type="password" class="form-control" name="password" id="password" placeholder="password">
                </div>
            </div>
            <%--<div class="form-group" style="display: flex; align-items: center;">
                <label for="inputCode" class="col-sm-2 control-label">验证码</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="inputCode" id="inputCode" placeholder="inputCode">
                    <img src="validCode" id="vCode" onclick="refreshCode()"><br>
                </div>
            </div>--%>
            <div class="loginBtn">
                <button type="submit" class=" btn btn-info loginBtn">登录</button>
            </div>
        </form>
    </div>
</div>

<script>
    function refreshCode() {
        document.getElementById("vCode").src = "validCode?r=" + Math.random();
    }
</script>
</body>
</html>