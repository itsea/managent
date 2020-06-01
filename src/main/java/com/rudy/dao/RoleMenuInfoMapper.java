package com.rudy.dao;

import com.rudy.entity.RoleMenuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleMenuInfoMapper {
    int deleteByPrimaryKey(RoleMenuInfo key);

    int insert(RoleMenuInfo record);

    int insertSelective(RoleMenuInfo record);
}