package cn.cf.videoarchive.service.impl;

import cn.cf.videoarchive.dao.UserDao;
import cn.cf.videoarchive.dao.impl.UserDaoImpl;
import cn.cf.videoarchive.pojo.User;
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
}
