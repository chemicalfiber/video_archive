<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chemicalfiber
  Date: 2022/11/9
  Time: 10:53 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UP主视频存档系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <style>
        .content {
            background-color: azure;
        }

        .videoItem {
            float: left;
            margin-left: 50px;
            margin-top: 50px;
        }

        .cover > img {
            border-radius: 10px;
            width: 320px;
            height: 180px;
        }
        .cover:hover{
            cursor: pointer;
        }

        .videoInfo {
            margin-left: 10px;
        }
    </style>
</head>
<body>
<div class="title">
    <img src="${pageContext.request.contextPath}/static/img/logo.png" alt="logo"
         style="height: 80px;width: 80px;"/><span>UP主视频存档系统</span>
    <span class="userConsole">您好，请<a href="pages/user/login.jsp">登录</a>以观看/上传视频</span>
</div>
<div class="content">
    <div class="videoList">
        <C:forEach var="i" begin="1" end="10">
            <%--单个视频：封面+信息--%>
            <div class="videoItem">
                <div class="cover" onclick="watch(this)">
                    <div style="display:none;">${i}</div>
                    <img src="${pageContext.request.contextPath}/static/img/cover.png" alt="封面"/>
                </div>
                <div class="videoInfo">
                    <div class="author">作者：化学纤维</div>
                    <div class="publicationDate">发布时间：2022-11-4</div>
                </div>
            </div>
        </C:forEach>
    </div>
</div>
</body>
<script>
    function watch(element) {
        // console.log(element);  // 触发事件的对象本身
        // console.log(element.children);   // 触发事件的对象的子元素列表
        // console.log(element.children[0]);    // 第一个子元素
        console.log(element.children[0].innerText);
        location.href = "${pageContext.request.contextPath}/video?action=watch&v_id="+element.children[0].innerText;
    }
</script>
</html>
