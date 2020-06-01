package com.rudy.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rudy.dao.MyUserInfoMapper;
import com.rudy.entity.MyUserInfo;
import com.rudy.entity.TableInfo;
import com.rudy.service.UserService;
import com.rudy.util.ShiroEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private MyUserInfoMapper myUserInfoMapper;
    @Override
    public PageInfo<MyUserInfo> selectUser(int page, int limit) {
        //开始分页
        PageHelper.startPage(page, limit);
        //开始查询
        List<MyUserInfo> userInfos = myUserInfoMapper.selectAllUser();
        //System.out.println(userInfos);
        //结束分页
        PageInfo<MyUserInfo> pageInfo = new PageInfo<>(userInfos);
        return pageInfo;
    }

    @Override
    public PageInfo<TableInfo> selectUserTableInfos(int page, int limit) {
        //开始分页
        PageHelper.startPage(page,limit);
        //开始查询
        List<TableInfo> tableInfos = myUserInfoMapper.selectUserTableInfos();
        //System.out.println(tableInfos);
        //结束分页
        PageInfo<TableInfo> pageInfo = new PageInfo<>(tableInfos);
        return pageInfo;
    }

    @Override
    public PageInfo<TableInfo> selectUserTableWithSearchInfos(int page, int limit, char status, String loginName, String phoneNumber, char delFlag) {
        //开始分页
        PageHelper.startPage(page,limit);
        //开始查询
        List<TableInfo> tableInfos = myUserInfoMapper.selectUserTableWithSearch(status,loginName,phoneNumber,delFlag);
        //System.out.println(tableInfos);
        //结束分页
        PageInfo<TableInfo> pageInfo = new PageInfo<>(tableInfos);
        return pageInfo;
    }

    @Override
    public List<MyUserInfo> selectAllUser() {
        return myUserInfoMapper.selectAllUser();
    }

    @Override
    public MyUserInfo selectAllUserByName(String loginName) {
        return myUserInfoMapper.selectUserByName(loginName);
    }

    @Override
    public int insertSelective(MyUserInfo userInfo) {
        MyUserInfo user = selectAllUserByName(userInfo.getUserName());
        if(user != null){
            return -1;
        }
        String password;
        String salt = userInfo.getLoginName();
        userInfo.setSalt(salt);
        password = ShiroEncryption.encryption("MD5",userInfo.getPassword(),salt,10);
        userInfo.setPassword(password);
        return myUserInfoMapper.insertSelective(userInfo);
    }

    @Override
    public MyUserInfo selectUserById(Integer id) {
        return myUserInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(MyUserInfo userInfo) {
        return myUserInfoMapper.updateByPrimaryKey(userInfo);
    }

    @Override
    public int deleteById(Integer id) {
        return myUserInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByIds(List<String> ids) {
        return myUserInfoMapper.deleteByIds(ids);
    }

    @Override
    public int updateDelFlagById(Integer userId) {
        return myUserInfoMapper.upateDelFlagById(userId);
    }

    @Override
    public int updateDelFlagByIds(List<String> ids) {
        return myUserInfoMapper.updateDelFlagByIds(ids);
    }

    @Override
    public Integer selectIdByName(String loginName) {
        return myUserInfoMapper.selectIdByName(loginName);
    }

    @Override
    public int updateByPrimaryKeySelective(MyUserInfo userInfo) {
        return myUserInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public int updateUserPasswordById(Integer userId, String password) {
        return myUserInfoMapper.updateUserPasswordById(userId,password);
    }

    @Override
    public List<MyUserInfo> selectUserWithSearch(char status, String loginName, String phoneNumber) {
        return myUserInfoMapper.selectUserWithSearch(status,loginName,phoneNumber);
    }

    @Override
    public List<TableInfo> selectUserTableWithSearch(char status, String loginName, String phoneNumber, char delFlag) {
        return myUserInfoMapper.selectUserTableWithSearch(status,loginName,phoneNumber,delFlag);
    }
}
