package cn.cf.videoarchive.servlets;

import cn.cf.videoarchive.Const;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/*
 * 生成登录验证码
 * 关联文件：VerifyCodeServlet.java
 */
@WebServlet("/code.jpg")
public class VerifyCodeGenerator extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 200;
        int height = 100;
        // 创建一个对象 ，可以在内存中存创建一个图片 BufferedImage
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 美化图片
        Graphics g = image.getGraphics();//画笔
        g.setColor(Color.LIGHT_GRAY);//给画笔设置颜色
        // 填充背景色
        g.fillRect(0, 0, width, height);
        // 画边框
        g.setColor(Color.BLUE);
        g.drawRect(0, 0, width - 1, height - 1);
        // 写验证码
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random ran = new Random();
        StringBuilder sb = new StringBuilder();
        // 设置字体大小
        g.setFont(new Font("Times New Roman", Font.BOLD, height / 3));
        for (int i = 1; i <= 4; i++) {
            // 生成随机的下标
            int index = ran.nextInt(str.length());
            char ch = str.charAt(index);
            sb.append(ch);
            // 写验证码
            g.drawString(ch + "", width / 5 * i, height / 2);
        }
        req.getSession().setAttribute(Const.VERIFY_CODE, sb.toString());
        // g.drawString("a", 25, 35);
        // g.drawString("b", 30, 35);
        // g.drawString("D", 45, 35);
        // g.drawString("8", 50, 35);
        // 画干扰线
        for (int i = 0; i < 8; i++) {
            // 每一条干扰线的颜色都不一样
            g.setColor(new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255)));
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);
            int y1 = ran.nextInt(height);
            int y2 = ran.nextInt(height);
            // 画线
            g.drawLine(x1, y1, x2, y2);
        }
        // 把图片显示到客户端
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }
}
