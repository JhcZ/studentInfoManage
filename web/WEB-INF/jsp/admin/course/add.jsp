<%--
  Created by IntelliJ IDEA.
  User: mila
  Date: 2023/12/21
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <base href="http://${header.host}${pageContext.request.contextPath}/admin/">
            <meta charset="utf-8">
    <title>课程信息表单</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        label {
            margin: 10px 0;
            display: inline-block;
        }

        input[type="text"], input[type="number"], input[type="date"], select {
            margin-bottom: 20px;
            width: 200px;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            padding: 5px;
            cursor: pointer;
        }
        .class-time-container {
            display: flex;
            flex-wrap: wrap;
        }
        .class-time {
            margin-bottom: 10px;
            width: 33%;
            box-sizing: border-box;
            padding: 5px;
        }
    </style>
</head>
<body>
<form id="courseForm" action="addCourse" method="post">
    <label for="courseName">课程名称:</label>
    <input type="text" id="courseName" name="courseName"><br><br>

    <label for="teacher">授课教师:</label>
    <input type="hidden" id="teacherId" name="teacherId"/>
    <input type="text" id="teacher" name="teacher" onkeyup="suggestTeachers()"><br>
    <ul id="teacherSuggestions" style="display:none;"></ul><br><br>

    <label for="location">上课地点:</label>
    <input type="text" id="location" name="location"><br><br>

    <label for="weeks">上课周数:</label>
    <input type="number" id="weeks" name="weeks" min="1"><br><br>

    <label for="startDate">开课时间:</label>
    <input type="date" id="startDate" name="startDate"><br><br>

    <label for="classDay">上课日期:</label>
    <select id="classDay" name="classDay">
        <option value="1">周一</option>
        <option value="2">周二</option>
        <option value="3">周三</option>
        <option value="4">周四</option>
        <option value="5">周五</option>
        <option value="6">周六</option>
        <option value="7">周日</option>
    </select><br><br>

    <div class="class-time-container">
        <!-- 动态生成课程时间复选框 -->
        <!-- 示例： -->
        <!-- <div class="class-time">
            <input type="checkbox" id="class1" name="classTime" value="1">
            <label for="class1">第1节</label>
        </div> -->
        <!-- 生成其余复选框 -->
    </div>



    <label for="semester">开课学期:</label>
    <input type="number" id="semester" name="semester" min="1" max="2"><br><br>

    <label for="enrollment">选课人数:</label>
    <input type="number" id="enrollment" name="enrollment" min="1"><br><br>

    <input type="submit" value="提交">
</form>

</body>
</html>
<script>
    function suggestTeachers() {
        var input = document.getElementById('teacher').value;

        if (input.length > 0) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "getTeachers?q=" + input, true);
            xhr.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    var suggestions = JSON.parse(this.responseText);
                    var suggestionBox = document.getElementById('teacherSuggestions');
                    suggestionBox.innerHTML = '';

                    suggestions.forEach(function(teacher) {
                        var li = document.createElement('li');
                        li.textContent = teacher.name + " " + teacher.teacherId;
                        li.onclick = function() {
                            document.getElementById('teacher').value = teacher.name;
                            document.getElementById('teacherId').value = teacher.teacherId;
                            suggestionBox.style.display = 'none';
                        };
                        suggestionBox.appendChild(li);
                    });
                    suggestionBox.style.display = 'block';
                }
            };
            xhr.send();
        }
    }

    const container = document.querySelector('.class-time-container');
    for (let i = 1; i <= 11; i++) {
        let div = document.createElement('div');
        div.className = 'class-time';
        let input = document.createElement('input');
        input.type = 'checkbox';
        input.id = 'class' + i;
        input.name = 'classTime';
        input.value = i;
        let label = document.createElement('label');
        label.htmlFor = 'class' + i;
        label.textContent = '第' + i + '节';
        div.appendChild(input);
        div.appendChild(label);
        container.appendChild(div);
    }
</script>

