package com.rudy.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

//用户实体类
@Component
@Data
public class MyUserInfo implements Serializable {//用到了shiro的记住我功能，此处要实现Serializable接口
    private Integer userId;
    private Integer deptId;
    private String loginName;
    private String userName;
    private String email;
    private String phoneNumber;
    private char sex;
    private String avatar;
    private String password;
    private String salt;
    private char status;
    private char delFlag;
    private Integer roleId;
}
