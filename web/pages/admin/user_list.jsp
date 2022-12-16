<%--
  Created by IntelliJ IDEA.
  User: chemicalfiber
  Date: 2022/11/15
  Time: 9:30 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户管理-超级管理员后台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">

</head>
<body>
<div class="title">
    <img src="${pageContext.request.contextPath}/static/img/logo.png" alt="logo"
         style="cursor:pointer;height: 80px;width: 80px;" onclick="location.href='${pageContext.request.contextPath}/'"/><span>管理员后台-用户管理</span>
    <%--    <span class="userConsole">您好，请<a href="pages/user/login.jsp">登录</a>以观看/上传视频</span>--%>

    <%@include file="/pages/includes/user_console.jsp"%>
</div>
<div class="content">
    <div class="userList">
        <!--TODO:超级管理员的用户管理页面-->
        <c:forEach var="user" items="${requestScope.page.items}">

        </c:forEach>
    </div>
</div>
</body>
</html>
