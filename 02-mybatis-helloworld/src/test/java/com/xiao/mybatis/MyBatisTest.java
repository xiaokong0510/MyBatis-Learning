package com.xiao.mybatis;

import com.xiao.mybatis.entity.Employee;
import com.xiao.mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author KongXiao
 * @date 2021/6/16
 */
public class MyBatisTest {
    @Test
    public void test() throws IOException {
        String resourceUrl = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resourceUrl);
        // SqlSessionFactoryBuilder对象获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 由SqlSession工厂获得SqlSession对象，使用其进行增删改查
        // 一个sqlSession就是和数据库的一次会话，使用完之后需要关闭资源
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            //sqlSession，直接执行已经映射的sql语句
            //selectOne()中两个参数：sql的唯一标识(对应sql映射文件中的namespace.id)和执行sql需要的参数
            Employee employee = sqlSession.selectOne("com.xiao.mybatis.mapper.EmployeeMapper.selectEmp", 1);
            System.out.println(employee);
        }
    }

    @Test
    public void testMapper() throws IOException {
        String resourceUrl = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resourceUrl);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            //获得接口文件的代理对象，执行方法
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
//            System.out.println(employeeMapper.selectEmp(1));
//            System.out.println(employeeMapper.selectAll());
//            System.out.println(employeeMapper.selectEmpByIdToMap(1));
//            System.out.println(employeeMapper.selectAllToMap());
            Employee employee = employeeMapper.selectEmpByIdAndName(1, "zhangsan");
            System.out.println(employee);

            HashMap<String, Object> map = new HashMap<>();
            map.put("id", 1);
            map.put("lastName", "zhangsan");
            System.out.println(employeeMapper.selectEmpByMap(map));
        }
    }

    // 提取的获得SqlSessionFactory对象的方法
    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //由SqlSessionFactoryBuilder对象获取SqlSessionFactory对象
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    // 测试增删改
    @Test
    public void testAdd() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 默认不自动提交事务，需要手动提交，或者构造方法传入true
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //   mapper.addEmp(new Employee(null,"林青霞","lingqingxia@163.com","1"));
        //   mapper.deleteEmpById(1);
            mapper.updateEmp( new Employee(5,"林青霞","lingqingxia@163.com","0"));
            // 手动提交事务
            sqlSession.commit();
        }
    }

}
