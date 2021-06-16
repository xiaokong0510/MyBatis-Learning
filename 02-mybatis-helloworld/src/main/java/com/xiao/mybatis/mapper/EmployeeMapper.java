package com.xiao.mybatis.mapper;

import com.xiao.mybatis.entity.Employee;

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
}
