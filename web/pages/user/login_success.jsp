<%--
  Created by IntelliJ IDEA.
  User: chemicalfiber
  Date: 2022/11/13
  Time: 3:53 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功！</title>
</head>
<body>
登录成功！欢迎${sessionScope.user.u_grant} ${sessionScope.user.u_nick_name}！<br>
正在回到<a href="${pageContext.request.contextPath}/">首页</a>......
</body>
</html>
