package com.xiao.mybatis.mapper;

import com.xiao.mybatis.entity.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author KongXiao
 * @date 2021/6/16
 */
public interface EmployeeMapper {
    /**
     * 根据id查询
     * @param id id
     * @return
     */
    Employee selectEmp(Integer id);

    /**
     * 查询所有,返回集合
     * @return
     */
    List<Employee> selectAll();

    /**
     * 返回一条记录的map，key是列名，value就是对应的值
     * @param id 用户id
     * @return
     */
    Map<String,Object> selectEmpByIdToMap(Integer id);

    /**
     * 返回多条记录封装到map中，key是主键值，value是JavaBean对象
     * 注解@MapKey；指定返回的map的key
     * @return
     */
    @MapKey("id")
    Map<Integer,Employee> selectAllToMap();

    /**
     * 传入两个参数查询
     * @param id 用户id
     * @param lastName lastName
     * @return
     */
    Employee selectEmpByIdAndName(@Param("id") int id, @Param("lastName") String lastName);

    /**
     *  封装传入参数为Map,查询
     * @param map
     * @return
     */
    Employee selectEmpByMap(Map<String,Object> map);

    /**
     *  新增
     * @param employee
     */
    void addEmp(Employee employee);

    /**
     * 根据id删除
     * @param id
     */
    void deleteEmpById(Integer id);

    /**
     * 修改
     * @param employee
     */
    void updateEmp(Employee employee);
}
