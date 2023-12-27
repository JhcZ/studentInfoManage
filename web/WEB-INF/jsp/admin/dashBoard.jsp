<%--
  Created by IntelliJ IDEA.
  User: mila
  Date: 2023/12/21
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="http://${header.host}${pageContext.request.contextPath}/admin/">
    <meta charset="utf-8">
    <title>主页</title>
</head>
<body>
<span>dashboard</span><br/>
<a href="course/add.do">添加课程</a><br/>
<a href="course/list.do">课程列表</a><br/>
<a href="courseCache/query">课程修改审批</a><br/>
<a href="student/list">学生列表</a><br/>
<a href="teacher/list">教师列表</a>
</body>
</html>
