<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/12/27 0027
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"	%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员端-学生列表</title>
    <base href="http://${header.host}${pageContext.request.contextPath}/admin/">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <jsp:include page="../../css_template.jsp"/>
</head>

<body>
    <div class="row">
        <div class="col-sm-9 col-md-9 main">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>专业</th>
                        <th>学院</th>
                        <th>性别</th>
                        <th>班级</th>
                        <th>状态</th>
                        <th>密码</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${studentList}" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td>${student.studentId}</td>
                                <td>${student.name}</td>
                                <td>${student.major}</td>
                                <td>${student.department}</td>
                                <td>${student.sex}</td>
                                <td>${student.className}</td>
                                <td>${student.status}</td>
                                <td>${student.password}</td>
                                <td>
                                    <button>
                                        <a href="student/reset?id=${student.studentId }">重置密码</a>
                                    </button>
                                    <button>
                                        <a href="student/del?id=${student.studentId }">删除</a>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:if test="${p>1}">
                <a href="student/list?p=${p-1 }">上一页</a>
            </c:if>
            <c:if test="${p<pCount}">
                <a href="student/list?p=${p+1 }">下一页</a>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
