<%@ page import="cn.cf.videoarchive.Const" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chemicalfiber
  Date: 2022/11/9
  Time: 10:53 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getAttribute(Const.PAGE) == null) {
        request.getRequestDispatcher(request.getContextPath() + "/video?action=page").forward(request, response);
    }
%>
<html>
<head>
    <title>UP主视频存档系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <style>
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

        .cover:hover {
            cursor: pointer;
        }

        .videoInfo {
            margin-left: 10px;
        }

        .videoTitle {
            /*多余的文字用省略号代替*/
            width: 320px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
    </style>
</head>
<body>
<div class="title">
    <img src="${pageContext.request.contextPath}/static/img/logo.png" alt="logo"
         style="cursor:pointer;height: 80px;width: 80px;" onclick="location.href='${pageContext.request.contextPath}/'"/><span>UP主视频存档系统</span>
    <%--    <span class="userConsole">您好，请<a href="pages/user/login.jsp">登录</a>以观看/上传视频</span>--%>

    <%@include file="/pages/includes/user_console.jsp"%>
</div>
<div class="content">
    <div class="videoList">
        <c:forEach var="video" items="${requestScope.page.items}">
            <%--单个视频：封面+信息--%>
            <div class="videoItem">
                <div class="cover" onclick="watch(this)">
                    <div style="display:none;">${video.v_id}</div>
                        <%--                    <img src="${pageContext.request.contextPath}/static/img/cover.png" alt="封面"/>--%>
                    <img src="${video.v_thumbnail}" alt="封面"/>
                </div>
                <div class="videoInfo">
                    <div class="videoTitle">${video.v_title}</div>
                    <div class="author">作者：${video.u_nick_name}</div>
                    <div class="publicationDate">发布时间：${video.v_publication_date}</div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="/pages/includes/page_nav.jsp" %>
</body>
<script>
    function watch(element) {
        // console.log(element);  // 触发事件的对象本身
        // console.log(element.children);   // 触发事件的对象的子元素列表
        // console.log(element.children[0]);    // 第一个子元素
        console.log(element.children[0].innerText);
        location.href = "${pageContext.request.contextPath}/video?action=watch&v_id=" + element.children[0].innerText;
    }
</script>
</html>
