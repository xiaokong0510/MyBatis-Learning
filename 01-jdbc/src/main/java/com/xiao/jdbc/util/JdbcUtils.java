package com.xiao.jdbc.util;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


/**
 * JDBC工具类
 *
 * @author KongXiao
 * @date 2021/6/1
 */
public class JdbcUtils {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    // 读取属性文件，获取jdbc信息
    static {
        try {
            readConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取数据库配置文件
     *
     * @throws IOException
     */
    private static void readConfig() throws IOException {
        InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    /**
     * 获取数据库连接对象
     *
     * @return 数据库连接对象Connection
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // 1. 注册JDBC驱动
            Class.forName(driver);
            // 2. 获取数据库连接
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭结果集、数据库操作对象、数据库连接
     *
     * @param connection        数据库连接对象
     * @param preparedStatement 数据库操作对象
     * @param resultSet         结果集
     */
    public static void release(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
