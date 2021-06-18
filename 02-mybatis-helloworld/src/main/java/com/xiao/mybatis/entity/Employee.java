package com.xiao.mybatis.entity;

import lombok.Data;

/**
 * @author KongXiao
 * @date 2021/6/16
 */
@Data
public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    private String gender;

    public Employee(Integer id, String lastName, String email, String gender) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }
}
