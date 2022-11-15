<%--
  Created by IntelliJ IDEA.
  User: chemicalfiber
  Date: 2022/11/15
  Time: 9:11 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>错误</title>
</head>
<body>
${requestScope.err_msg}<br>
3秒后返回<a href="${pageContext.request.contextPath}/index.jsp">首页</a>
</body>
<script>
    window.onload = setTimeout(function (){
        location.href = "${pageContext.request.contextPath}/index.jsp";
    },3000);
</script>
</html>
