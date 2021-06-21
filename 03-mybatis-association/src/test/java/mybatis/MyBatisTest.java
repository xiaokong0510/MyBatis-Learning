package mybatis;

import com.xiao.mybatis.entity.Dept;
import com.xiao.mybatis.entity.Employee;
import com.xiao.mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author KongXiao
 * @date 2021/6/16
 */
public class MyBatisTest {

    // 提取的获得SqlSessionFactory对象的方法
    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //由SqlSessionFactoryBuilder对象获取SqlSessionFactory对象
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpAndDept(1);
            System.out.println(employee.getEmail());
        }
    }
}
