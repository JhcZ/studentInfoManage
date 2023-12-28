<%--
  Created by IntelliJ IDEA.
  User: LGelo
  Date: 2023/12/28
  Time: 1:49
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
<table id="studentTable">
    <thead>
    <tr>

        <th>学号</th>
        <td>课程ID</td>
        <th>成绩</th>
    </tr>
    </thead>
    <tbody>
    <!-- 学生数据将通过Ajax动态添加到这里 -->
    </tbody>
</table>
<script>
    window.onload = function (){
        myload();
    }
    function myload(){
        $.ajax({
            url:"/studentInfo/teacher/get_score_list",
            type:"GET",

            success:function(scores){
                const tableBody = document.querySelector('#courseTable tbody');
                tableBody.innerHTML = '';
                scores.forEach(score => {
                    const row = document.createElement('tr');
                    row.innerHTML =
                        "<td>" + score.studentId + "</td>" +
                        "<td>" + score.courseId + "</td>" +

                        "<td>" + score.grade + "</button></td>";
                    tableBody.appendChild(row);
                });
            },
            error:function (){
                alert("查询失败")
            }
        })
    }
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
            },
            error:function (){
                location.href="login.do";
            }
        })
    }
</script>

</body>
</html>
