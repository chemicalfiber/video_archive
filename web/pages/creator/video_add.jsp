<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ChemicalFiber
  Date: 2022/12/17
  Time: 23:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加视频-创作者后台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <style>
        .inputBlock{
            float: left;
            right: -10%;
            top: 10%;
        }
        textarea{
            resize: none;
            width: 350px;
            height: 80px;
            margin-top: 5%;
            border: darkgrey solid 1px;
            border-radius: 7px;
            position: relative;
            top: 12px;
        }
    </style>
</head>
<body>
<div class="title">
    <img src="${pageContext.request.contextPath}/static/img/logo.png" alt="logo"
         style="cursor:pointer;height: 80px;width: 80px;"
         onclick="location.href='${pageContext.request.contextPath}/'"/><span>创作者后台-视频管理</span>
    <%--    <span class="userConsole">您好，请<a href="pages/user/login.jsp">登录</a>以观看/上传视频</span>--%>

    <%@include file="/pages/includes/user_console.jsp" %>
</div>
<div class="content">
    <div class="inputBlock">
        <span style="color: red" id="err_msg">${requestScope.err_msg}</span>
        <form action="${pageContext.request.contextPath}/video?action=add" method="post">
            <label><span>视频标题：</span>
                <input type="text" name="title" placeholder="输入视频标题" required minlength="4" maxlength="80">
            </label><br>
            <label><span>简介：</span>
                <textarea required name="introduction" maxlength="2000" placeholder="填写简介"></textarea>
            </label><br>
            <label><span>所属分区：</span>
                <input type="text" name="type" placeholder="在此处输入视频所属分区" required maxlength="16">
            </label><br>
            <label><span>封面直链地址：</span>
                <input type="text" name="thumbnail" placeholder="填入视频封面图床直链" required>
            </label><br>
            <label><span>视频可播放直链地址：</span>
                <input type="text" name="play_link" placeholder="填入视频播放直链" required>
            </label><br>
            <label><span>哔哩哔哩地址：</span>
                <input type="text" name="bili_link" placeholder="填入哔哩哔哩链接" required>
            </label><br>

            <input type="submit" value="添加视频" class="submit">
        </form>
    </div>
</div>
</body>
</html>
