<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/12/28 0028
  Time: 1:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加教师</title>
</head>

<body>

<div class="container-fluid">
    <div class="row">
        <div>
            <h4>添加教师信息</h4>
            <form id="updateInfoForm" action="add" method="post">
                教师号：<input type="text" id="updateTeacherId" name="updateTeacherId" required><br>
                姓名：<input type="text" id="updateName" name="updateName" required><br>
                部门：<input type="text" id="updateDepartment" name="updateDepartment" required><br>
                学位：<input type="text" id="updateDegree" name="updateDegree" required><br>
                性别：<input type="text" id="updateSex" name="updateSex" required><br>
                电话：<input type="text" id="updatePhone" name="updatePhone" required><br>
                邮箱：<input type="text" id="updateEmail" name="updateEmail" required><br>
                入职时间：<input type="text" id="updateCreateTime" name="updateCreateTime" required><br>
                密码：<input type="text" id="updatePwd" name="updatePwd" required><br>
                <button type="submit" class="bt btn-primary">确定更新</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
