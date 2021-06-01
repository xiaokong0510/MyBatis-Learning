package com.xiao.jdbc.demo;

import java.sql.*;

/**
 * @author KongXiao
 * @date 2021/5/27
 */
public class JdbcDemo {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/demo?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2b8";
    static final String USERNAME = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // 1. 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 2. 获得数据库连接
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            // 3. 创建Statement\PreparedStatement对象
            statement = connection.createStatement();
            // 4. 执行SQL语句
            String sql;
            sql = "SELECT id, user_name, age,create_time FROM demo.user_info";
            resultSet = statement.executeQuery(sql);
            // 展开结果集数据库,如果有数据，resultSet.next()返回true
            while (resultSet.next()) {
                // 通过字段检索
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("user_name");
                int age = resultSet.getInt("age");
                String createTime = resultSet.getString("create_time");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 姓名: " + userName);
                System.out.print(", 年龄: " + age);
                System.out.print(", 创建时间: " + createTime);
                System.out.print("\n");
            }
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
