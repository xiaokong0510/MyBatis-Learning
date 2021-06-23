package com.xiao.mybatis.dynamic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author KongXiao
 * @date 2021/6/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    private static final long serialVersionUID = -7390587151857533202L;
    private Integer id;
    private String lastName;
    private String email;
    private String gender;

}
