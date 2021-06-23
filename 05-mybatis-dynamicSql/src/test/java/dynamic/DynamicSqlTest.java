package dynamic;

import com.xiao.mybatis.dynamic.entity.Employee;
import com.xiao.mybatis.dynamic.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author KongXiao
 * @date 2021/6/23
 */
public class DynamicSqlTest {

    // 提取的获得SqlSessionFactory对象的方法
    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //由SqlSessionFactoryBuilder对象获取SqlSessionFactory对象
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testIf() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(null,null,null,"1");
            List<Employee> list = mapper.selectEmp(employee);
            System.out.println(list);
        }
    }

    @Test
    public void testWhere() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(null,null,null,"1");
            List<Employee> list = mapper.selectEmp2(employee);
            System.out.println(list);
        }
    }

    @Test
    public void testUpdate() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(1,"lisi","lisi@163.com","0");
            System.out.println(mapper.updateEmp(employee));
        }
    }

    @Test
    public void testForeach() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            mapper.selectEmpByList(Arrays.asList(1, 2, 3));
        }
    }

    @Test
    public void testCache() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession1.getMapper(EmployeeMapper.class);
        Employee employee1 = mapper.selectEmpById(1);
        System.out.println(employee1);
        Employee employee2 = mapper.selectEmpById(1);
        System.out.println(employee2);
        System.out.println(employee1==employee2); //true
    }

    @Test
    public void testCache2() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee1 = mapper.selectEmpById(1);
        System.out.println(employee1);
        sqlSession.close();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);
        Employee employee2 = mapper2.selectEmpById(1);
        System.out.println(employee2);
        System.out.println(employee1 == employee2); //false
        sqlSession2.close();
    }
}
