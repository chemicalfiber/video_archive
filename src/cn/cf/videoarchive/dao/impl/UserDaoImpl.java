package cn.cf.videoarchive.dao.impl;

import cn.cf.videoarchive.Constants;
import cn.cf.videoarchive.dao.UserDao;
import cn.cf.videoarchive.pojo.User;

import java.util.List;

public class UserDaoImpl extends BaseDAO implements UserDao {
    /**
     * 新增一个用户
     * @param user 用户的实例化对象
     * @return 影响的行数，如果是1就为成功
     */
    @Override
    public int addUser(User user) {
        //language=MySQL
        String sql = "INSERT INTO `user` VALUES(null,?,?,?,?)";
        return update(sql, user.getU_name(), user.getU_nick_name(), user.getU_password(), Constants.AUDIENCE); // 默认新注册的用户是「观众」
    }

    /**
     * 删除一个用户
     * @param id 要删除的用户的ID
     * @return 影响的行数，如果是1就为成功
     */
    @Override
    public int deleteUser(Integer id) {
        //language=MySQL
        String sql = "DELETE FROM user WHERE u_id=?";
        return update(sql,id);
    }

    /**
     * 更新用户
     * @param user 用户的实例对象
     * @return 影响的行数，如果是1就为成功
     */
    @Override
    public int updateUser(User user) {
        //language=MySQL
        String sql = "UPDATE user set u_name=?,u_nick_name=?,u_password=?,u_grant=? WHERE u_id=?";
        return update(sql,user.getU_name(),user.getU_nick_name(),user.getU_password(),user.getU_grant(),user.getU_id());
    }

    /**
     * 根据用户名和密码查询用户
     * @param username 用户名
     * @param password 用户密码
     * @return 用户类实例对象
     */
    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        // language=MySQL
        String sql = "SELECT `user`.*, `grant`.g_name FROM `user`,`grant` WHERE u_name=? AND u_password=? AND user.u_grant=`grant`.g_id";
        return getOne(User.class,sql,username,password);
    }

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户类实例对象
     */
    @Override
    public User getByUsername(String username) {
        // language=MySQL
        String sql = "SELECT `user`.*, `grant`.g_name FROM `user`,`grant` WHERE u_name=? AND user.u_grant=`grant`.g_id";
        return getOne(User.class,sql,username);
    }

    /**
     * 查询所有用户
     * @return 包含多个用户类实例化对象的集合
     */
    @Override
    public List<User> getAll() {
        // language=MySQL
        String sql = "SELECT `user`.*, `grant`.g_name FROM `user`,`grant` where user.u_grant=`grant`.g_id";
        return getList(User.class,sql);
    }

    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户类实例对象
     */
    @Override
    public User getUserById(Integer id) {
        // language=MySQL
        String sql = "SELECT `user`.*, `grant`.g_name FROM `user`,`grant` WHERE u_id=? AND user.u_grant=`grant`.g_id";
        return getOne(User.class,sql,id);
    }

    /**
     * 统计用户个数
     * @return 用户总个数
     */
    @Override
    public Long countUser() {
        // language=MySQL
        String sql = "SELECT count(*) FROM user";
        Object value = getValue(sql);
        return (Long) value;
    }
}
