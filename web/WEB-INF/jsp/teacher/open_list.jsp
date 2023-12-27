<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LGelo
  Date: 2023/12/27
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>开课申请列表</title>
    <script src = "js/jquery.min.js"></script>
    <style>
        .navbar {
            background-color: #f1f1f1;
            padding: 10px;
            display: flex;
            align-items: center;
        }

        .navbar-title {
            font-size: 20px;
            margin-right: auto;
        }

        .navbar-links {
            margin-right: 20px;
        }

        .navbar-links a {
            margin-left: 10px;
        }
    </style>
</head>
<body>
<div class="navbar">
    <div class="navbar-title">教师管理</div>
    <div class="navbar-links">
        <a href="list.do">主页</a>
        <a href="#" onclick="logout()">注销</a>
    </div>
</div>
<h1>课程申请列表</h1>
<table id="courseApplicationTable">
    <thead>
    <tr>
        <th>申请类别</th>
        <th>申请人ID</th>
        <th>课程名</th>
        <th>审批状态</th>
    </tr>
    </thead>
    <tbody id="tableBody" class="tableBody">
    <!-- 表格内容将通过 JavaScript 动态添加 -->
    </tbody>
</table>

<script>
    $.ajax({
        url: "/studentInfo/teacher/query_open",
        type: "GET",
        dataType: "json",
        success: function(data) {
            // 处理返回的课程数据
            for (let i = 0; i < data.length; i++) {
                const cache = data[i];
                console.log(cache);
                const tr = $("<tr>");
                tr.append("<td>" + cache.kind + "</td>");
                tr.append("<td>" + cache.tId + "</td>");
                tr.append("<td>" + cache.cName + "</td>");
                var approvalStatus = "";
                if (cache.approval === 1) {
                    approvalStatus = "通过";
                } else if (cache.approval === -1) {
                    approvalStatus = "不通过";
                } else {
                    approvalStatus = "未审批";
                }
                tr.append("<td>" +approvalStatus + "</td>");
                $("#tableBody").append(tr);
            }
        },
        error: function() {
            alert("Failed to fetch course data.");
        }
    });

</script>
</body>
</html>
