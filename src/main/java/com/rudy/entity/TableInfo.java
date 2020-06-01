package com.rudy.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

//用户表格实体类
@Component
@Data
public class TableInfo {
    private Integer userId;
    private String deptName;
    private String loginName;
    private String userName;
    private String email;
    private String phoneNumber;
    private char sex;
    private char status;
    private String roleName;
}
