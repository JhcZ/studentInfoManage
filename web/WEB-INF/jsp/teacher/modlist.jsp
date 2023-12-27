<%@ page import="model.CourseApprovalUpdate" %><%--
  Created by IntelliJ IDEA.
  User: LGelo
  Date: 2023/12/25
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程列表</title>
    <script src="js/jquery.min.js"></script>
</head>
<body>
<h1>课程列表</h1>

<table id="courseTable">
    <tr>
        <th>课程ID</th>
        <th>课程名称</th>
        <th>地点</th>
        <th>课程时长</th>

        <th>上课日期</th>
        <th>上课时间</th>
        <th>开始时间</th>
        <th>学期</th>
        <th>审批状态</th>
    </tr>
</table>

<script>
    // 使用AJAX请求获取课程数据
    $.ajax({
        url: "/studentInfo/teacher/modlist",
        type: "GET",
        dataType: "json",
        success: function(data) {
            // 处理返回的课程数据
            for (let i = 0; i < data.length; i++) {
                const course1 = data[i];
                console.log(course1);
                const tr = $("<tr>");
                tr.append("<td>" + course1.courseId + "</td>");
                tr.append("<td>" + course1.name + "</td>");
                tr.append("<td>" + course1.location + "</td>");
                tr.append("<td>" + course1.courseDuration + "</td>");
                tr.append("<td>" + course1.classDay + "</td>");
                tr.append("<td>" + course1.classTime + "</td>");
                tr.append("<td>" + course1.startTime + "</td>");
                tr.append("<td>" + course1.semester + "</td>");
                var approvalStatus = "";
                if (course1.approval === 1) {
                    approvalStatus = "通过";
                } else if (course1.approval === -1) {
                    approvalStatus = "不通过";
                } else {
                    approvalStatus = "未审批";
                }
                tr.append("<td>" + approvalStatus + "</td>");
                $("#courseTable").append(tr);
            }
        },
        error: function() {
            alert("Failed to fetch course data.");
        }
    });
</script>
</body>
</html>

