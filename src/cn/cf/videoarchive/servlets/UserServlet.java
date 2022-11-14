package cn.cf.videoarchive.servlets;

import cn.cf.videoarchive.Constants;
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
public class UserServlet extends BaseServlet{
    private final UserService service = new UserServiceImpl();
    // 添加用户
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取Session中的验证码
        String verifyCode = (String) req.getSession().getAttribute(Constants.VERIFY_CODE);
        // 让验证码失效，避免被重复使用
        req.getSession().removeAttribute(Constants.VERIFY_CODE);
        // 获取必要参数
        String username = req.getParameter("username");
        String nickname = req.getParameter("nickname");
        // 校验两个密码
        String password = req.getParameter("password");
        String passwordConfirm = req.getParameter("passwordConfirm");
        if (!passwordConfirm.equals(password)){
            req.setAttribute("msg","两次输入的密码不一致！");
            req.getRequestDispatcher(req.getContextPath()+"/pages/user/register.jsp").forward(req,resp);
            return;
        }
        // 检验验证码
        String code = req.getParameter("code");
        if (code != null && code.equalsIgnoreCase(verifyCode)){    // 不区分大小写
            if (service.existUsername(username)){   // 检验用户名是否存在
                req.setAttribute("msg","用户名已被占用！");
                req.setAttribute("username",username);
                req.setAttribute("nickname",nickname);
                req.getRequestDispatcher(req.getContextPath()+"/pages/user/register.jsp").forward(req,resp);
            }else {
                // 默认注册的用户权限是「观众」，所以User构造方法权限填写null
                int i = service.addUser(new User(null, username, nickname, password, null));
                resp.sendRedirect(req.getContextPath()+"/pages/user/register_success.jsp");
            }
        }else{
            req.setAttribute("msg","验证码错误！");
            req.getRequestDispatcher(req.getContextPath()+"/pages/user/register.jsp").forward(req,resp);
        }
    }
    // 删除用户
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 记得校验权限，只有管理员可以删除用户！
    }
    // 修改用户
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 记得校验权限，只有管理员或者账户拥有者才可以修改用户
    }
    // 查询所有
    public void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    // 分页查询
    public void getByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    // 登录
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User loginUser = service.login(new User(null, username, null, password, null));
        if (loginUser == null){
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",username);
            req.getRequestDispatcher(req.getContextPath()+"/pages/user/login.jsp").forward(req,resp);
        }else {
            HttpSession session = req.getSession();
            session.setAttribute("user",loginUser);
            req.getRequestDispatcher(req.getContextPath()+"/pages/user/login_success.jsp").forward(req,resp);
        }
    }
    // 注销
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath()+"/");
    }
}
