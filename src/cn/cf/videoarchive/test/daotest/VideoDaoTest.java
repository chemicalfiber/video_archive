package cn.cf.videoarchive.test.daotest;

import cn.cf.videoarchive.dao.VideoDao;
import cn.cf.videoarchive.dao.impl.VideoDaoImpl;
import cn.cf.videoarchive.pojo.Video;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

public class VideoDaoTest {
private final VideoDao dao = new VideoDaoImpl();
    @Test
    public void getVideoById() {
        Video videoById = dao.getVideoById(4);
        System.out.println(videoById);
    }

    @Test
    public void countVideo() {
        Integer aLong = dao.countVideo();
        System.out.println(aLong);
    }

    @Test
    public void countVideoByCreator() {
        Integer aLong = dao.countVideoByCreator(2);
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

    @Test
    public void testAdd(){
        Video video = new Video(null, 2, "化学纤维", "ceshi", "ceshishipin", "ceshi", "ceshifengmian", "ceshilianjie", new Date(new java.util.Date().getTime()), "no");
        int add = dao.add(video);
        System.out.println(add);
    }

    @Test
    public void testDelete(){
        int delete = dao.delete(34);
        System.out.println(delete);
    }

    @Test
    public void testEdit(){
        Video video = new Video(33, 2, "化学纤维", "ceshi", "ceshishipin", "ceshi", "ceshifengmian", "ceshilianjie", new Date(new java.util.Date().getTime()), "no");
        int edit = dao.edit(video);
        System.out.println(edit);
    }

    @Test
    public void testGetAll(){
        for (Video video : dao.getAll()) {
            System.out.println(video);
            System.out.println("-------------------");
        }
    }

    @Test
    public void testPage(){
        for (Video video : dao.page(0, 5)) {
            System.out.println(video);
        }
    }

    @Test
    public void testPageByTitle(){
        for (Video video : dao.pageByTitle("骚操作", 0, 5)) {
            System.out.println(video);
        }
    }

    @Test
    public void testPageByCreatorId(){
        for (Video video : dao.pageByCreatorId(2, 0, 5)) {
            System.out.println(video);
        }
    }

    @Test
    public void testCountVideoByTitle(){
        System.out.println(dao.countVideoByTitle("骚操作"));
    }
}