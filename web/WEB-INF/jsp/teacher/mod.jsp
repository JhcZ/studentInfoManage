<%--
  Created by IntelliJ IDEA.
  User: LGelo
  Date: 2023/12/25
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>申请课程表单</title>
    <script src="js/jquery.min.js"></script>
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

<h1>申请课程表单</h1>
<form>
    <label for="courseId">课程ID：</label>
    <input type="number" id="courseId" name="courseId" required><br><br>

    <label for="name">名称：</label>
    <input type="text" id="name" name="name"><br><br>

    <label for="location">地点：</label>
    <input type="text" id="location" name="location"><br><br>

    <label for="courseDuration">课程时长：</label>
    <input type="text" id="courseDuration" name="courseDuration"><br><br>

    <label for="startTime">开始时间：</label>
    <input type="date" id="startTime" name="startTime"><br><br>

    <label for="startTime">上课日期：</label>
    <input type="text" id="classDay" name="classDay"><br><br>

    <label for="startTime">上课时间：</label>
    <input type="text" id="classTime" name="classTime"><br><br>

    <label for="semester">学期：</label>
    <input type="text" id="semester" name="semester"><br><br>

    <input type="submit" value="提交">
</form>

<script>
    $(document).ready(function() {
        $('form').submit(function(event) {
            event.preventDefault(); // 阻止表单提交

            // 获取表单数据
            var formData = {
                courseId: $('#courseId').val(),
                name: $('#name').val(),
                location: $('#location').val(),
                courseDuration: $('#courseDuration').val(),
                startTime: $('#startTime').val(),
                classDay: $('#classDay').val(),
                classTime: $('#classTime').val(),
                semester: $('#semester').val(),
            };

            // 发送Ajax请求
            $.ajax({
                url: '/studentInfo/teacher/mod', // 后端处理请求的URL
                type: 'POST',
                data: formData,
                success: function(response) {
                    // 请求成功后的操作
                    console.log(response);
                    if(response != null && response==="success"){
                        console.log('修改申请提交成功！');
                        location.href="modlist.do";
                    }else{

                        alert('修改申请提交失败！');
                        location.href="list.do";
                    }

                    // 可以根据返回的数据进行其他操作
                },
                error: function(xhr, status, error) {
                    // 请求失败时的操作
                    console.log(xhr.responseText);
                    alert('表单提交失败！');
                    location.href="list.do";
                }
            });
        });
    });
</script>
</body>
</html>
