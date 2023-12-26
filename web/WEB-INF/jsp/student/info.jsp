<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/12/21 0022
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>主页</title>
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
			<a class="navbar-brand" href="${pageContext.request.contextPath}/info">学生管理系统</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<!-- 导航条菜单 -->
			<%@ include file="/WEB-INF/jsp/student/header_nav_template.jsp"%>
		</div>
	</div>
</nav>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-3 col-md-2 sidebar">
			<!-- 侧边栏 -->
			<jsp:include page="/WEB-INF/jsp/student/side_nav_template.jsp">
				<jsp:param value="active" name="1" />
			</jsp:include>
		</div>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header">欢迎您, ${student.getName() }！</h1>

			<div class="row placeholders">
				<div class="col-xs-6 col-sm-3 placeholder">
					<div class="imgBox">
						<img src="<%=basePath%>/img/nav1.jpg">
						<h1 class="imgText">${student.getStudentId() }</h1>
					</div>
					<h4>学号</h4>
					<span class="text-muted">Student</span>
				</div>

				<div class="col-xs-6 col-sm-3 placeholder">
					<div class="imgBox">
						<img src="<%=basePath%>/img/nav2.jpg"/>
						<h1 class="imgText">${student.getMajor() }</h1>
					</div>
					<h4>专业</h4>
					<span class="text-muted">Major</span>
				</div>

				<div class="col-xs-6 col-sm-3 placeholder">
					<div class="imgBox">
						<img src="<%=basePath%>/img/nav3.jpg"/>
						<h1 class="imgText">${student.getClassName() }</h1>
					</div>
					<h4>班级</h4>
					<span class="text-muted">Class</span>
				</div>

				<div class="col-xs-6 col-sm-3 placeholder">
					<div class="imgBox">
						<img src="<%=basePath%>/img/nav4.jpg"/>
						<h1 class="imgText">${student.getStatus().getName() }</h1>
					</div>
					<h4>状态</h4>
					<span class="text-muted">Status</span>
				</div>

			</div>

			<h2 class="sub-header">课程信息</h2>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
					<tr>
						<th>课程号</th>
						<th>课程名称</th>
						<th>执教老师ID</th>
						<th>课程类别</th>
						<th>课程周数</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="course" items="${courseList }" varStatus="status">
						<tr>
							<td>${course.courseId }</td>
							<td>${course.name }</td>
							<td>${course.teacherId }</td>
							<td>${course.flag }</td>
							<td>${course.courseDuration }</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
</html>