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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>查询课程</title>
    <script src="js/jquery.min.js"></script>
</head>
<body>
<h1>课程信息</h1>
<div class="container">
    <div class="row">
        <div class="course-info">
            <label>课程号:</label>
            <span class="courseId"></span>
        </div>
        <div class="course-info">
            <label>课程名称:</label>
            <span class="name"></span>
        </div>
        <div class="course-info">
            <label>授课教师:</label>
            <span class="teacher"></span>
        </div>
        <div class="course-info">
            <label>授课教师ID:</label>
            <span class="teacherId"></span>
        </div>
        <div class="course-info">
            <label>上课地点:</label>
            <span class="location"></span>
        </div>
        <div class="course-info">
            <label>上课周数:</label>
            <span class="courseDuration"></span>
        </div>
        <div class="course-info">
            <label>课程类别:</label>
            <span class="flag"></span>
        </div>
        <div class="course-info">
            <label>开设班级:</label>
            <span class="classes"></span>
        </div>
        <div class="course-info">
            <label>课程开始时间:</label>
            <span class="startTime"></span>
        </div>
        <div class="course-info">
            <label>课程开设学期:</label>
            <span class="semester"></span>
        </div>
        <div class="course-info">
            <label>选课人数:</label>
            <span class="numOfStu"></span>
        </div>
    </div>
</div>

<script>
    // 使用Ajax请求课程数据
    function getCourse(){
        $.ajax({
            url: '/studentInfo/teacher/query',
            type: 'POST',
            success: function(data) {
                // 将课程数据展示到页面上对应的元素中
                let container = document.querySelector('.container');
                for(let course of data) {
                    let row = document.createElement('div');
                    row.className = 'row';
                    let courseId = document.createElement('div');
                    courseId.className = 'courseId';
                    courseId.innerHTML = course.courseId;

                    let name = document.createElement('div');
                    courseId.className = 'name';
                    courseId.innerHTML = course.name;

                    let teacher = document.createElement('div');
                    courseId.className = 'teacher';
                    courseId.innerHTML = course.teacher;

                    let teacherId = document.createElement('div');
                    courseId.className = 'teacherId';
                    courseId.innerHTML = course.teacherId;

                    let location = document.createElement('div');
                    courseId.className = 'location';
                    courseId.innerHTML = course.loaction;

                    let courseDuration = document.createElement('div');
                    courseId.className = 'courseDuration';
                    courseId.innerHTML = course.courseDuration;

                    let flag = document.createElement('div');
                    courseId.className = 'flag';
                    courseId.innerHTML = course.flag;

                    let classes = document.createElement('div');
                    courseId.className = 'classes';
                    courseId.innerHTML = course.classes;

                    let startTime = document.createElement('div');
                    courseId.className = 'startTime';
                    courseId.innerHTML = course.startTime;

                    let semester = document.createElement('div');
                    courseId.className = 'semester';
                    courseId.innerHTML = course.semester;

                    let numOfStu = document.createElement('div');
                    courseId.className = 'numOfStu';
                    courseId.innerHTML = course.numOfStu;
                    row.appendChild(courseId);
                    row.appendChild(name);
                    row.appendChild(teacher);
                    row.appendChild(teacherId);
                    row.appendChild(location);
                    row.appendChild(courseDuration);
                    row.appendChild(flag);
                    row.appendChild(classes);
                    row.appendChild(startTime);
                    row.appendChild(semester);
                    row.appendChild(numOfStu);
                    container.appendChild(row);
                }
            },
            error: function() {
                console.log('Error occurred');
            }
        });
    }
    getCourse();

</script>
</body>
</html>
