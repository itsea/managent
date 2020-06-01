package com.rudy.dao;

import com.rudy.entity.MyUserInfo;
import com.rudy.entity.TableInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MyUserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyUserInfo record);

    int insertSelective(MyUserInfo record);

    MyUserInfo selectByPrimaryKey(Integer id);

    List<MyUserInfo> selectAllUser();

    int updateByPrimaryKeySelective(MyUserInfo record);

    int updateByPrimaryKey(MyUserInfo record);

    MyUserInfo selectUserByName(String loginName);

    int deleteByIds(@Param("ids") List<String> ids);

    List<TableInfo> selectUserTableInfos();

    int upateDelFlagById(Integer userId);

    int updateDelFlagByIds(@Param("ids") List<String> ids);

    Integer selectIdByName(String loginName);

    int updateUserPasswordById(Integer userId, String password);

    List<TableInfo> selectUserTableWithSearch(char status, String loginName, String phoneNumber, char delFlag);

    List<MyUserInfo> selectUserWithSearch(char status, String loginName, String phoneNumber);
}