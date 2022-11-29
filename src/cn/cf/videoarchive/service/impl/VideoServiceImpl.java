package cn.cf.videoarchive.service.impl;

import cn.cf.videoarchive.dao.VideoDao;
import cn.cf.videoarchive.dao.impl.VideoDaoImpl;
import cn.cf.videoarchive.pojo.Page;
import cn.cf.videoarchive.pojo.Video;
import cn.cf.videoarchive.service.VideoService;

import java.util.List;

public class VideoServiceImpl implements VideoService {
    private final VideoDao dao = new VideoDaoImpl();

    /**
     * 增加一个视频
     *
     * @param video 视频类实例对象
     * @return 影响的行数，如果是1就为成功
     */
    @Override
    public int addVideo(Video video) {
        return dao.add(video);
    }

    /**
     * 删除一个视频
     *
     * @param id 视频的ID
     * @return 影响的行数，如果是1就为成功
     */
    @Override
    public int deleteVideo(Integer id) {
        return dao.delete(id);
    }

    /**
     * 更新一个视频的信息
     *
     * @param video 视频类实例对象
     * @return 影响的行数，如果是1就为成功
     */
    @Override
    public int updateVideo(Video video) {
        return dao.edit(video);
    }

    /**
     * 获取所有视频
     *
     * @return 包含多个视频类实例化对象的集合
     */
    @Override
    public List<Video> getAll() {
        return dao.getAll();
    }

    /**
     * 统计视频总数
     *
     * @return 视频总数
     */
    @Override
    public long countVideos() {
        return dao.countVideo();
    }

    /**
     * 统计指定创作者的视频总数
     *
     * @param creatorId 创作者的ID
     * @return 指定创作者的视频总数
     */
    @Override
    public long countVideoByCreatorId(Integer creatorId) {
        return dao.countVideoByCreator(creatorId);
    }

    /**
     * 通过创作者ID搜索视频
     *
     * @param creatorId 创作者ID
     * @return 包含多个视频类实例化对象的集合
     */
    @Override
    public List<Video> getVideoByCreatorId(Integer creatorId) {
        return dao.searchByCreatorId(creatorId);
    }

    /**
     * 根据标题模糊查询视频
     *
     * @param title 标题
     * @return 包含多个视频类实例化对象的集合
     */
    @Override
    public List<Video> getVideoByTitle(String title) {
        return dao.searchByTitle(title);
    }

    /**
     * 分页查询所有视频
     *
     * @param pageNo   本页页码
     * @param pageSize 每页显示的项目个数
     * @return Page类实例化对象，Page.items内装的是包含多个Video类的实例化对象的集合
     */
    @Override
    public Page<Video> page(Integer pageNo, Integer pageSize) {
        Page<Video> videoPage = new Page<>();
        // 设置每一页显示项目个数
        videoPage.setPageSize(pageSize);
        // 取得并设置项目总个数
        Integer pageTotalCount = dao.countVideo();
        videoPage.setPageTotalCount(pageTotalCount);
        // 求总页码
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {    // 当出现"溢出"的数据时，总页码+1
            pageTotal++;
        }
        // 设置总页码
        videoPage.setPageTotal(pageTotal);
        // 设置当前页码
        videoPage.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (videoPage.getPageNo() - 1) * pageSize;
        // 取得当前页数据
        List<Video> items = dao.page(begin, pageSize);
        // 设置当前页数据
        videoPage.setItems(items);

        return videoPage;
    }

    /**
     * 根据视频标题模糊分页查询
     *
     * @param videoTitle 视频的标题
     * @param pageNo     本页页码
     * @param pageSize   每页显示的项目个数
     * @return 包含多个视频类实例化对象的集合
     */
    @Override
    public Page<Video> pageByTitle(String videoTitle, Integer pageNo, Integer pageSize) {
        Page<Video> videoPage = new Page<>();
        // 设置每一页显示项目个数
        videoPage.setPageSize(pageSize);
        // 取得并设置项目总个数
        Integer pageTotalCount = dao.countVideoByTitle(videoTitle);
        videoPage.setPageTotalCount(pageTotalCount);
        // 求总页码
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {    // 当出现"溢出"的数据时，总页码+1
            pageTotal++;
        }
        // 设置总页码
        videoPage.setPageTotal(pageTotal);
        // 设置当前页码
        videoPage.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (videoPage.getPageNo() - 1) * pageSize;
        // 取得当前页数据
        List<Video> items = dao.pageByTitle(videoTitle, begin, pageSize);
        // 设置当前页数据
        videoPage.setItems(items);

        return videoPage;
    }

    /**
     * 根据创作者ID分页查询视频
     *
     * @param creatorId 创作者ID
     * @param pageNo    本页页码
     * @param pageSize  每页显示的项目个数
     * @return 包含多个视频类实例化对象的集合
     */
    @Override
    public Page<Video> pageByCreatorId(Integer creatorId, Integer pageNo, Integer pageSize) {
        Page<Video> videoPage = new Page<>();
        // 设置每一页显示项目个数
        videoPage.setPageSize(pageSize);
        // 取得并设置项目总个数
        Integer pageTotalCount = dao.countVideoByCreator(creatorId);
        videoPage.setPageTotalCount(pageTotalCount);
        // 求总页码
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {    // 当出现"溢出"的数据时，总页码+1
            pageTotal++;
        }
        // 设置总页码
        videoPage.setPageTotal(pageTotal);
        // 设置当前页码
        videoPage.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (videoPage.getPageNo() - 1) * pageSize;
        // 取得当前页数据
        List<Video> items = dao.pageByCreatorId(creatorId,begin,pageSize);
        // 设置当前页数据
        videoPage.setItems(items);

        return videoPage;
    }
}
