<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/12/26 0026
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>查分</title>

    <%@ include file="/WEB-INF/jsp/student/css_template.jsp"%>
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
            <a class="navbar-brand" href="${pageContext.request.contextPath}/student/info">学生管理系统</a>
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
            <h1 class="page-header">成绩查询</h1>
            <!-- 成绩查询表单 -->
            <div class="table-responsive">
                <form class="score-form" action="queryScore" method="post" onsubmit="showPopup()">
                    <div class="form-group">
                        <label for="studentId">学号：</label>
                        <input type="text" class="form-control" id="studentId" name="studentId" required>
                    </div>
                    <div class="form-group">
                        <label for="courseId">课程号：</label>
                        <input type="text" class="form-control" id="courseId" name="courseId" required>
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="showPopup()">查询</button>
                </form>

                <!-- 弹出界面 -->
                <div id="resultPopup" class="popup">
                    <h3>查询结果</h3>
                    <p>学生姓名：${stuName}</p>
                    <p>课程名称：${cName}</p>
                    <p>教师姓名：${tName}</p>
                    <p>分数：${score}</p>
                    <button onclick="closePopup()" class="btn btn-secondary">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script>
    function showPopup() {
        document.getElementById('resultPopup').style.display = 'block';
    }

    function closePopup() {
        document.getElementById('resultPopup').style.display = 'none';
    }
</script>
</html>
