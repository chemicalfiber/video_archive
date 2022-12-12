package cn.cf.videoarchive.service;

import cn.cf.videoarchive.pojo.Page;
import cn.cf.videoarchive.pojo.Video;

import java.util.List;

public interface VideoService {
    // 添加视频
    int addVideo(Video video);
    // 删除视频
    int deleteVideo(Integer id);
    // 修改视频
    int updateVideo(Video video);
    // 通过ID查询视频
    Video getVideoById(Integer videoId);
    // 查询所有
    List<Video> getAll();
    // 查询总个数
    int countVideos();
    // 查询指定创作者的视频的个数
    int countVideoByCreatorId(Integer creatorId);
    // 查询指定标题的视频的个数
    int countVideoByTitle(String title);
    // 查询指定创作者的视频
    List<Video> getVideoByCreatorId(Integer creatorId);
    // 根据标题模糊查询
    List<Video> getVideoByTitle(String title);
    // 分页查询
    Page<Video> page(Integer begin, Integer pageSize);
    // 根据标题分页查询
    Page<Video> pageByTitle(String videoTitle, Integer begin, Integer pageSize);
    // 根据创作者分页查询
    Page<Video> pageByCreatorId(Integer creatorId, Integer begin, Integer pageSize);
}
