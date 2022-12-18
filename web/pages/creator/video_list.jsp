<%@ page import="cn.cf.videoarchive.Const" %>
<%@ page import="cn.cf.videoarchive.pojo.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ChemicalFiber
  Date: 2022/12/17
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getAttribute(Const.PAGE) == null) {
        User loginUser = (User) session.getAttribute(Const.LOGIN_USER);
        request.getRequestDispatcher(request.getContextPath() + "/video?action=pageByCreator&creatorId=" + loginUser.getU_id()).forward(request, response);
    }
%>
<html>
<head>
    <title>视频列表-创作者后台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <style>
        .videoList_title {
            width: 192px;
        }

        .videoList_introduction {
            width: 384px;
        }

        .videoList_type {
            width: 70px;
        }

        .videoList_publicationDate {
            width: 120px;
        }

        .videoList_thumbnail {
            width: 370px;
        }

        .videoList_playLink {
            width: 288px;
        }

        .videoList_biliLink {
            width: 288px;
        }

        .videoList_operator {
            width: 120px;
        }

        th {
            text-align: center;
        }

        tr > td, th {
            display: inline-block;
            height: 60px;
            /*多余的文字用省略号代替*/
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }

        .tableContent:hover {
            background: aquamarine;
        }

        .addVideo{
            width: 100px;
            height: 50px;
        }
    </style>
</head>
<body>
<div class="title">
    <img src="${pageContext.request.contextPath}/static/img/logo.png" alt="logo"
         style="cursor:pointer;height: 80px;width: 80px;" onclick="location.href='${pageContext.request.contextPath}/'"/><span>创作者后台-视频管理</span>
    <%--    <span class="userConsole">您好，请<a href="pages/user/login.jsp">登录</a>以观看/上传视频</span>--%>

    <%@include file="/pages/includes/user_console.jsp" %>
</div>
<div class="content">
    <button class="addVideo" onclick="location.href='${pageContext.request.contextPath}/pages/creator/video_add.jsp'">添加视频</button>

    <table class="videoListTable" border="1" cellspacing="0">
        <tr>
            <th class="videoList_title">标题</th>
            <th class="videoList_introduction">简介</th>
            <th class="videoList_type">分区</th>
            <th class="videoList_publicationDate">发布时间</th>
            <th class="videoList_thumbnail">缩略图地址</th>
            <th class="videoList_playLink">视频直链</th>
            <th class="videoList_biliLink">哔哩哔哩播放地址</th>
            <th class="videoList_operator">操作</th>
        </tr>
        <c:forEach var="video" items="${requestScope.page.items}">
            <tr class="tableContent">
                <td class="videoList_title">${video.v_title}</td>
                <td class="videoList_introduction">${video.v_introduction}</td>
                <td class="videoList_type">${video.v_type}</td>
                <td class="videoList_publicationDate">${video.v_publication_date}</td>
                <td class="videoList_thumbnail">${video.v_thumbnail}</td>
                <td class="videoList_playLink">${video.v_play_link}</td>
                <td class="videoList_biliLink">${video.v_bili_link}</td>
                <td class="videoList_operator">
                    <button onclick="deleteVideo('${video.v_id}', '${video.v_title}')">删除</button>
                    <button onclick="location.href='${pageContext.request.contextPath}/video?action=toEdit&id=${video.v_id}'">编辑</button>
                    <button onclick="location.href='${pageContext.request.contextPath}/video?action=watch&v_id=${video.v_id}'">
                        观看
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="/pages/includes/page_nav.jsp" %>
</body>
<script>
    function deleteVideo(id, title) {
        // 当视频的标题超过20个字符就截短
        if (title.length > 20) {
            title = title.substr(0, 20) + "...";
        }
        let b = confirm("删除视频【" + title + "】吗？");
        if (b) {
            location.href = "${pageContext.request.contextPath}/video?action=delete&id=" + id;
        }
    }
</script>
</html>
