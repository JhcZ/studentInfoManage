<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/12/28 0028
  Time: 8:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改密码</title>
    <jsp:include page="../css_template.jsp"/>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#navbar" aria-expanded="false"
                    aria-controls="navbar">
                <span class="sr-only">学生管理系统</span> <span class="icon-bar"></span>
                <span class="icon-bar"></span> <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">学生管理系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <!-- 导航条菜单 -->
            <jsp:include page="../header_nav_template.jsp"/>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <!-- 侧边栏 -->
            <jsp:include page="../side_nav_template.jsp">
                <jsp:param value="active" name="2" />
            </jsp:include>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">修改密码</h1>
            <form action="${pageContext.request.contextPath}/modPwd" method="post" id="modPwdForm">
                学&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp号：<input type="text" name="studentId" id="studentId" placeholder="学号" required><br>
                修改密码：<input type="password" name="newPwd" id="newPwd" placeholder="新的密码" required><br>
                <button type="submit" class="bt btn-primary">提交</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
