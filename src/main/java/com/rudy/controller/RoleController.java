package com.rudy.controller;

import com.alibaba.fastjson.JSON;
import com.rudy.entity.LayUITable;
import com.rudy.entity.RoleInfo;
import com.rudy.service.RoleService;
import com.rudy.util.LayuiTableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//角色控制器
@Controller
public class RoleController {
    @Autowired
    RoleService roleService;

    //动态加载角色下拉框数据的请求
    @RequestMapping("/roleSelect")
    @ResponseBody
    public Object roleSelect(){
        List<RoleInfo> roleInfos = roleService.selectAllRole();
        return roleInfos;
    }

    @RequestMapping("/insertRole")
    @ResponseBody
    public Object insertRole(String roleName, String roleKey, Integer sort, String status){
        RoleInfo role = roleService.selectAllRoleByName(roleName);
//        System.out.println(status);
        Map map = new HashMap();
        if (role != null){
            map.put("code",0);
            map.put("message","添加失败，该角色已存在！");
            return map;
        }
        char statusChar;
        if (!status.equals("on")){
            statusChar = '1';
        }
        else{
            statusChar = '0';
        }
//        System.out.println(statusChar);
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setRoleName(roleName);
        roleInfo.setRoleKey(roleKey);
        roleInfo.setRoleSort(sort);
        roleInfo.setStatus(statusChar);
        roleInfo.setDelFlag('0');
        int i = roleService.insertSelective(roleInfo);
        if (i <= 0){
            map.put("code",0);
            map.put("message","添加失败，数据库异常！ ");
            return map;
        }
        map.put("code",0);
        map.put("message","添加成功！");
        return map;
    }

    @RequestMapping("/updateRole")
    @ResponseBody
    public Object updateRole(Integer roleId, String roleName, String roleKey, Integer roleSort, String status){
        Map map = new HashMap();
        RoleInfo role = roleService.selectRoleById(roleId);
        if (role == null){
            map.put("code",0);
            map.put("message","编辑失败，该角色已失效！");
            return map;
        }
        RoleInfo roleWithName = roleService.selectAllRoleByName(roleName);
        if (roleWithName != null && roleWithName.getRoleId() != roleId){
            map.put("code",0);
            map.put("message","编辑失败，该角色存在！");
            return map;
        }
        char statusChar;
        if (!status.equals("on")){
            statusChar = '1';
        }
        else{
            statusChar = '0';
        }
//        RoleInfo roleWithId = new RoleInfo();
        role.setRoleId(roleId);
        role.setStatus(statusChar);
        role.setRoleName(roleName);
        role.setRoleSort(roleSort);
        role.setRoleKey(roleKey);
        int i = roleService.updateRoleSelective(role);
        if (i <= 0){
            map.put("code",0);
            map.put("message","编辑失败，数据库异常！ ");
            return map;
        }
        map.put("code",1);
        map.put("message","编辑成功！");
        return map;
    }

    //用户表格行工具栏中删除按钮点击事件的请求
    @RequestMapping("/toolDeleteRole")
    @ResponseBody
    public String toolDelete(Integer roleId){
        RoleInfo role = roleService.selectRoleById(roleId);
        if (role == null){
            return "删除失败，该用户ID不存在！";
        }
        //userService.deleteById(id);
        roleService.updateRoleDelFlagById(roleId);
        return "删除成功！";
    }

    //用户表头删除按钮点击事件的请求
    @RequestMapping("/toolbarDeleteRole")
    @ResponseBody
    public String toolbarDelete(@RequestParam(value = "list")String list){
        List<String> ids = (List<String>) JSON.parse(list);
        //int i = userService.deleteByIds(ids);
        int i = roleService.updateRoleDelFlagByIds(ids);
        if (i <= 0){
            return "删除失败";
        }
        return "删除成功！";
    }

    @RequestMapping("/roleTableInfo")
    @ResponseBody
    public LayUITable roleTableInfo(){
        List<RoleInfo> roles = roleService.selectAllRole();
        LayuiTableUtil layuiTableUtil = new LayuiTableUtil(0,"角色表格返回消息", roles.size(),roles);
        return layuiTableUtil.getLayUITable();
    }

    @RequestMapping("/roleTable")
    public String roleTable(){
        return "role/roleTable";
    }
}
