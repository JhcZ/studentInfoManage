<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/12/28 0028
  Time: 0:21
  To change this template use File | Settings | File Templates.
--%>
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
    <title>管理员端-教师列表</title>
    <base href="http://${header.host}${pageContext.request.contextPath}/admin/">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <jsp:include page="../../css_template.jsp"/>
</head>

<body>
<div class="row">
    <div class="col-sm-9 col-md-9 main">
        <%--头部导航--%>
        <div>
            <a href="dashBoard.do">管理员主页</a>
        </div>
        <div class="table-responsive">

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>教师号</th>
                    <th>姓名</th>
                    <th>部门</th>
                    <th>学位</th>
                    <th>性别</th>
                    <th>电话</th>
                    <th>邮箱</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="teacher" items="${teacherList}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${teacher.teacherId}</td>
                        <td>${teacher.name}</td>
                        <td>${teacher.department}</td>
                        <td>${teacher.degree}</td>
                        <td>${teacher.sex}</td>
                        <td>${teacher.phone}</td>
                        <td>${teacher.email}</td>
                        <td>
                            <button>
                                <a href="teacher/reset?id=${teacher.teacherId }">重置密码</a>
                            </button>
                            <button>
                                <a href="teacher/del?id=${teacher.teacherId }">删除</a>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:if test="${p>1}">
                <a href="teacher/list?p=${p-1 }" style="margin-right: 10px;">上一页</a>
            </c:if>
            <c:if test="${p<pCount}">
                <a href="teacher/list?p=${p+1 }" style="margin-right: 10px;">下一页</a>
            </c:if>
            <button type="button" class="btn btn-primary" style="margin-right: 10px;" onclick="redirectToAnotherPage()">
                添加教师
            </button>
        </div>

    </div>
</div>

<script>
    function redirectToAnotherPage(){
        window.location.href = "${pageContext.request.contextPath}/admin/teacher/add.do";
    }
</script>
</body>
</html>

