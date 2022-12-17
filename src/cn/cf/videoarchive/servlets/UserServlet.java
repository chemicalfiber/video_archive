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
        String password = req.getParameter(Const.PASSWORD);
        String passwordConfirm = req.getParameter(Const.PASSWORD_CONFIRM);
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
        if (service.existUsername(username)) {
            resp.getWriter().println("用户名已被占用！");
        }
    }

    // 修改用户
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 记得校验权限，只有管理员或者账户拥有者才可以修改用户
        boolean flag = false;   // 用来检验有没有必要修改数据库的flag
        User loginUser = (User) req.getSession().getAttribute(Const.LOGIN_USER);
        // 获取要修改的用户的id
        int id = Integer.parseInt(req.getParameter("id"));

        // 用户不能为空
        if (loginUser == null) {
            req.setAttribute(Const.ERR_MSG, "您没有权限操作！");
            req.getRequestDispatcher(req.getContextPath() + "/user/login.jsp").forward(req, resp);
            return;
        }

        // 判断是不是账户拥有者
        if (loginUser.getU_id() != id) {
            req.setAttribute(Const.ERR_MSG, "您没有权限操作！");
            req.getRequestDispatcher(req.getContextPath() + "/user/login.jsp").forward(req, resp);
            return;
        }

        // 获取相应信息
        String nickname = req.getParameter(Const.NICKNAME);
        String oldPassword = req.getParameter("oldPassword");
        String password = req.getParameter(Const.PASSWORD);
        String passwordConfirm = req.getParameter(Const.PASSWORD_CONFIRM);
        // 昵称
        if (!loginUser.getU_nick_name().equals(nickname)){
            // 如果登录的用户的昵称和提交的昵称不相等，就更新
            loginUser.setU_nick_name(nickname);
            flag = true;
        }
        // 密码
        if (!oldPassword.equals("") && !password.equals("") && !passwordConfirm.equals("")){
            // 检查提交的旧密码是否正确
            if (loginUser.getU_password().equals(oldPassword)){
                if (!password.equals(passwordConfirm)){
                    req.setAttribute(Const.ERR_MSG,"两次输入的密码不一致！");
                    req.getRequestDispatcher(req.getContextPath() + "/pages/user/edit.jsp").forward(req,resp);
                    return;
                }
                loginUser.setU_password(password);
                flag = true;
            }else{
                req.setAttribute(Const.ERR_MSG,"输入的旧密码不正确！");
                req.getRequestDispatcher(req.getContextPath() + "/pages/user/edit.jsp").forward(req,resp);
                return;
            }
        }

        if (flag){
            System.out.println("有需要变动的项");
            int rows = service.updateUser(loginUser);
            if (rows < 1){
                System.err.println("更新用户失败！");
            }
        }
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    // 登录
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter(Const.USERNAME);
        String password = req.getParameter(Const.PASSWORD);

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
