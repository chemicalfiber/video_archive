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
    // 跳转到编辑用户页面
    public void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User userById = service.findUserById(id);
        req.setAttribute("user",userById);
        req.getRequestDispatcher(req.getContextPath() + "/pages/admin/user_edit.jsp").forward(req,resp);
    }
    // 编辑用户权限
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 记得校验权限，只有管理员可以编辑其他用户！
        boolean flag = false;   // 用来检验有没有必要修改数据库的flag
        int id = Integer.parseInt(req.getParameter("id"));
        String isResetPassword = req.getParameter("isResetPassword");
        int grant = Integer.parseInt(req.getParameter(Const.GRANT));
        String nickname = req.getParameter(Const.NICKNAME);
//        System.out.println(grant);
        System.out.println("管理员请求更改id为【" + id + "】的用户");
        User userById = service.findUserById(id);
        if (!userById.getU_nick_name().equals(nickname)){
            // 如果前端传过来的nickname有变化，就更新用户
            userById.setU_nick_name(nickname);
            flag = true;
        }
        if (userById.getU_grant() != grant){
            // 如果提交过来的权限位标识有变化，就更新用户
            userById.setU_grant(grant);
            flag = true;
        }
        if (isResetPassword != null){
            // 如果isResetPassword参数不是null，则说明要重置密码
            userById.setU_password("123456");
            flag = true;
        }
        if (flag){
            System.out.println("有需要变动的项");
            int rows = service.updateUser(userById);
            if (rows < 1){
                System.err.println("更新用户失败！");
            }
        }
        resp.sendRedirect(req.getContextPath() + "/admin/user?action=getByPage");
    }
    // 删除用户
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 记得校验权限，只有管理员可以删除用户！
        int id = Integer.parseInt(req.getParameter("id"));
        int rows = service.deleteUser(id);
        if (rows < 1){
            System.err.println("删除id为" + id + "的用户失败！");
        }
        resp.sendRedirect(req.getContextPath() + "/pages/admin/user_list.jsp");
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

        page.setUrl("/admin/user?action=getByPage");

        // 保存Page对象到Request域中
        req.setAttribute("page",page);
        // 请求转发到pages/admin/video_list.jsp页面
        req.getRequestDispatcher(req.getContextPath()+"/pages/admin/user_list.jsp").forward(req,resp);
    }
}
