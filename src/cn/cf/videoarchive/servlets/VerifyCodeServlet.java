package cn.cf.videoarchive.servlets;

import cn.cf.videoarchive.Const;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 验证验证码的Servlet
 * 关联文件：VerifyCode.java
 */
@WebServlet("/submitCode")
public class VerifyCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String verifyCode = (String) req.getSession().getAttribute(Const.VERIFY_CODE);
        // 使session中的验证码立即失效！
        req.getSession().removeAttribute(Const.VERIFY_CODE);
        // 为了保证验证码不区分大小写，所以将请求参数直接转大写处理
        String code = req.getParameter("code").toUpperCase();
        if (code.equals(verifyCode)) {
            resp.getWriter().println("success!");
        } else {
            resp.getWriter().println("failed!");
        }
    }
}
