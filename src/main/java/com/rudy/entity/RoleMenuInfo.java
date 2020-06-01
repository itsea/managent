package com.rudy.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

//角色菜单实体类
@Component
@Data
public class RoleMenuInfo {
    private Integer roleId;

    private Integer menuId;
}