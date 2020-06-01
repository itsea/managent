package com.rudy.dao;

import com.rudy.entity.RoleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Integer id);

    List<RoleInfo> selectAllRole();

    int updateByPrimaryKeySelective(RoleInfo record);

    RoleInfo selectRoleByName(String roleName);

    int deleteByIds(@Param("ids") List<String> ids);

    int upateRoleDelFlagById(Integer roleId);

    int updateRoleDelFlagByIds(@Param("ids") List<String> ids);
}
