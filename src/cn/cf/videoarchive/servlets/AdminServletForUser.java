package cn.cf.videoarchive.servlets;

import cn.cf.videoarchive.Const;
import cn.cf.videoarchive.pojo.Page;
import cn.cf.videoarchive.pojo.User;
import cn.cf.videoarchive.pojo.Video;
import cn.cf.videoarchive.service.UserService;
import cn.cf.videoarchive.service.impl.UserServiceImpl;
import cn.cf.videoarchive.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/user")
public class AdminServletForUser extends BaseServlet {
    private final UserService service = new UserServiceImpl();
    // 编辑用户
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 记得校验权限，只有管理员可以编辑其他用户！
        // TODO：管理员提升用户的权限为创作者
    }
    // 删除用户
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 记得校验权限，只有管理员可以删除用户！
        int id = Integer.parseInt(req.getParameter("id"));
        int rows = service.deleteUser(id);
        if (rows < 1){
            System.err.println("删除id为" + id + "的用户失败！");
        }
    }

    // 查询所有
    public void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 记得校验权限，只有管理员才可以查看所有用户！
//        User loginUser = (User) req.getSession().getAttribute(Const.LOGIN_USER);
//        if (loginUser != null && loginUser.getU_grant() == Const.ADMIN) {
            List<User> all = service.getAll();
            req.setAttribute("userList",all);
            req.getRequestDispatcher(req.getContextPath() + "/pages/admin/user_list.jsp").forward(req,resp);
//        } else {
//            req.setAttribute(Const.ERR_MSG, "您没有权限操作！");
//            req.getRequestDispatcher(req.getContextPath() + "/pages/error.jsp").forward(req, resp);
//        }
    }

    // 分页查询
    public void getByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("管理员页面请求用户分页");
        // 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 调用service中的分页方法
        Page<User> page = service.page(pageNo,pageSize);

        page.setUrl("/admin/user?action=page");

        // 保存Page对象到Request域中
        req.setAttribute("page",page);
        // 请求转发到pages/admin/video_list.jsp页面
        req.getRequestDispatcher(req.getContextPath()+"/pages/admin/user_list.jsp").forward(req,resp);
    }
}
