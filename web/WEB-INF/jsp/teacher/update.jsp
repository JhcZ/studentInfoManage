<%--
  Created by IntelliJ IDEA.
  User: LGelo
  Date: 2023/12/21
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改密码</title>
    <script src="js/jquery.min.js"></script>
</head>
<body>
<div class="row">
    <span>输入初始密码</span>
    <input type="password" id="initial_password" name="initial_password">
</div>
<div class="row">
    <span>输入修改密码</span>
    <input type="password" id="up_password1" name="up_password1">
</div>
<div class="row">
    <span>输入修改密码</span>
    <input type="password" id="up_password2" name="up_password2">
</div>
<div class="row">
    <button id="submit" onclick="mysub()" >登录</button>
</div>
<script>
    function mysub(){
        //1.非空校验
        var initial_password = jQuery("#initial_password");
        var up_password1 = jQuery("#up_password1");
        var up_password2 = jQuery("#up_password2");
        if(initial_password.val() === ""){
            alert("请输入初始密码!");
            initial_password.focus();
            return;
        }
        if(up_password1.val()===""){
            alert("请输入要修改的密码");
            up_password1.focus();
            return;
        }
        if(up_password2.val()===""){
            alert("请确认密码");
            up_password2.focus();
            return;
        }
        if(up_password1.val()!=up_password2.val()){
            alert("密码不相同，请确认密码");
            up_password2.focus();
            return;
        }
        //2.ajax请求修改接口
        jQuery.ajax({
            url:"/studentInfo/teacher/update",
            type:"POST",
            data:{"initial_password":initial_password.val(),"up_password1":up_password1.val()},
            success:function(result){
                if(result!=null && result == "success"){
                    //修改成功了
                    console.log("修改成功");
                    location.href="login.do";
                }else{
                    alert("修改失败!");
                }
            }
        })
    }
</script>
</body>
</html>
