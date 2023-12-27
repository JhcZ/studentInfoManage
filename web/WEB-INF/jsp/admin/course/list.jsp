<%--
  Created by IntelliJ IDEA.
  User: mila
  Date: 2023/12/26
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <base href="http://${header.host}${pageContext.request.contextPath}/admin/">
  <meta charset="utf-8">
  <title>课程列表</title>
  <style>
    #courseList {
      width: 100%;
      border-collapse: collapse;
    }

    #courseList th, #courseList td {
      border: 1px solid #ddd;
      padding: 8px;
      text-align: left;
    }

    #pagination {
      margin-top: 20px;
    }

    .pagination-button {
      cursor: pointer;
      padding: 5px;
      border: 1px solid #ddd;
      margin-right: 5px;
    }
  </style>
</head>
<body>
<div id="courseList">
  <!-- 课程信息将通过 JavaScript 动态加载 -->
</div>
<div id="pagination">
  <!-- 分页按钮将在这里生成 -->
</div>
</body>
</html>
<script>
  document.addEventListener('DOMContentLoaded', function() {
    loadCourses(1); // 加载第一页的课程
  });

  function loadCourses(pageNumber) {
    // 这里使用 Ajax 请求来加载课程信息
    // 示例 URL: 'course/query?page=' + pageNumber
    // 假设后端返回的数据格式如下:
    // {
    //     courses: [{id, name, teacher, location, weeks, day, time, startDate, semester, enrollment}, ...],
    //     totalPages: 5
    // }
    // 在这里处理 Ajax 请求和响应...
    var xhr = new XMLHttpRequest();
    xhr.open("POST" , "course/query?page=" + pageNumber , true);
    xhr.onreadystatechange = function() {
      if(this.readyState === 4 && this.status === 200) {
        var res = JSON.parse(this.responseText)
        // 更新课程列表的 HTML
        updateCourseListHTML(res.courses);
        // 更新分页按钮的 HTML
        updatePaginationHTML(res.totalPages, pageNumber);
      }
      else {
        console.log("error")
      }
    }
    xhr.send();
  }

  function updateCourseListHTML(courses) {
    var html = '<table id="courseList">';
    html += '<tr><th>ID</th><th>名称</th><th>教师</th><th>地点</th><th>周数</th><th>日期</th><th>时间</th><th>开课时间</th><th>学期</th><th>人数</th><th>操作</th></tr>';

    courses.forEach(function(course) {
      console.log(course);
      html += '<tr>' +
              '<td>' + course.courseId + '</td>' +
              '<td>' + course.name + '</td>' +
              '<td>' + course.teacherId + '</td>' +
              '<td>' + course.location + '</td>' +
              '<td>' + course.courseDuration + '</td>' +
              '<td>' + course.classDay + '</td>' +
              '<td>' + course.classTime + '</td>' +
              '<td>' + course.startTime + '</td>' +
              '<td>' + course.semester + '</td>' +
              '<td>' + course.numOfStu + '</td>' +
              '<td><button onclick="deleteCourse(' + course.courseId + ')">删除</button> ' +
              '<button onclick="viewDetails(' + course.courseId + ')">详情</button></td>' +
              '</tr>';
    });

    html += '</table>';
    document.getElementById('courseList').innerHTML = html;
  }

  function updatePaginationHTML(totalPages, currentPage) {
    var html = '';
    for (var i = 1; i <= totalPages; i++) {
      html += '<span class="pagination-button" onclick="loadCourses(' + i + ')">' + i + '</span>';
    }
    document.getElementById('pagination').innerHTML = html;
  }

  function deleteCourse(courseId) {
    if (confirm('确认删除课程吗？')) {
      var xhr = new XMLHttpRequest();
      xhr.open("POST", "course/delete?id=" + courseId, true); // 假设的删除课程的 API
      xhr.onreadystatechange = function() {
        if (this.readyState === 4) {
          if(this.status === 200) {
            // 成功删除后，从表格中移除该课程的行
            window.location.reload();
          } else {
            // 错误处理
            console.error("删除失败: ", this.responseText);
          }
        }
      };
      xhr.send();
    }
  }

  function viewDetails(courseId) {
    // 在这里处理查看详情逻辑，比如打开新页面或弹出窗口显示课程详细信息
    window.location.href="course/details?id=" + courseId;
  }

</script>
