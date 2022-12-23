<%@ page import="cn.cf.videoarchive.Const" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ChemicalFiber
  Date: 2022/12/17
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getSession().getAttribute(Const.LOGIN_USER) == null){
        request.setAttribute(Const.ERR_MSG,"请先登录！");
        request.getRequestDispatcher(request.getContextPath() + "/pages/user/login.jsp").forward(request,response);
        return;
    }
%>
<html>
<head>
    <title>修改个人信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
<div class="title">
    <img src="${pageContext.request.contextPath}/static/img/logo.png" alt="logo"
         style="cursor:pointer;height: 80px;width: 80px;"
         onclick="location.href='${pageContext.request.contextPath}/'"/><span>修改个人信息</span>
    <%--    <span class="userConsole">您好，请<a href="pages/user/login.jsp">登录</a>以观看/上传视频</span>--%>
    <%@include file="/pages/includes/user_console.jsp"%>
</div>
<div class="content">
    <div class="inputBlock">
        <span style="color: red" id="err_msg">${requestScope.err_msg}</span>
        <form action="${pageContext.request.contextPath}/user?action=update" method="post">
            <input type="hidden" name="id" value="${sessionScope.login_user.u_id}"/>
            <label><span>用户名：</span>
                <input type="text" name="username" readonly value="${sessionScope.login_user.u_name}"/>
            </label><br>
            <label><span>昵称：</span>
                <input type="text" name="nickname" placeholder="输入昵称" required value="${sessionScope.login_user.u_nick_name}">
            </label><br>
            <label><span>旧密码：</span>
                <input type="password" name="oldPassword" placeholder="输入旧的密码"/>
            </label><br>
            <label><span>新密码：</span>
                <input type="password" name="password" placeholder="在此处输入新的密码" minlength="8" maxlength="16">
            </label><br>
            <label><span>确认密码：</span>
                <input type="password" name="passwordConfirm" placeholder="在此处确认新的密码" minlength="8" maxlength="16">
            </label><br>
            <br>
            <input type="submit" value="确定修改" class="submit">
        </form>
    </div>
</div>
</body>
</html>
