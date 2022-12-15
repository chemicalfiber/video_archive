package cn.cf.videoarchive.servlets;

import cn.cf.videoarchive.Const;
import cn.cf.videoarchive.pojo.User;
import cn.cf.videoarchive.service.UserService;
import cn.cf.videoarchive.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/user")
public class AdminServlet extends BaseServlet {
    private final UserService service = new UserServiceImpl();
    // 删除用户
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 记得校验权限，只有管理员可以删除用户！
    }

    // 查询所有
    public void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 记得校验权限，只有管理员才可以查看所有用户！
        User loginUser = (User) req.getSession().getAttribute(Const.LOGIN_USER);
        if (loginUser != null && loginUser.getU_grant() == Const.ADMIN) {
            List<User> all = service.getAll();
            req.setAttribute("userList",all);
            req.getRequestDispatcher("/pages/admin/user_list.jsp").forward(req,resp);
        } else {
            req.setAttribute(Const.ERR_MSG, "您没有权限操作！");
            req.getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }
    }

    // 分页查询
    public void getByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
