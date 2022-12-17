<%--
  Created by IntelliJ IDEA.
  User: ChemicalFiber
  Date: 2022/12/15
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<span class="userConsole">
        <%--如果用户还没有登录，显示     【登录 和注册的菜单】 --%>
				<c:if test="${empty sessionScope.login_user}">
                    您好，请
                    <a href="${pageContext.request.contextPath}/pages/user/login.jsp">登录</a> |
                    <a href="${pageContext.request.contextPath}/pages/user/register.jsp">注册</a>
                    以观看/上传视频
                </c:if>
				<%--如果已经登录，则显示 登录 成功之后的用户信息。--%>
				<c:if test="${not empty sessionScope.login_user}">
                    <span>您好，${sessionScope.login_user.u_nick_name}【${sessionScope.login_user.g_name}】</span>
                    <a href="${pageContext.request.contextPath}/pages/user/edit.jsp">修改个人信息</a> |
                    <c:if test="${sessionScope.login_user.u_grant == 2}">
                        <a href="${pageContext.request.contextPath}/pages/creator/video_list.jsp">视频管理</a>
                    </c:if>
                    <c:if test="${sessionScope.login_user.u_grant == 1}">
                        <a href="${pageContext.request.contextPath}/pages/admin/video_list.jsp">视频管理</a> |
                        <a href="${pageContext.request.contextPath}/pages/admin/user_list.jsp">用户管理</a>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/user?action=logout">登出</a>
                </c:if>
    </span>
