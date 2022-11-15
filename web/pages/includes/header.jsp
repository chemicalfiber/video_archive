<%--
  Created by IntelliJ IDEA.
  User: chemicalfiber
  Date: 2022/11/13
  Time: 9:30 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";

    pageContext.setAttribute("basePath",basePath);
%>

<!--使用base标签固定相对路径跳转的结果-->
<base href="<%=basePath%>">