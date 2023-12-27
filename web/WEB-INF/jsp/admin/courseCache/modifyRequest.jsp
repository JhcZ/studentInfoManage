<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mila
  Date: 2023/12/27
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程修改审批</title>
    <base href="http://${header.host}${pageContext.request.contextPath}/admin/">
    <meta charset="utf-8">
    <style>
        #courseList {
            width: 100%;
            border-collapse: collapse;
        }

        #courseList th, #courseList td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>
<table id="courseList">
    <thead>
    <tr>
        <th>课程号</th>
        <th>课程名称</th>
        <th>授课教师</th>
        <th>上课地点</th>
        <th>上课周数</th>
        <th>上课时间(周几)</th>
        <th>上课时间(哪几节)</th>
        <th>课程开始时间</th>
        <th>课程开设学期</th>
        <th>选课人数</th>
        <th>是否通过</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cache" items="${caches}">
        <tr>
            <td>${cache.course.courseId}</td>
            <td>${cache.course.name}</td>
            <td>${cache.course.teacherId}</td>
            <td>${cache.course.location}</td>
            <td>${cache.course.courseDuration}</td>
            <td>${cache.course.classDay}</td>
            <td>${cache.course.classTime}</td>
            <td>${cache.course.startTime}</td>
            <td>${cache.course.semester}</td>
            <td>${cache.course.numOfStu}</td>
            <td>
                <c:if test="${cache.approval == 1}">
                    通过
                </c:if>
                <c:if test="${cache.approval == 0}">
                    未审批
                </c:if>
                <c:if test="${cache.approval == -1}">
                    未通过
                </c:if>
            </td>
            <td>
                <button onclick="approveCourse(${cache.course.courseId})">通过</button>
                <button onclick="rejectCourse(${cache.course.courseId})">不通过</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
<script>
    function approveCourse(courseId) {
        // 发送 Ajax 请求来批准课程
        // 示例 URL: 'course/approve?id=' + courseId
        var xhr = new XMLHttpRequest();
        xhr.open("POST" , "courseCache/approve?id=" + courseId);
        xhr.send();
        window.location.reload();
    }

    function rejectCourse(courseId) {
        // 发送 Ajax 请求来拒绝课程
        // 示例 URL: 'course/reject?id=' + courseId
        var xhr = new XMLHttpRequest();
        xhr.open("POST" , "courseCache/unApproval?id=" + courseId);
        xhr.send();
        window.location.reload();
    }

</script>