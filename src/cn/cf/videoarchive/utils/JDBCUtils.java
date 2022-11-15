package cn.cf.videoarchive.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    /**
     * 获取数据库的连接
     *
     * @return Connection，数据库连接
     * @throws IOException            读取不到配置时抛出
     * @throws ClassNotFoundException 获取不到驱动类时抛出
     * @throws SQLException           获取不到连接时抛出
     */
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        // 读取配置文件中的4个基本信息（url、user、password、driverClass）
        // 需要写出配置文件的详细路径，从src目录开始
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("cn/cf/videoarchive/jdbc.properties");

        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        // 2.加载驱动
        Class.forName(driverClass);

        // 3.获取连接
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 关闭连接和Statement的操作
     *
     * @param conn 数据库连接
     * @param ps   由数据库连接生成的PrepareStatement对象
     */
    public static void closeResource(Connection conn, Statement ps) {
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭资源操作
     *
     * @param conn 数据库连接
     * @param ps   由数据库连接生成的PrepareStatement对象
     * @param rs   查询得到的结果集
     */
    public static void closeResource(Connection conn, Statement ps, ResultSet rs) {
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
