package com.xiao.jdbc;


import com.xiao.jdbc.util.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JDBC工具类测试
 *
 * @author KongXiao
 * @date 2021/6/1
 */
public class JdbcUtilsTest {

    /**
     * 查询
     * @throws SQLException
     */
    @Test
    public void JDBCQueryTest() throws SQLException {
        Connection connection = JdbcUtils.getConnection();

        String sql = "select * from user_info where user_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "张三");
        ResultSet resultSet = preparedStatement.executeQuery();
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
        JdbcUtils.release(connection, preparedStatement, resultSet);
    }
}