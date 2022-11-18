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
            top: -35px;
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
        <source src="http://192.168.1.10/chfs/shared/%E7%BA%A4%E7%BB%B4%E6%83%B3%E6%B3%95%E5%A4%9A/%E7%94%A8CPU%E6%9D%A5%E8%B7%91AI%E7%94%BB%E5%9B%BE/%E7%94%A8CPU%E6%9D%A5%E8%B7%91AI%E7%94%BB%E5%9B%BE.mp4"/>
        您的浏览器不支持 video 标签吗？尝试使用Google Chrome或者Mozilla Firefox打开此网页
    </video>
    <div class="videoInfo">
        <div class="videoTitle">【AI绘画】90%的电脑都能用!没显卡 用CPU跑AI绘画吧! 无需特殊网络 低硬件要求-NovelAI SDWebUI-纤维想法多</div>
        <div class="videoCreator" onclick="searchCreatorVideos()">
            ✍️作者：
            <br>
            <img src="/static/img/ChemicalFiber.jpeg" alt="化学纤维"/>
            <span class="videoCreatorNickname">化学纤维</span>
        </div>
        <div class="videoType">
            🌐分区：
            <br>
            <span>知识-野生技能协会</span>
        </div>
        <div class="videoPublicationDate">
            🗓️发布日期：2022-11-4
        </div>
        <div class="bili_Link">
            ⏩哔哩哔哩播放链接：
            <br>
            <a href="https://www.bilibili.com/video/BV1id4y1c7xM/" target="_blank">https://www.bilibili.com/video/BV1id4y1c7xM/</a>
        </div>
        <div class="yt_link">
            ⏩YouTube播放链接：
            <br>
            <a href="#">暂无</a>
        </div>
        <div class="videoIntroduction">
            视频简介：
            <br>
            <span>
                系列视频《纤维想法多》更新！想玩AI绘画却没有显卡？今天UP主为你带来仅使用CPU运行AI绘画框架的方法，让你随时随地都能画出你的小姐姐！
无需特殊网络，超低硬件需求，100%本地运行，独立部署，让AI完全属于你～
视频中用到的PDF文档：https://wwk.lanzoue.com/iTgSQ0f5j4kj
同时欢迎加入群聊：131370108（光圈科技试验场beta）和我们一起进行技术探讨！
            </span>
        </div>
    </div>
</div>
</body>
<script>
    function searchCreatorVideos() {
        location.href="${pageContext.request.contextPath}/video?action=searchByCreator&creatorNickname=" + "化学纤维";
    }
</script>
</html>
