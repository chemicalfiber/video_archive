<%@ page import="cn.cf.videoarchive.Const" %><%--
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
</head>
<body>
<form action="${pageContext.request.contextPath}/user?action=login" method="post">
    <label>用户名：
        <input type="text" name="username">
    </label><br>
    <label>密码：
        <input type="password" name="password">
    </label><br>
    <input type="submit" value="登录">
</form>
</body>
</html>
