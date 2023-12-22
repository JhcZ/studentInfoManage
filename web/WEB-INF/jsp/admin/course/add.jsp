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
    </style>
    <!-- 链接 CSS -->
</head>
<body>
<form id="courseForm">
    <label for="courseName">课程名称:</label>
    <input type="text" id="courseName" name="courseName"><br><br>

    <label for="teacher">授课教师:</label>
    <input type="hidden" id="teacherId" name="teacherId"/>
    <input type="text" id="teacher" name="teacher" onkeyup="suggestTeachers()"><br>
    <ul id="teacherSuggestions" style="display:none;"></ul><br><br>

    <label for="location">上课地点:</label>
    <input type="text" id="location" name="location"><br><br>

    <label for="weeks">上课周数:</label>
    <input type="number" id="weeks" name="weeks"><br><br>

    <label for="class">开设班级:</label>
    <select id="class" name="class">
        <!-- 班级选项 -->
    </select><br><br>

    <label for="startDate">开课时间:</label>
    <input type="date" id="startDate" name="startDate"><br><br>

    <label for="semester">开课学期:</label>
    <input type="number" id="semester" name="semester"><br><br>

    <label for="enrollment">选课人数:</label>
    <input type="number" id="enrollment" name="enrollment"><br><br>

    <input type="submit" value="提交">
</form>

<!-- 链接 JS -->
</body>
</html>
<script>
    function suggestTeachers() {
        var input = document.getElementById('teacher').value;

        if (input.length > 0) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "getTeachers.do?q=" + input, true);
            xhr.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    var suggestions = JSON.parse(this.responseText);
                    var suggestionBox = document.getElementById('teacherSuggestions');
                    suggestionBox.innerHTML = '';

                    suggestions.forEach(function(teacher) {
                        var li = document.createElement('li');
                        li.textContent = teacher;
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


</script>

