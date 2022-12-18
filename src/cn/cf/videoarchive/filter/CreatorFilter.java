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

@WebFilter("/pages/creator/*")
public class CreatorFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.err.println("正在请求：" + request.getRequestURI());
        User loginUser = (User) request.getSession().getAttribute(Const.LOGIN_USER);
        if (loginUser == null){
            request.setAttribute(Const.ERR_MSG,"请先登录！");
            request.getRequestDispatcher(request.getContextPath()+"/pages/user/login.jsp").forward(request,response);
            return;
        }

        // 检查身份
        if (loginUser.getU_grant() != 2){
            System.err.println("当前用户身份是：" + loginUser.getG_name() + "，拒绝访问。");
            request.setAttribute(Const.ERR_MSG,"无权操作，您不是创作者！");
            request.getRequestDispatcher(request.getContextPath()+"/pages/user/login.jsp").forward(request,response);
            return;
        }
        System.err.println("权限确认：" + loginUser.getG_name() + "，允许访问。");
        chain.doFilter(request,response);
    }
}
