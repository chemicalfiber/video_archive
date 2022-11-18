package cn.cf.videoarchive.dao;

import cn.cf.videoarchive.pojo.Video;

import java.util.List;

public interface VideoDao {
    // 增加
    int add(Video video);
    // 删除
    int delete(Integer videoId);
    // 修改
    int edit(Video video);
    // 通过标题搜索
    List<Video> searchByTitle(String videoTitle);
    // 通过创作者ID搜索
    List<Video> searchByCreatorId(Integer creatorId);
    // 通过视频ID获取信息
    Video getVideoById(Integer id);
    // 查询总个数
    Long countVideo();
    // 查询指定创作者的视频个数
    Long countVideoByCreator(Integer creatorId);
}
