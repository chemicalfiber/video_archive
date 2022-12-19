<%--
  Created by IntelliJ IDEA.
  User: ChemicalFiber
  Date: 2022/12/16
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>编辑用户-超级管理员后台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <style>
        #grantSelect{
            display: block;
            width: 310px;
            text-align: right;
            height: 40px;
            margin-top: 5%;
            border-radius: 7px;
        }
        select{
            height: 40px;
            width: 120px;
        }
    </style>
</head>
<body>
<div class="title">
    <img src="${pageContext.request.contextPath}/static/img/logo.png" alt="logo"
         style="cursor:pointer;height: 80px;width: 80px;"
         onclick="location.href='${pageContext.request.contextPath}/'"/><span>管理员后台-用户管理</span>
    <%@include file="/pages/includes/user_console.jsp"%>
</div>
<div class="content">
    <div class="inputBlock">
        <span style="color: red" id="err_msg">${requestScope.err_msg}</span>
        <form action="${pageContext.request.contextPath}/admin/user?action=edit" method="post">
            <input type="hidden" name="id" value="${requestScope.user.u_id}"/>
            <label><span>用户名：</span>
                <input type="text" name="username" readonly value="${requestScope.user.u_name}"/>
            </label><br>
            <label><span>昵称：</span>
                <input type="text" name="nickname" placeholder="输入昵称" value="${requestScope.user.u_nick_name}">
            </label><br>
            <label id="grantSelect">更改权限：
                <select name="grant">
                    <option value="1">超级管理员</option>
                    <option value="2">创作者</option>
                    <option value="3">观众</option>
                </select>
            </label>
            <label><span>重置密码？</span>
                <input type="checkbox" name="isResetPassword" style="width: 50px;"/>
            </label>
            <br>
            <input type="submit" value="确定修改" class="submit">
        </form>
    </div>
</div>
</body>
<script>
    function resetPassword(id, nickname) {
        let b = confirm("重置用户【" + nickname + "】的密码为123456？");
        if (b){
            location.href = "${pageContext.request.contextPath}/admin/user?action=resetPassword&id=" + id;
        }
    }
    // 跟据后端传过来的用户，动态地为下拉菜单的可选项赋值
    window.onload = function (){
        let children = document.getElementById("grantSelect").children[0].children;
        console.log(children);
        console.log(${requestScope.user.u_grant});
        <%--console.log(typeof(${requestScope.user.u_grant}+1)) // 运算结果是数字--%>
        let chose = ${requestScope.user.u_grant}-1; // 权限从1开始，索引从0开始，故-1
        children[chose].selected = true;
    }
</script>
</html>
