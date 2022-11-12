package cn.cf.videoarchive.test;

import cn.cf.videoarchive.dao.UserDao;
import cn.cf.videoarchive.dao.impl.UserDaoImpl;
import cn.cf.videoarchive.pojo.User;
import org.junit.Test;

public class UserDaoTest {
    UserDao dao = new UserDaoImpl();
    @Test
    public void testCountUser(){
        long i = dao.countUser();
        System.out.println(i);
    }
    @Test
    public void testAddUser(){
        User user = new User(null, "ceshi", "测试用户", "CESHIMIMA", null);
        int i = dao.addUser(user);
        System.out.println(i);
    }
    @Test
    public void testDeleteUser(){
        int i = dao.deleteUser(7);
        System.out.println(i);
    }
    @Test
    public void testUpdateUser(){
        User user = new User(6, "ceshi", "测试用户", "CESHIMIMA", 2);
        int i = dao.updateUser(user);
        System.out.println(i);
    }
    @Test
    public void testGetUserByUsernameAndPassword(){
        User chemicalFiber = dao.getUserByUsernameAndPassword("ChemicalFiber", "Servlet007?");
        System.out.println(chemicalFiber);
    }
    @Test
    public void testGetUserByUsername(){
        User lunarMeal = dao.getByUsername("LunarMeal");
        System.out.println(lunarMeal);
    }
    @Test
    public void testGetUserById(){
        User userById = dao.getUserById(1);
        System.out.println(userById);
    }
}
