<%--
  Created by IntelliJ IDEA.
  User: LGelo
  Date: 2023/12/27
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Open Course</title>
    <script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
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
<h1>课程申请表单</h1>
<form id="courseApplicationForm">
    <label for="kind">申请类别:</label>
    <input type="number" id="kind" name="kind" required><br><br>

    <label for="cName">课程名:</label>
    <input type="text" id="cName" name="cName" required><br><br>

    <input type="submit" value="提交申请">
</form>

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
    $(document).ready(function() {
        $('form').submit(function(event) {
            event.preventDefault(); // 阻止表单提
            // 获取表单数据

            var formData = {
                kind: $('#kind').val(),
                cName: $('#cName').val(),
            };
            $.ajax({
                url: "/studentInfo/teacher/open_course",
                type: "POST",
                data: formData,
                success: function (result) {
                    console.log(result);
                    if (result != null && result === "success") {
                        console.log(result);
                        //登录成功了
                        location.href = "open_list.do";

                    } else {
                        alert("申请失败!");
                    }
                },
                error: function (result) {
                    console.log("申请失败");
                }
            });
        })
    });
</script>
</body>
</html>
