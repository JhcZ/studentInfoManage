<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/12/21 0021
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <%--<div>
        <span><a href="book/list">首页</a></span>
        <span><a href="register.do">新顾客注册</a></span>
        <span><a href="../admin/login.do">管理员登录</a></span>
    </div>--%>
</div>

<script>
    function refreshCode() {
        document.getElementById("vCode").src = "validCode?r=" + Math.random();
    }
</script>
</body>
</html>
