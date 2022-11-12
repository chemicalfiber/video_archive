package cn.cf.videoarchive.dao;

import cn.cf.videoarchive.pojo.User;

import java.util.List;

public interface UserDao {
    // 增加
    int addUser(User user);
    // 删除
    int deleteUser(Integer id);
    // 修改
    int updateUser(User user);
    // 通过用户名和密码查询
    User getUserByUsernameAndPassword(String username, String password);
    // 通过用户名查询
    User getByUsername(String name);
    // 查询所有
    List<User> getAll();
    // 查询一个
    User getUserById(Integer id);
    // 查询总个数
    Long countUser();
}
