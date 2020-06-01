package com.rudy.service.imp;

import com.github.pagehelper.PageInfo;
import com.rudy.dao.RoleInfoMapper;
import com.rudy.entity.RoleInfo;
import com.rudy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    RoleInfoMapper roleInfoMapper;

    @Override
    public PageInfo<RoleInfo> selectRole(int page, int limit) {
        return null;
    }

    @Override
    public List<RoleInfo> selectAllRole() {
        return roleInfoMapper.selectAllRole();
    }

    @Override
    public RoleInfo selectAllRoleByName(String roleName) {
        return roleInfoMapper.selectRoleByName(roleName);
    }

    @Override
    public int insertSelective(RoleInfo record) {
        return roleInfoMapper.insertSelective(record);
    }

    @Override
    public RoleInfo selectRoleById(Integer id) {
        return roleInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteById(Integer id) {
        return roleInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByIds(List<String> ids) {
        return roleInfoMapper.deleteByIds(ids);
    }

    @Override
    public int updateRoleSelective(RoleInfo roleInfo) {
        return roleInfoMapper.updateByPrimaryKeySelective(roleInfo);
    }

    @Override
    public int updateRoleDelFlagById(Integer roleId) {
        return roleInfoMapper.upateRoleDelFlagById(roleId);
    }

    @Override
    public int updateRoleDelFlagByIds(List<String> ids) {
        return roleInfoMapper.updateRoleDelFlagByIds(ids);
    }
}
