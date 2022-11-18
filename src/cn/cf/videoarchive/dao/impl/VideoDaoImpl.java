package cn.cf.videoarchive.dao.impl;

import cn.cf.videoarchive.dao.VideoDao;
import cn.cf.videoarchive.pojo.Video;

import java.util.List;

public class VideoDaoImpl extends BaseDAO implements VideoDao {
    /**
     * 添加视频
     * @param video 视频类实例对象
     * @return 影响的行数，如果是1就为成功
     */
    @Override
    public int add(Video video) {
        return 0;
    }

    /**
     * 删除视频
     * @param videoId 要删除的视频的ID
     * @return 影响的行数，如果是1就为成功
     */
    @Override
    public int delete(Integer videoId) {
        return 0;
    }

    /**
     * 修改视频
     * @param video 视频类实例对象
     * @return 影响的行数，如果是1就为成功
     */
    @Override
    public int edit(Video video) {
        return 0;
    }

    /**
     * 通过标题搜索视频
     * @param videoTitle 视频标题
     * @return 包含多个视频类实例化对象的集合
     */
    @Override
    public List<Video> searchByTitle(String videoTitle) {
        // language=MySQL
        String sql = "SELECT video.*, `user`.u_nick_name FROM `user`,video WHERE `user`.u_id=video.v_creator_id AND video.v_title LIKE ?";
        return getList(Video.class,sql,"%"+videoTitle+"%");
    }

    /**
     * 通过创作者ID搜索视频
     * @param creatorId 创作者ID
     * @return 包含多个视频类实例化对象的集合
     */
    @Override
    public List<Video> searchByCreatorId(Integer creatorId) {
        // language=MySQL
        String sql = "SELECT video.*, `user`.u_nick_name FROM `user`,video WHERE `user`.u_id=video.v_creator_id AND video.v_creator_id=?";
        return getList(Video.class,sql,creatorId);
    }

    /**
     * 根据ID查询一个视频的所有信息
     * @param id 视频的ID
     * @return 视频类实体对象
     */
    @Override
    public Video getVideoById(Integer id) {
        // language=MySQL
        String sql = "SELECT video.*, `user`.u_nick_name FROM `user`,video WHERE `user`.u_id=video.v_creator_id AND video.v_id=?";
        return getOne(Video.class,sql,id);
    }

    /**
     * 统计视频总数
     * @return 视频总数
     */
    @Override
    public Long countVideo() {
        // language=MySQL
        String sql = "SELECT count(*) FROM video";
        Object value = getValue(sql);
        return (Long) value;
    }

    /**
     * 查询指定创作者的视频总数
     * @param creatorId 创作者ID
     * @return 指定的创作者的视频总数
     */
    @Override
    public Long countVideoByCreator(Integer creatorId) {
        // language=MySQL
        String sql = "SELECT count(*) FROM video WHERE v_creator_id=?";
        Object value = getValue(sql,creatorId);
        return (Long) value;
    }
}
