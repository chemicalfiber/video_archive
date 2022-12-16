package cn.cf.videoarchive.filter;

import cn.cf.videoarchive.Const;
import cn.cf.videoarchive.pojo.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/pages/admin/*"})
public class AdminFilter extends HttpFilter {
    // TODO：管理员相关过滤器

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.err.println("正在请求：" + request.getRequestURI());
        User loginUser = (User) request.getSession().getAttribute(Const.LOGIN_USER);
        // 检查用户是否登录
        if (loginUser == null){
            request.setAttribute(Const.ERR_MSG,"请先登录！");
            request.getRequestDispatcher(request.getContextPath()+"/pages/user/login.jsp").forward(request,response);
            return;
        }
        // 检查用户是不是管理员
        if (loginUser.getU_grant() != Const.ADMIN){
            request.setAttribute(Const.ERR_MSG,"您不是管理员，无权操作！");
            request.getRequestDispatcher(request.getContextPath()+"/pages/user/login.jsp").forward(request,response);
            return;
        }
        chain.doFilter(request,response);
    }
}
