<%@ page import="cn.cf.videoarchive.Const" %><%--
  Created by IntelliJ IDEA.
  User: chemicalfiber
  Date: 2022/11/13
  Time: 8:54 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
<div class="title"><img src="${pageContext.request.contextPath}/static/img/logo.png" alt="logo" style="cursor:pointer;height: 80px;width: 80px;" onclick="location.href='${pageContext.request.contextPath}/'"/><span>UP主视频存档系统</span></div>
<div class="content">
    <div class="inputBlock">
        <span style="color: red" id="err_msg">${requestScope.err_msg}</span>
        <form action="${pageContext.request.contextPath}/user?action=register" method="post">
            <label><span>用户名：</span>
                <input type="text" name="username" id="username" placeholder="输入用户名" required minlength="4" maxlength="16" onblur="checkUsername()">
            </label><br>
            <label><span>昵称：</span>
                <input type="text" name="username" placeholder="输入昵称" required minlength="4" maxlength="16">
            </label><br>
            <label><span>密码：</span>
                <input type="password" name="password" placeholder="在此处输入您的密码" required minlength="8" maxlength="16">
            </label><br>
            <label><span>确认密码：</span>
                <input type="password" name="passwordConfirm" placeholder="在此处确认您的密码" required minlength="8" maxlength="16">
            </label><br>
            <label><span>图片验证码：</span>
                <input type="password" name="code" placeholder="输入右侧验证码图片内容" required style="width: 180px" maxlength="4" minlength="4">
            </label>
            <img src="${pageContext.request.contextPath}/code.jpg" alt="验证码" id="code"
                 onclick="changeVerifyCode()" style="width: 165px;height: 50px; position:relative; top: 20px"/>
            <br>
            <input type="submit" value="注册" class="submit">
        </form>
        已有账户？去
        <a href="${pageContext.request.contextPath}/pages/user/login.jsp">登录</a>
    </div>
</div>
</body>
<script>
    // 更新验证码
    function changeVerifyCode() {
        document.getElementById("code").src="${pageContext.request.contextPath}/code.jpg?d=" + new Date();
    }
    // 检查用户名是否被占用
    function checkUsername(){
        let username = document.getElementById("username").value;
        let xhr = new XMLHttpRequest();
        xhr.open("GET","${pageContext.request.contextPath}/user?action=checkName&username="+username);
        xhr.send();
        xhr.onreadystatechange = function (){
            if (xhr.readyState === 4 && xhr.status === 200){
                document.getElementById("err_msg").innerText = xhr.responseText;
            }
        }
    }
</script>
</html>
