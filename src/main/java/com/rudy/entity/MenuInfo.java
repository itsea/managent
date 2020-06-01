package com.rudy.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

//菜单实体类
@Component
@Data
public class MenuInfo {
    private Integer menuId;

    private String menuName;

    private Integer parentId;

    private Integer orderNum;

    private String url;

    private String menuType;

    private String visible;

    private String perms;

    private String icon;
}