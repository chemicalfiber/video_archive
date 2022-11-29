package cn.cf.videoarchive.test.servicetest;

import cn.cf.videoarchive.pojo.Video;
import cn.cf.videoarchive.service.VideoService;
import cn.cf.videoarchive.service.impl.VideoServiceImpl;
import org.junit.Test;

import java.sql.Date;

public class VideoServiceTest {
    private final VideoService service = new VideoServiceImpl();
    @Test
    public void addVideo() {
        Video video = new Video(null, 3, null, "测试一下啊", "测试视频的简介", "测试", "测试封面", "测试链接", new Date(new java.util.Date().getTime()), "");
        int i = service.addVideo(video);
        System.out.println(i);
    }

    @Test
    public void deleteVideo() {
        int i = service.deleteVideo(34);
        System.out.println(i);
    }

    @Test
    public void updateVideo() {
        Video video = new Video(34, 3, null, "测试一下啊2", "测试视频的2简介", "测2试", "测2试封面", "测试22链接", new Date(new java.util.Date().getTime()), "new");
        int i = service.updateVideo(video);
        System.out.println(i);
    }

    @Test
    public void getAll() {
        for (Video video : service.getAll()) {
            System.out.println(video);
        }
    }

    @Test
    public void countVideos() {
        System.out.println(service.countVideos());
    }

    @Test
    public void countVideoByCreatorId() {
        System.out.println(service.countVideoByCreatorId(2));
    }

    @Test
    public void getVideoByCreatorId() {
        for (Video video : service.getVideoByCreatorId(2)) {
            System.out.println(video);
        }
    }

    @Test
    public void getVideoByTitle() {
        for (Video video : service.getVideoByTitle("骚操作")) {
            System.out.println(video);
        }
    }

    @Test
    public void page() {
        System.out.println(service.page(0, 5));
    }

    @Test
    public void pageByTitle() {
        System.out.println(service.pageByTitle("骚操作", 0, 5));
    }

    @Test
    public void pageByCreatorId() {
        System.out.println(service.pageByCreatorId(2, 0, 5));
    }
}