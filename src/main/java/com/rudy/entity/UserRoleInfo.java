package com.rudy.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

//用户角色实体类
@Component
@Data
public class UserRoleInfo {
    private Integer userId;

    private Integer roleId;
}