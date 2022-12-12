<%--
  Created by IntelliJ IDEA.
  User: chemicalfiber
  Date: 2022/11/15
  Time: 9:49 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>视频播放页面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <style>
        .videoInfo {
            float: right;
            margin-right: 20px;
            width: 550px;
            font-size: 15px;
        }

        .videoInfo > div > span, a {
            margin-left: 20px;
        }

        .videoInfo > div {
            margin-bottom: 20px;
        }

        .videoTitle {
            font-size: 30px;
        }

        .videoCreator:hover{
            cursor: pointer;
        }
        .videoCreator img {
            width: 100px;
            height: 100px;
            border-radius: 50px;
        }

        .videoCreatorNickname {
            position: relative;
            font-size: 30px;
        }
    </style>
</head>
<body>
<div class="title">
    <img src="${pageContext.request.contextPath}/static/img/logo.png" alt="logo"
         style="height: 80px;width: 80px;"/><span>UP主视频存档系统</span>
    <span class="userConsole">您好，请<a
            href="${pageContext.request.contextPath}/pages/user/login.jsp">登录</a>以观看/上传视频</span>
</div>
<div class="content">
    <video controls>
        <source src="${requestScope.video.v_play_link}"/>
        您的浏览器不支持 video 标签吗？尝试使用Google Chrome或者Mozilla Firefox打开此网页
    </video>
    <div class="videoInfo">
        <div class="videoTitle">${requestScope.video.v_title}</div>
        <div class="videoCreator" onclick="searchCreatorVideos()">
            ✍️作者：
            <br>
            <span class="videoCreatorNickname">${requestScope.video.u_nick_name}</span>
        </div>
        <div class="videoType">
            🌐分区：
            <br>
            <span>${requestScope.video.v_type}</span>
        </div>
        <div class="videoPublicationDate">
            🗓️发布日期：${requestScope.video.v_publication_date}
        </div>
        <div class="bili_Link">
            ⏩哔哩哔哩播放链接：
            <br>
            <a href="${requestScope.video.v_bili_link}" target="_blank">${requestScope.video.v_bili_link}</a>
        </div>
        <div class="videoIntroduction">
            视频简介：
            <br>
            <span>
                ${requestScope.video.v_introduction}
            </span>
        </div>
    </div>
</div>
</body>
<script>
    function searchCreatorVideos() {
        location.href="${pageContext.request.contextPath}/video?action=searchByCreator&creatorId=" + ${requestScope.video.v_creator_id};
    }
</script>
</html>
