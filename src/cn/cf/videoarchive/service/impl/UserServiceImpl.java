package cn.cf.videoarchive.service.impl;

import cn.cf.videoarchive.dao.UserDao;
import cn.cf.videoarchive.dao.impl.UserDaoImpl;
import cn.cf.videoarchive.pojo.Page;
import cn.cf.videoarchive.pojo.User;
import cn.cf.videoarchive.pojo.Video;
import cn.cf.videoarchive.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao dao = new UserDaoImpl();
    @Override
    public int addUser(User user) {
        return dao.addUser(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return dao.deleteUser(id);
    }

    @Override
    public int updateUser(User user) {
        return dao.updateUser(user);
    }

    @Override
    public User login(User user) {
        return dao.getUserByUsernameAndPassword(user.getU_name(),user.getU_password());
    }

    @Override
    public boolean existUsername(String username) {
        return dao.getByUsername(username) != null; // 如果根据用户名没查到用户，说明用户名并未被占用
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public long countUser() {
        return dao.countUser();
    }

    @Override
    public Page<User> page(int pageNo, int pageSize) {
        Page<User> userPagePage = new Page<>();
        // 设置每一页显示项目个数
        userPagePage.setPageSize(pageSize);
        // 取得并设置项目总个数
        Integer pageTotalCount = dao.countUser();
        userPagePage.setPageTotalCount(pageTotalCount);
        // 求总页码
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {    // 当出现"溢出"的数据时，总页码+1
            pageTotal++;
        }
        // 设置总页码
        userPagePage.setPageTotal(pageTotal);
        // 设置当前页码
        userPagePage.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (userPagePage.getPageNo() - 1) * pageSize;
        // 取得当前页数据
        List<User> items = dao.page(begin, pageSize);
        // 设置当前页数据
        userPagePage.setItems(items);

        return userPagePage;
    }
}
