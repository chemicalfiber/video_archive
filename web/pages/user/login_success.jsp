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
    <%@include file="/pages/includes/header.jsp"%>
</head>
<body>
登录成功！欢迎${sessionScope.login_user.g_name} ${sessionScope.login_user.u_nick_name}！<br>
正在回到<a href="${pageContext.request.contextPath}/index.jsp">首页</a>......
</body>
<script>
    window.onload = setTimeout(function (){
        location.href = "${pageContext.request.contextPath}/index.jsp";
    },3000);
</script>
</html>
