<%--
  Created by IntelliJ IDEA.
  User: mila
  Date: 2023/12/18
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <base href="http://${header.host}${pageContext.request.contextPath}/admin/">
    <title>管理员登录</title>
</head>
<body>
<h1>管理员登录</h1>
<form action="login" method="post">
    用户名：<input type="text" name="name"/><br/>
    密码：<input typr="password" name="password"><br/>
    验证码：<input type="text" name="validCode">
    <img src="validCode" id="vCode" onclick="refreshCode()"><br/>
    <button>登录</button>
</form>
</body>
</html>
<script>
    function refreshCode() {
        document.getElementById("vCode").src = "validCode?r=" + Math.random();
    }
</script>
