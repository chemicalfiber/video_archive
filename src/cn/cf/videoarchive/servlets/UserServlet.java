package cn.cf.videoarchive.servlets;

import cn.cf.videoarchive.Const;
import cn.cf.videoarchive.pojo.User;
import cn.cf.videoarchive.service.UserService;
import cn.cf.videoarchive.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户相关
 */
@WebServlet("/user")
public class UserServlet extends BaseServlet {
    private final UserService service = new UserServiceImpl();

    // 添加用户
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取Session中的验证码
        String verifyCode = (String) req.getSession().getAttribute(Const.VERIFY_CODE);
        // 让验证码失效，避免被重复使用
        req.getSession().removeAttribute(Const.VERIFY_CODE);
        // 获取必要参数
        String username = req.getParameter(Const.USERNAME);
        String nickname = req.getParameter(Const.NICKNAME);
        // 校验两个密码
        String password = req.getParameter("password");
        String passwordConfirm = req.getParameter("passwordConfirm");
        if (!passwordConfirm.equals(password)) {
            req.setAttribute(Const.ERR_MSG, "两次输入的密码不一致！");
            req.getRequestDispatcher(req.getContextPath() + "/pages/user/register.jsp").forward(req, resp);
            return;
        }
        // 检验验证码
        String code = req.getParameter("code");
        if (code != null && code.equalsIgnoreCase(verifyCode)) {    // 不区分大小写
            if (service.existUsername(username)) {   // 检验用户名是否存在
                req.setAttribute(Const.ERR_MSG, "用户名已被占用！");
                req.setAttribute(Const.USERNAME, username);
                req.setAttribute(Const.NICKNAME, nickname);
                req.getRequestDispatcher(req.getContextPath() + "/pages/user/register.jsp").forward(req, resp);
            } else {
                // 默认注册的用户权限是「观众」，所以User构造方法权限填写null
                service.addUser(new User(null, username, nickname, password, null));
                resp.sendRedirect(req.getContextPath() + "/pages/user/register_success.jsp");
            }
        } else {
            req.setAttribute(Const.ERR_MSG, "验证码错误！");
            req.getRequestDispatcher(req.getContextPath() + "/pages/user/register.jsp").forward(req, resp);
        }
    }

    // 校验用户名是否重复
    public void checkName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter(Const.USERNAME);
        if (service.existUsername(username)){
            resp.getWriter().println("用户名已被占用！");
        }
    }

    // 修改用户
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 记得校验权限，只有管理员或者账户拥有者才可以修改用户
        User loginUser = (User) req.getSession().getAttribute(Const.LOGIN_USER);
        if (loginUser != null && loginUser.getU_grant() == Const.ADMIN) {
            int id = Integer.parseInt(req.getParameter("id"));
            if (loginUser.getU_id() != id) { // 判断是不是账户拥有者
                req.setAttribute(Const.ERR_MSG, "您没有权限操作！");
                req.getRequestDispatcher("/pages/error.jsp").forward(req, resp);
                return;
            }
            // TODO：更新用户信息
            // 昵称
            // 密码
            // 头像
        } else {
            req.setAttribute(Const.ERR_MSG, "您没有权限操作！");
            req.getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }
    }

    // 登录
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter(Const.USERNAME);
        String password = req.getParameter("password");

        User loginUser = service.login(new User(null, username, null, password, null));
        if (loginUser == null) {
            req.setAttribute(Const.ERR_MSG, "用户名或密码错误！");
            req.setAttribute(Const.USERNAME, username);
            req.getRequestDispatcher(req.getContextPath() + "/pages/user/login.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute(Const.LOGIN_USER, loginUser);
            req.getRequestDispatcher(req.getContextPath() + "/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    // 注销
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
