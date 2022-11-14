package cn.cf.videoarchive.test.servicetest;

import cn.cf.videoarchive.Constants;
import cn.cf.videoarchive.pojo.User;
import cn.cf.videoarchive.service.UserService;
import cn.cf.videoarchive.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceTest {
private final UserService service = new UserServiceImpl();
    @Test
    public void addUser() {
        User user = new User(null, "ctts", "测试用户2", "zhimakaimen", null);
        int i = service.addUser(user);
        System.out.println(i);
    }

    @Test
    public void deleteUser() {
        int i = service.deleteUser(8);
        System.out.println(i);
    }

    @Test
    public void updateUser() {
        User user = new User(8, "ctts", "测试用户23333", "mimacuowu", Constants.ADMIN);
        int i = service.updateUser(user);
        System.out.println(i);
    }

    @Test
    public void login() {
//        User login = service.login(new User(null, "admin", null, "admin", null));
        User login = service.login(new User(null, "admin", null, "admin", null));
        if (login == null){
            System.out.println("登录失败！");
        }else {
            System.out.println("登录成功！");
            System.out.println(login);
        }
    }

    @Test
    public void existUsername() {
        boolean existUsername = service.existUsername("ChemicalFiber");
        if (existUsername){
            System.out.println("用户名已被占用！");
        }else {
            System.out.println("恭喜！用户名可用");
        }
    }

    @Test
    public void getAll() {
        System.out.println(service.getAll());
    }

    @Test
    public void countUser() {
        System.out.println(service.countUser());
    }
}