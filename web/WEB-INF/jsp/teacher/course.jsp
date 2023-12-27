

<%--
  Created by IntelliJ IDEA.
  User: LGelo
  Date: 2023/12/21
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Course" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Course List</title>
    <script src="js/jquery.min.js"></script>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }
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
        <th>授课教师ID</th>
        <th>上课地点</th>
        <th>上课周数</th>
        <th>课程类别</th>
        <th>上课日期</th>
        <th>上课时间</th>
        <th>开课时间</th>
        <th>开设学期</th>
        <th>选课人数</th>
    </tr>
    </thead>
    <tbody></tbody>
</table>

<script>
    fetch('/studentInfo/teacher/query')
        .then(response => response.json())
        .then(data => {
            displayCourses(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
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
    function displayCourses(courses) {
        
        const tableBody = document.querySelector('#courseTable tbody');
        tableBody.innerHTML = '';
        courses.forEach(course => {
            const row = document.createElement('tr');
            console.log(
   "<td>" + course.courseId + "</td>" ,
  "<td>" + course.name + "</td>",
  "<td>" + course.teacherId + "</td>",
  "<td>" + course.location + "</td>",
  "<td>" + course.courseDuration + "</td>",
  "<td>" + course.flag + "</td>",
  "<td>" + course.classDay + "</td>",
  "<td>" + course.classTime + "</td>",
  "<td>" + course.startTime + "</td>",
  "<td>" + course.semester + "</td>",
  "<td>" + course.numOfStu + "</td>");

            row.innerHTML = 
  "<td>" + course.courseId + "</td>" + 
  "<td>" + course.name + "</td>" +
  "<td>" + course.teacherId + "</td>" +
  "<td>" + course.location + "</td>" +
  "<td>" + course.courseDuration + "</td>" +
  "<td>" + course.flag + "</td>" + 
  "<td>" + course.classDay + "</td>" +
  "<td>" + course.classTime + "</td>" +
  "<td>" + course.startTime + "</td>" +
  "<td>" + course.semester + "</td>" + 
  "<td>" + course.numOfStu + "</td>" ;
            tableBody.appendChild(row);
        });
    }
    
</script>
</body>

