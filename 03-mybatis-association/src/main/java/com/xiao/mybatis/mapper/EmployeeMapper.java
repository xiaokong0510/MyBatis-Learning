package com.xiao.mybatis.mapper;


import com.xiao.mybatis.entity.Employee;

/**
 * @author KongXiao
 * @date 2021/6/18
 */
public interface EmployeeMapper {
    /**
     * 根据id查询员工的信息及其部门信息
     * @param id 员工id
     * @return
     */
    Employee getEmpAndDept(Integer id);
}
