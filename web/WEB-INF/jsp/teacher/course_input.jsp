<%@ page import="model.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: LGelo
  Date: 2023/12/27
  Time: 17:25
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

<button id="submitBtn" onclick="mysub()">提交</button>

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
    window.onload = function (){
        myload();
    }
    function myload(){
        $.ajax({
            url:"/studentInfo/teacher/get_stu_info",
            type:"GET",

            success:function(students){
                if(students!=null){
                    console.log(students);
                    var tbody = document.querySelector('#studentTable tbody');

                    // 动态添加学生数据到表格
                    students.forEach(function(student) {
                        var tr = document.createElement('tr');
                        var cId = document.createElement('td');
                        var stuId = document.createElement('td');
                        var scoreTd = document.createElement('td');
                        var scoreInput = document.createElement('input');
                        scoreInput.type = 'text';
                        scoreInput.name = 'score';
                        scoreInput.placeholder = '请输入成绩';

                        cId.textContent = student.courseId;
                        stuId.textContent = student.studentId;

                        scoreTd.appendChild(scoreInput);

                        tr.appendChild(stuId);
                        tr.appendChild(cId);
                        tr.appendChild(scoreTd);
                        tbody.appendChild(tr);
                    });
                }else{
                    alert("登录失败，用户名或密码错误!");
                }
            },
            error:function (){
                location.href="login.do";
            }
        })
    }
    function mysub(){
        var rows = document.querySelectorAll('#studentTable tbody tr');
        var formData = [];

        // 收集表单数据
        rows.forEach(function(row) {
            var sId = row.cells[0].textContent;
            var cId = row.cells[1].textContent;
            var score = row.querySelector('input[name="score"]').value;

            formData.push({
                stuId: sId,
                cId: cId,
                score: score
            });
            console.log(formData);
        });
        $.ajax({
            url:"/studentInfo/teacher/sub_score",
            type:"POST",
            dataType:"json",
            data:formData,
            success:function(result){
                if (result != null && result === "success"){
                    location.href = "score_list.do";
                }
            },
            error:function (){
                alert("提交失败")
                console.log("提交失败");
            }
        })
    }

</script>
<script>

</script>
</body>
</html>
