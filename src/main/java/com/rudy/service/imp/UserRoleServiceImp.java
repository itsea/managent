package com.rudy.service.imp;

import com.rudy.dao.UserRoleInfoMapper;
import com.rudy.entity.UserRoleInfo;
import com.rudy.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImp implements UserRoleService {
    @Autowired
    UserRoleInfoMapper userRoleInfoMapper;
    @Override
    public int insertUserRole(UserRoleInfo record) {
        return userRoleInfoMapper.insert(record);
    }

    @Override
    public int updateUserRole(UserRoleInfo record) {
        return userRoleInfoMapper.updateUserRole(record);
    }
}
