<%--
  Created by IntelliJ IDEA.
  User: LGelo
  Date: 2023/12/27
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
<table id="courseTable">
    <thead>
    <tr>
        <th>课程号</th>
        <th>课程名称</th>
        <th>开课时间</th>
        <th>开设学期</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody></tbody>
</table>
<script>
    function logout(){
        $.ajax({
            url:"/studentInfo/teacher/logout",
            type:"GET",

            success:function(result){
                if(result!=null && result==="success"){
                    console.log(result);
                    //注销成功
                    location.href="login.do";
                }else{
                    alert("登录失败，用户名或密码错误!");
                }
            }
        })
    }

    fetch('/studentInfo/teacher/query')
        .then(response => response.json())
        .then(data => {
            displayCourses(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
    function displayCourses(courses) {

        const tableBody = document.querySelector('#courseTable tbody');
        tableBody.innerHTML = '';
        courses.forEach(course => {
            const row = document.createElement('tr');
            row.innerHTML =
                "<td>" + course.courseId + "</td>" +
                "<td>" + course.name + "</td>" +
                "<td>" + course.startTime + "</td>" +
                "<td>" + course.semester + "</td>" +
                "<td><button onclick='submitGrade(\"" + course.courseId + "\")'>成绩录入</button></td>";
            tableBody.appendChild(row);
        });
    }
    function submitGrade(courseId) {
        // 使用 AJAX 向后端发送 courseId
        $.ajax({
            url: "/studentInfo/teacher/query_stu_course",
            method: "POST",
            data: { courseId: courseId },
            success: function(response) {
                console.log("Course ID submitted successfully:", courseId);
                location.href = "course_input.do"
            },
            error: function(error) {
                console.log("Error occurred while submitting Course ID:", error);
            }
        });
    }
</script>
</body>
</html>
