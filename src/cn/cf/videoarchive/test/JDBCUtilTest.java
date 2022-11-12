package cn.cf.videoarchive.test;

import cn.cf.videoarchive.utils.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class JDBCUtilTest {
    @Test
    public void testUtils(){
        try {
            System.out.println(JDBCUtils.getConnection());
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
