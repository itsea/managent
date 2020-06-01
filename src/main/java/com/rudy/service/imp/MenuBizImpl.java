package com.rudy.service.imp;


import com.rudy.dao.MenuInfoMapper;
import com.rudy.entity.LayUiTree;
import com.rudy.entity.MenuInfo;
import com.rudy.service.MenuBiz;
import com.rudy.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MenuBizImpl implements MenuBiz {
    @Autowired
    private MenuInfoMapper menuInfoMapper;


/*    @Override
    public PageInfo<MenuInfo> selectAllMenu(int page,int limit) {
        //开始分页,第一个参数是当前第几页，第二个参数是一页显示多少行
        PageHelper.startPage(page,limit);
        List<MenuInfo> menuInfos = menuInfoMapper.selectAllMenu();
        //结束分页,pageInfo封装了分页之后所有数据
        PageInfo<MenuInfo> pageInfo = new PageInfo(menuInfos);
        return pageInfo;

    }*/


    @Override
    public List<MenuInfo> selectAllMenu() {
        List<MenuInfo> menuInfoList = menuInfoMapper.selectAllMenu();
        return menuInfoList;
    }

/*    @Override
    public int deleteByPrimaryKey(Integer userid) {return menuInfoMapper.deleteByPrimaryKey(userid);}*/



    @Override
    public MenuInfo selectMenuByStr(String str) {
        MenuInfo myUserInfo=menuInfoMapper.selectMenuByStr(str);
        return null;
    }

    @Override
    public int selectMenuNum() {

        return menuInfoMapper.selectMenuNum();
    }

    @Override
    public List<String> seleMenu() {
        return menuInfoMapper.seleMenu();
    }

    @Override
    public int insertSelective(MenuInfo record) {

        return menuInfoMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(MenuInfo record) {
        return menuInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(int menuid) {
        return menuInfoMapper.deleteByPrimaryKey(menuid);
    }

    @Override
    public int delMenusByID(List<String> ids) {
        return menuInfoMapper.delMenuByID(ids);
    }

    @Override
    public List<LayUiTree> selectAllMenuByName(String loginName) {
        //查询所有的菜单
        List<MenuInfo> menus = menuInfoMapper.selectAllMenuByName(loginName);
        //并组装成tree格式的
        return TreeUtils.getChildPerms(menus, 0);
    }


}

