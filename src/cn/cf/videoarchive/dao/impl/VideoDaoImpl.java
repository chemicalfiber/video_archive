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
        // language=MySQL
        String sql = "INSERT INTO video VALUES(null,?,?,?,?,?,?,CURDATE(),?)";
        return update(sql,video.getV_creator_id(),video.getV_title(),video.getV_introduction(),video.getV_type(),video.getV_thumbnail(),video.getV_play_link(), video.getV_bili_link());
    }

    /**
     * 删除视频
     * @param videoId 要删除的视频的ID
     * @return 影响的行数，如果是1就为成功
     */
    @Override
    public int delete(Integer videoId) {
        // language=MySQL
        String sql = "DELETE FROM video WHERE v_id=?";
        return update(sql,videoId);
    }

    /**
     * 修改视频
     * @param video 视频类实例对象
     * @return 影响的行数，如果是1就为成功
     */
    @Override
    public int edit(Video video) {
        // language=MySQL
        String sql = "update video SET v_creator_id=?,v_title=?,v_introduction=?,v_type=?,v_thumbnail=?,v_play_link=?,v_bili_link=? WHERE v_id=?";
        return update(sql,video.getV_creator_id(),video.getV_title(),video.getV_introduction(),video.getV_type(),video.getV_thumbnail(),video.getV_play_link(), video.getV_bili_link(),video.getV_id());
    }

    @Override
    public List<Video> getAll() {
        // language=MySQL
        String sql = "SELECT video.*,`user`.u_nick_name FROM video,`user` WHERE video.v_creator_id=`user`.u_id ORDER BY video.v_publication_date DESC";
        return getList(Video.class,sql);
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
    public Integer countVideo() {
        // language=MySQL
        String sql = "SELECT count(*) FROM video";
        Object value = getValue(sql);   // 此object是java.lang.Long类型
        return Integer.parseInt(value.toString());
    }

    /**
     * 查询指定创作者的视频总数
     * @param creatorId 创作者ID
     * @return 指定的创作者的视频总数
     */
    @Override
    public Integer countVideoByCreator(Integer creatorId) {
        // language=MySQL
        String sql = "SELECT count(*) FROM video WHERE v_creator_id=?";
        Object value = getValue(sql,creatorId);
        return Integer.parseInt(value.toString());
    }

    /**
     * 查询指定标题的视频总数
     * @param videoTitle 视频的标题
     * @return 指定的标题的视频总数
     */
    @Override
    public Integer countVideoByTitle(String videoTitle) {
        // language=MySQL
        String sql = "SELECT count(*) FROM video WHERE v_title LIKE ?";
        Object value = getValue(sql,"%"+videoTitle+"%");
        return Integer.parseInt(value.toString());
    }

    /**
     * 分页查询所有视频
     * @param begin 开始查找的索引号
     * @param pageSize 每页显示的项目个数
     * @return 包含多个视频类实例化对象的集合
     */
    @Override
    public List<Video> page(Integer begin, Integer pageSize) {
        // language=MySQL
        String sql = "SELECT video.*,`user`.u_nick_name FROM video,`user` WHERE video.v_creator_id=`user`.u_id ORDER BY video.v_publication_date DESC LIMIT ?,?";
        return getList(Video.class,sql,begin,pageSize);
    }

    /**
     * 根据视频标题模糊分页查询
     * @param videoTitle 视频的标题
     * @param begin 开始查找的索引号
     * @param pageSize 每页显示的项目个数
     * @return 包含多个视频类实例化对象的集合
     */
    @Override
    public List<Video> pageByTitle(String videoTitle, Integer begin, Integer pageSize) {
        // language=MySQL
        String sql = "SELECT video.*, `user`.u_nick_name FROM `user`,video WHERE `user`.u_id=video.v_creator_id AND video.v_title LIKE ? ORDER BY video.v_publication_date DESC LIMIT ?,?";
        return getList(Video.class,sql,"%"+videoTitle+"%",begin,pageSize);
    }

    /**
     * 根据创作者ID分页查询视频
     * @param creatorId 创作者ID
     * @param begin 开始查找的索引号
     * @param pageSize 每页显示的项目个数
     * @return 包含多个视频类实例化对象的集合
     */
    @Override
    public List<Video> pageByCreatorId(Integer creatorId, Integer begin, Integer pageSize) {
        // language=MySQL
        String sql = "SELECT video.*, `user`.u_nick_name FROM `user`,video WHERE `user`.u_id=video.v_creator_id AND video.v_creator_id=? ORDER BY video.v_publication_date DESC LIMIT ?,?";
        return getList(Video.class,sql,creatorId,begin,pageSize);
    }
}
