package com.xiao.mybatis.dynamic.mapper;




import com.xiao.mybatis.dynamic.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author KongXiao
 * @date 2021/6/18
 */
public interface EmployeeMapper {
    /**
     * 定义一个查询方法，传入的参数是一个对象
     * @param employee
     * @return
     */
    List<Employee> selectEmp(Employee employee);
    /**
     * 定义一个查询方法，传入的参数是一个对象
     * @param employee
     * @return
     */
    List<Employee> selectEmp2(Employee employee);

    /**
     * 定义一个查询方法，传入的参数是一个对象
     * @param employee
     * @return
     */
    List<Employee> selectEmp3(Employee employee);


    /**
     * 更新
     * @param employee
     * @return
     */
    int updateEmp(Employee employee);

    /**
     * 根据传入的id集合查询
     * @param ids
     * @return
     */
    List<Employee> selectEmpByList(@Param("ids") List<Integer> ids);


    /**
     *
     * @param employees
     */
    void insertEmp(@Param("emps") List<Employee> employees);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Employee selectEmpById(Integer id);
}
