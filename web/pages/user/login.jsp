<%--
  Created by IntelliJ IDEA.
  User: chemicalfiber
  Date: 2022/11/13
  Time: 3:52 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
<div class="title"><img src="${pageContext.request.contextPath}/static/img/logo.png" alt="logo" style="cursor:pointer;height: 80px;width: 80px;" onclick="location.href='${pageContext.request.contextPath}/'"><span>UP主视频存档系统</span></div>
<div class="content">
    <div class="inputBlock">
        <span style="color: red">${requestScope.err_msg}</span>
        <form action="${pageContext.request.contextPath}/user?action=login" method="post">
            <label><span>用户名：</span>
                <input type="text" name="username" placeholder="输入用户名" value="${requestScope.username}" required>
            </label><br>
            <label><span>密码：</span>
                <input type="password" name="password" placeholder="在此处输入您的密码" required>
            </label><br>
            <input type="submit" value="登录" class="submit">
        </form>
        没有账户？去
        <a href="${pageContext.request.contextPath}/pages/user/register.jsp">注册</a>
    </div>
</div>
</body>
</html>
