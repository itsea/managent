package com.rudy.dao;



import com.rudy.entity.MenuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuInfoMapper {
    int deleteByPrimaryKey(Integer menuId);


    int insert(MenuInfo record);

    int insertSelective(MenuInfo record);

    MenuInfo selectByPrimaryKey(Integer menuId);
    List<MenuInfo> selectAllMenu();
    int selectMenuNum();
    List<MenuInfo>  selectAllMenuByName(String loginName);
    MenuInfo selectMenuByStr(String str);
    List<String> seleMenu();


    int updateByPrimaryKeySelective(MenuInfo record);

    int updateByPrimaryKey(MenuInfo record);

    int deleteByPrimaryKey(int menuid);
    int delMenuByID(@Param("ids") List<String> ids);
}