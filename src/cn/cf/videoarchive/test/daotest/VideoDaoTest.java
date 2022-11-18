package cn.cf.videoarchive.test.daotest;

import cn.cf.videoarchive.dao.VideoDao;
import cn.cf.videoarchive.dao.impl.VideoDaoImpl;
import cn.cf.videoarchive.pojo.Video;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class VideoDaoTest {
private final VideoDao dao = new VideoDaoImpl();
    @Test
    public void getVideoById() {
        Video videoById = dao.getVideoById(4);
        System.out.println(videoById);
    }

    @Test
    public void countVideo() {
        Long aLong = dao.countVideo();
        System.out.println(aLong);
    }

    @Test
    public void countVideoByCreator() {
        Long aLong = dao.countVideoByCreator(2);
        System.out.println(aLong);
    }

    @Test
    public void searchByCreatorId(){
        List<Video> videos = dao.searchByCreatorId(2);
        for (Video video : videos) {
            System.out.println(video);
        }
    }
    @Test
    public void searchByTitle(){
        List<Video> ai = dao.searchByTitle("骚操作");
        for (Video video : ai) {
            System.out.println(video);
        }
    }

}