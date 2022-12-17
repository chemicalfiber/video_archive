<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ChemicalFiber
  Date: 2022/12/17
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑视频-创作者后台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">

</head>
<body>
<div class="title">
    <img src="${pageContext.request.contextPath}/static/img/logo.png" alt="logo"
         style="cursor:pointer;height: 80px;width: 80px;" onclick="location.href='${pageContext.request.contextPath}/'"/><span>管理员后台-视频管理</span>
    <%--    <span class="userConsole">您好，请<a href="pages/user/login.jsp">登录</a>以观看/上传视频</span>--%>

    <%@include file="/pages/includes/user_console.jsp" %>
</div>
<div class="content">
<%--TODO:视频修改页面--%>
</div>
</body>
</html>
