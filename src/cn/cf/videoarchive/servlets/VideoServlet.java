package cn.cf.videoarchive.servlets;

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
    // 添加视频
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    // 删除视频

    // 修改视频

    // 查询所有

    // 分页查询

    // 根据创作者查询
    public void searchByCreator(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String creatorId = req.getParameter("creatorId");
        System.out.println(creatorId);
    }

    // 检视详情
    public void watch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String v_id = req.getParameter("v_id");
        System.out.println(v_id);
    }
}
