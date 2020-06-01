package com.rudy.service;


import com.rudy.entity.LayUiTree;
import com.rudy.entity.MenuInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuBiz {

    //PageInfo<MenuInfo> selectAllMenu(int page, int limit);
    List<MenuInfo> selectAllMenu();
    MenuInfo selectMenuByStr(String str);
    int selectMenuNum();
    List<String> seleMenu();

  //  int deleteByPrimaryKey(Integer userid);

    int insertSelective(MenuInfo record);
    int updateByPrimaryKeySelective(MenuInfo record);
    int deleteByPrimaryKey(int menuid);

    int delMenusByID(@Param("ids") List<String> ids);
    List<LayUiTree>  selectAllMenuByName(String loginName);
}
