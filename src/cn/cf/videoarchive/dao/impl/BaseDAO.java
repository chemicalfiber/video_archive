package cn.cf.videoarchive.dao.impl;

import cn.cf.videoarchive.utils.JDBCUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * DAO: data(base) access object
 * 封装了针对于数据表的通用的操作
 * 因为这个是通用类，用于提供通用数据库操作方法，所以将其抽象，禁止建立其实例化对象
 */
public abstract class BaseDAO {
    /**
     * 通用的增删改操作
     * @param sql 要执行的SQL语句
     * @param args SQL语句中的参数
     * @return 影响的行数
     */
    public int update(String sql, Object... args) {    // args中参数的个数必须与sql中占位符的个数相等！否则boom
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            // 预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            // 为SQL语句装填参数
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);    // 占位符索引从1开始，但是args数组索引从0开始，这里要注意
            }
            // 执行
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 资源的关闭
            JDBCUtils.closeResource(conn, ps);
        }
        return 0;
    }

    /**
     * 通用的查询操作，用于返回数据表中的一条记录
     * @param clazz 要返回的实例对象的类型
     * @param sql 要执行的SQL语句
     * @param args SQL语句中的参数
     * @param <T> 要返回的实例对象的类型
     * @return clazz参数指定的类型的实例
     */
    public <T> T getOne(Class<T> clazz, String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            // 获取结果集的元数据 :ResultSetMetaData
            ResultSetMetaData metaData = rs.getMetaData();
            // 通过ResultSetMetaData获取结果集中的列数
            int columnCount = metaData.getColumnCount();

            if (rs.next()) {
                T t = clazz.getDeclaredConstructor().newInstance();
                // 处理结果集一行数据中的每一个列
                for (int i = 0; i < columnCount; i++) {
                    // 获取列值
                    Object columnValue = rs.getObject(i + 1);

                    // 获取每个列的列名
                    String columnLabel = metaData.getColumnLabel(i + 1);

                    // 给t对象指定的columnName属性，赋值为columnValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;
    }

    /**
     * 通用的查询操作，用于返回数据表中的多条记录构成的集合
     * @param clazz 要返回的实例对象的类型
     * @param sql 要执行的SQL语句
     * @param args SQL语句中的参数
     * @param <T> 要返回的实例对象的类型
     * @return clazz参数指定的实例的集合
     */
    public <T> List<T> getList(Class<T> clazz, String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            // 获取结果集的元数据 :ResultSetMetaData
            ResultSetMetaData metaData = rs.getMetaData();
            // 通过ResultSetMetaData获取结果集中的列数
            int columnCount = metaData.getColumnCount();
            // 创建集合对象
            ArrayList<T> list = new ArrayList<>();
            while (rs.next()) {
                T t = clazz.getDeclaredConstructor().newInstance();
                // 处理结果集一行数据中的每一个列:给t对象指定的属性赋值
                for (int i = 0; i < columnCount; i++) {
                    // 获取列值
                    Object columnValue = rs.getObject(i + 1);

                    // 获取每个列的列名
                    String columnLabel = metaData.getColumnLabel(i + 1);

                    // 给t对象指定的columnName属性，赋值为columnValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;
    }

    /**
     * 用于查询特殊值的通用的方法
     * @param sql 要执行的SQL语句
     * @param args SQL语句中的参数
     * @return 查询的值
     */
    public Object getValue(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getObject(1);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;
    }
}
