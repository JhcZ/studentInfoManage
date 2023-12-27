<%--
  Created by IntelliJ IDEA.
  User: LGelo
  Date: 2023/12/21
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List</title>
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
<div>
    <div class="box">
        <a href="update.do">修改个人信息</a>
    </div>
    <div class="box">
        <a href="inputCourse_list.do">录入成绩</a>
    </div>
    <div class="box">
        <a href="open_course.do">开课申请</a>
    </div>
    <div class="box">
        <a href="open_list.do">开课申请列表</a>
    </div>
    <div class="box">
        <a href="mod.do">课程修改申请</a>
    </div>
    <div class="box">
        <a href="modlist.do">修改申请列表</a>
    </div>
    <div class="box">
        <a href="course.do">查询上课课程</a>
    </div>
</div>
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
            },
            error:function (){
                location.href="login.do";
            }
        })
    }
</script>
</body>
</html>
