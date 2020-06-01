package com.rudy.service;

import com.github.pagehelper.PageInfo;
import com.rudy.entity.RoleInfo;
import org.springframework.stereotype.Component;

import java.util.List;

public interface RoleService {
    public PageInfo<RoleInfo> selectRole(int page, int limit);
    public List<RoleInfo> selectAllRole();
    public RoleInfo selectAllRoleByName(String roleName);
    public int insertSelective(RoleInfo userInfo);
    public RoleInfo selectRoleById(Integer id);
    public int deleteById(Integer id);
    public int deleteByIds(List<String> ids);
    public int updateRoleSelective(RoleInfo roleInfo);
    public int updateRoleDelFlagById(Integer roleId);
    public int updateRoleDelFlagByIds(List<String> ids);
}
