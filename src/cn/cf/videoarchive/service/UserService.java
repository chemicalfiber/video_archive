package cn.cf.videoarchive.service;

import cn.cf.videoarchive.pojo.Page;
import cn.cf.videoarchive.pojo.User;

import java.util.List;

public interface UserService {
    // 添加用户
    int addUser(User user);
    // 删除用户
    int deleteUser(Integer id);
    // 修改用户
    int updateUser(User user);
    // 登录
    User login(User user);
    // 检查用户名是否存在
    boolean existUsername(String username);
    // 查询所有
    List<User> getAll();
    // 查询总个数
    long countUser();
    // 分页查询用户
    Page<User> page(int pageNo, int pageSize);
}
