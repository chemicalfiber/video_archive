<%@ page import="cn.cf.videoarchive.Const" %><%--
  Created by IntelliJ IDEA.
  User: chemicalfiber
  Date: 2022/11/15
  Time: 9:30 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    if (request.getAttribute(Const.PAGE) == null) {
        request.getRequestDispatcher(request.getContextPath() + "/admin/user?action=getByPage").forward(request, response);
    }
%>
<html>
<head>
    <title>用户管理-超级管理员后台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <style>
        .tableContent:hover {
            background: aquamarine;
        }

        tr > td, th {
            display: inline-block;
            height: 60px;
            /*多余的文字用省略号代替*/
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }

        .userList_id {
            width: 50px;
        }

        .userList_username {
            width: 200px;
        }

        .userList_nickname {
            width: 200px;
        }

        .userList_grant {
            width: 100px;
        }

        .userList_operation {
            width: 150px;
        }
    </style>
</head>
<body>
<div class="title">
    <img src="${pageContext.request.contextPath}/static/img/logo.png" alt="logo"
         style="cursor:pointer;height: 80px;width: 80px;"
         onclick="location.href='${pageContext.request.contextPath}/'"/><span>管理员后台-用户管理</span>
    <%--    <span class="userConsole">您好，请<a href="pages/user/login.jsp">登录</a>以观看/上传视频</span>--%>

    <%@include file="/pages/includes/user_console.jsp" %>
</div>
<div class="content">
    <div class="userList">
        <table border="1" cellspacing="0">
            <tr>
                <th class="userList_id">用户ID</th>
                <th class="userList_username">用户名</th>
                <th class="userList_nickname">用户昵称</th>
                <th class="userList_grant">角色身份</th>
                <th class="userList_operation">操作</th>
            </tr>
            <c:forEach var="user" items="${requestScope.page.items}">
                <tr class="tableContent">
                    <td class="userList_id">${user.u_id}</td>
                    <td class="userList_username">${user.u_name}</td>
                    <td class="userList_nickname">${user.u_nick_name}</td>
                    <td class="userList_grant">${user.g_name}</td>
                    <td class="userList_operation">
                        <button onclick="location.href='${pageContext.request.contextPath}/admin/user?action=toEdit&id=${user.u_id}'">编辑</button>
                        <button onclick="deleteUser('${user.u_id}', '${user.u_nick_name}')">删除用户</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<%@include file="/pages/includes/page_nav.jsp" %>
</body>
<script>
    function deleteUser(id, nickName) {
        let b = confirm("删除用户【" + nickName + "】吗？");
        if (b) {
            location.href = '${pageContext.request.contextPath}/admin/user?action=delete&id=' + id;
        }
    }
</script>
</html>
