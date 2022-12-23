package cn.cf.videoarchive.servlets;

import cn.cf.videoarchive.Const;
import cn.cf.videoarchive.pojo.Page;
import cn.cf.videoarchive.pojo.User;
import cn.cf.videoarchive.pojo.Video;
import cn.cf.videoarchive.service.VideoService;
import cn.cf.videoarchive.service.impl.VideoServiceImpl;
import cn.cf.videoarchive.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 视频相关
 */
@WebServlet("/video")
public class VideoServlet extends BaseServlet{
    private final VideoService service = new VideoServiceImpl();
    // 添加视频
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute(Const.LOGIN_USER);
        if (loginUser.getU_grant() != Const.CREATOR){
            System.err.println("当前用户身份是：" + loginUser.getG_name() + "，拒绝访问。");
            req.setAttribute(Const.ERR_MSG,"您不是创作者，无权操作！");
            req.getRequestDispatcher(req.getContextPath() + "/pages/user/login.jsp").forward(req,resp);
            return;
        }
        String title = req.getParameter(Const.VIDEO_TITLE);
        String introduction = req.getParameter(Const.VIDEO_INTRODUCTION);
        String type = req.getParameter(Const.VIDEO_TYPE);
        String thumbnail = req.getParameter(Const.VIDEO_THUMBNAIL);
        String playLink = req.getParameter(Const.VIDEO_PLAY_LINK);
        String biliLink = req.getParameter(Const.VIDEO_BILI_LINK);

        Video video = new Video(null, loginUser.getU_id(), null, title, introduction, type, thumbnail, playLink, null, biliLink);
        int rows = service.addVideo(video);
        if (rows < 1){
            System.err.println("添加视频失败！");
        }
        resp.sendRedirect(req.getContextPath() + "/pages/creator/video_list.jsp");
    }
    // 删除视频
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 删除视频前，校验身份，只有自己才能删除自己的视频
        int v_id = Integer.parseInt(req.getParameter("id"));
        User loginUser = (User) req.getSession().getAttribute(Const.LOGIN_USER);
        Video video = service.getVideoById(v_id);
        // 检查视频所属的创作者是不是当前登录的用户
        if (!loginUser.getU_id().equals(video.getV_creator_id())){
            req.setAttribute(Const.ERR_MSG,"您无权操作！");
            req.getRequestDispatcher(req.getContextPath() + "/pages/user/login.jsp").forward(req,resp);
            return;
        }

        int rows = service.deleteVideo(v_id);
        if (rows < 1){
            System.err.println("删除视频失败！");
        }

        resp.sendRedirect(req.getContextPath() + "/pages/creator/video_list.jsp");
    }

    // 去修改视频页面
    public void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 更新视频前校验身份，只有自己才能修改自己的视频
        int v_id = Integer.parseInt(req.getParameter("id"));
        User loginUser = (User) req.getSession().getAttribute(Const.LOGIN_USER);
        Video video = service.getVideoById(v_id);
        // 检查视频所属的创作者是不是当前登录的用户
        if (!loginUser.getU_id().equals(video.getV_creator_id())){
            req.setAttribute(Const.ERR_MSG,"您无权操作！");
            req.getRequestDispatcher(req.getContextPath() + "/pages/user/login.jsp").forward(req,resp);
            return;
        }
        req.setAttribute(Const.VIDEO,video);
        req.getRequestDispatcher(req.getContextPath() + "/pages/creator/video_edit.jsp").forward(req,resp);
    }

    // 修改视频
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 更新视频信息前校验身份，只有自己才能修改自己的视频
        User loginUser = (User) req.getSession().getAttribute(Const.LOGIN_USER);
        if (loginUser.getU_grant() != Const.CREATOR){
            System.err.println("当前用户身份是：" + loginUser.getG_name() + "，拒绝访问。");
            req.setAttribute(Const.ERR_MSG,"您不是创作者，无权操作！");
            req.getRequestDispatcher(req.getContextPath() + "/pages/user/login.jsp").forward(req,resp);
            return;
        }
        // 通过前端传过来的ID查找要修改的视频
        int id = Integer.parseInt(req.getParameter("id"));
        Video video = service.getVideoById(id);
        if (video == null){
            System.err.println("找不到id为【" + id + "】的视频！");
            resp.sendRedirect(req.getContextPath() + "/pages/creator/video_list.jsp");
            return;
        }
        boolean flag = false;   // 用来检验有没有必要修改数据库的flag
        // 相关参数获取
        String title = req.getParameter(Const.VIDEO_TITLE);
        String introduction = req.getParameter(Const.VIDEO_INTRODUCTION);
        String type = req.getParameter(Const.VIDEO_TYPE);
        String thumbnail = req.getParameter(Const.VIDEO_THUMBNAIL);
        String playLink = req.getParameter(Const.VIDEO_PLAY_LINK);
        String biliLink = req.getParameter(Const.VIDEO_BILI_LINK);
        // 检查有没有必要修改
        if (!title.equals(video.getV_title())){
            flag = true;
            video.setV_title(title);
        }
        if (!introduction.equals(video.getV_introduction())){
            flag = true;
            video.setV_introduction(introduction);
        }
        if (!type.equals(video.getV_type())){
            flag = true;
            video.setV_type(type);
        }
        if (!thumbnail.equals(video.getV_thumbnail())){
            flag = true;
            video.setV_thumbnail(thumbnail);
        }
        if (!playLink.equals(video.getV_play_link())){
            flag = true;
            video.setV_play_link(playLink);
        }
        if (!biliLink.equals(video.getV_bili_link())){
            flag = true;
            video.setV_bili_link(biliLink);
        }
        // 检查修改flag
        if (flag){
            int rows = service.updateVideo(video);
            if (rows < 1){
                System.err.println("修改id为【" + id + "】的视频失败！");
            }
        }
        resp.sendRedirect(req.getContextPath() + "/pages/creator/video_list.jsp");
    }

    // 分页查询
    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("视频分页");
        // 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 调用service中的分页方法
        Page<Video> page = service.page(pageNo,pageSize);

        page.setUrl("/video?action=page");

        // 保存Page对象到Request域中
        req.setAttribute(Const.PAGE,page);
        // 请求转发到index.jsp页面
        req.getRequestDispatcher(req.getContextPath()+"/index.jsp").forward(req,resp);
    }

    // 根据创作者查询
    public void searchByCreator(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int creatorId = Integer.parseInt(req.getParameter("creatorId"));
        System.out.println("请求查询id为【" + creatorId + "】的创作者的所有视频");
        // 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 调用service中的分页方法
        Page<Video> page = service.pageByCreatorId(creatorId,pageNo,pageSize);

        page.setUrl("/video?action=searchByCreator&creatorId="+creatorId);

        // 保存Page对象到Request域中
        req.setAttribute(Const.PAGE,page);
        // 请求转发到创作者视频管理页面
        req.getRequestDispatcher(req.getContextPath()+"/index.jsp").forward(req,resp);
    }
    // 根据创作者分页查询视频
    public void pageByCreator(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int creatorId = Integer.parseInt(req.getParameter("creatorId"));
        System.out.println("请求分页查询id为【" + creatorId + "】的创作者的所有视频");
        // 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 调用service中的分页方法
        Page<Video> page = service.pageByCreatorId(creatorId,pageNo,pageSize);

        page.setUrl("/video?action=pageByCreator&creatorId="+creatorId);

        // 保存Page对象到Request域中
        req.setAttribute(Const.PAGE,page);
        // 请求转发到创作者视频管理页面
        req.getRequestDispatcher(req.getContextPath()+"/pages/creator/video_list.jsp").forward(req,resp);
    }

    // 检视详情
    public void watch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int v_id = Integer.parseInt(req.getParameter("v_id"));
        System.out.println("请求观看id为【" + v_id + "】的视频");
        Video video = service.getVideoById(v_id);
        req.setAttribute(Const.VIDEO,video);
        req.getRequestDispatcher("/pages/video/watch.jsp").forward(req,resp);
    }
}
