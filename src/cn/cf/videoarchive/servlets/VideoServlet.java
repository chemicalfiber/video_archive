package cn.cf.videoarchive.servlets;

import cn.cf.videoarchive.pojo.Video;
import cn.cf.videoarchive.service.VideoService;
import cn.cf.videoarchive.service.impl.VideoServiceImpl;

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

    }
    // 删除视频
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    // 修改视频
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    // 分页查询
    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    // 根据创作者查询
    public void searchByCreator(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int creatorId = Integer.parseInt(req.getParameter("creatorId"));
        System.out.println("请求查询id为【" + creatorId + "】的创作者的所有视频");
    }

    // 检视详情
    public void watch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int v_id = Integer.parseInt(req.getParameter("v_id"));
        System.out.println("请求观看id为【" + v_id + "】的视频");
        Video video = service.getVideoById(v_id);
        req.setAttribute("video",video);
        req.getRequestDispatcher("/pages/video/watch.jsp").forward(req,resp);
    }
}
