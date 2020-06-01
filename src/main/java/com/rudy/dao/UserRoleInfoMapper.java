package com.rudy.dao;

import com.rudy.entity.UserRoleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRoleInfoMapper {
    int deleteByPrimaryKey(UserRoleInfo key);

    int insert(UserRoleInfo record);

    int insertSelective(UserRoleInfo record);

    int updateUserRole(UserRoleInfo record);
}