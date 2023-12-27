<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/12/26 0026
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>档案查询</title>
    <jsp:include page="../css_template.jsp"/>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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
            <a class="navbar-brand" href="${pageContext.request.contextPath}/mainUrl">学生管理系统</a>
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
                <jsp:param value="active" name="3" />
            </jsp:include>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">欢迎您, ${student.getName() }！</h1>
            <form id="studentInfoForm">
                <div>
                    <div class="panel panel-default">
                        <ul class="list-group">
                            <li class="list-group-item"><span
                                    class="label label-primary">学&#12288号</span>
                                ${student.getStudentId() }</li>
                            <li class="list-group-item"><span
                                    class="label label-primary">姓&#12288名</span>
                                ${student.getName() }
                            </li>
                            <li class="list-group-item"><span
                                    class="label label-primary">专&#12288业</span>
                                ${student.getMajor() }
                            </li>
                            <li class="list-group-item"><span
                                    class="label label-primary">学&#12288院</span>
                                ${student.getDepartment() }
                            </li>
                            <li class="list-group-item"><span
                                    class="label label-primary">性&#12288别</span>
                                ${student.getSex()}
                            </li>
                            <li class="list-group-item"><span
                                    class="label label-primary">班&#12288级</span>
                                ${student.getClassName()}
                            </li>
                            <li class="list-group-item"><span
                                    class="label label-primary">手机号</span>
                                ${student.getPhone()}
                            </li>
                            <li class="list-group-item"><span
                                    class="label label-primary">状&#12288态</span>
                                ${student.getStatus().getName() }
                            </li>
                        </ul>
                    </div>
                </div>
            </form>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#updateModal" onclick="redirectToAnotherPage()">
                更新
            </button>
        </div>
    </div>
</div>

<script>
    function redirectToAnotherPage(){
        window.location.href = "${pageContext.request.contextPath}/student/updateInfo.do";
    }

    function closeModal() {
        document.getElementById('updateModal').style.display = 'none';
    }
</script>
</body>
</html>

