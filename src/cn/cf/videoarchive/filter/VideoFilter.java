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

// TODO:完成视频访问过滤器
@WebFilter(urlPatterns = {"/pages/video/*","/video"})
public class VideoFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.err.println("正在请求：" + request.getRequestURI());
        User loginUser = (User) request.getSession().getAttribute(Const.LOGIN_USER);
        if (loginUser == null){
            request.setAttribute(Const.ERR_MSG,"请先登录！");
            request.getRequestDispatcher(request.getContextPath()+"/pages/user/login.jsp").forward(request,response);
            return;
        }
        chain.doFilter(request,response);
    }
}
