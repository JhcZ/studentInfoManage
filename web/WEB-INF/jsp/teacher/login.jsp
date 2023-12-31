<%--
  Created by IntelliJ IDEA.
  User: LGelo
  Date: 2023/12/21
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Teacher login</title>
    <script src="js/jquery.min.js"></script>
</head>
<body>
<div id="container">
    <div class="row">
        <span>账号</span>
        <!-- 通过name属性才能构造请求键值对中的key键 -->
        <input type="text" id="teacherId" name="teacherId">
    </div>
    <div class="row">
        <span>密码</span>
        <input type="password" id="password" name="password">
    </div>
    <div class="row">
        <button id="submit" onclick="mysub()" >登录</button>
    </div>
</div>
<script>
    function mysub(){
        //1.非空校验
        var teacherId = jQuery("#teacherId");
        var password = jQuery("#password");
        if(teacherId.val() === ""){
            alert("请输入用户名!");
            teacherId.focus();
            return;
        }
        if(password.val()===""){
            alert("请输入密码");
            password.focus();
            return;
        }
        //2.ajax请求登录接口
        jQuery.ajax({
            url:"/studentInfo/teacher/login",
            type:"POST",
            data:{"teacherId":teacherId.val(),"password":password.val()},
            success:function(result){
                if(result!=null && result==="success"){
                    console.log(result);
                    //登录成功了
                    location.href="list.do";

                }else{
                    alert("登录失败，用户名或密码错误!");
                }
            }
        })
    }
</script>
</body>
</html>
