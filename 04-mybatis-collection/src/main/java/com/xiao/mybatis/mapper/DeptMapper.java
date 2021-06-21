package com.xiao.mybatis.mapper;

import com.xiao.mybatis.entity.Dept;

/**
 * @author KongXiao
 * @date 2021/6/21
 */
public interface DeptMapper {

    /**
     * 根据部门id查询部门信息及所有员工信息
     * @param id
     * @return
     */
    Dept selectDeptById(Integer id);
}
