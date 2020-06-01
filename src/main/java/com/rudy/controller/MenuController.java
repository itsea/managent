package com.rudy.controller;


import com.alibaba.fastjson.JSON;


import com.rudy.entity.LayuitreeTable;
import com.rudy.entity.MenuInfo;
import com.rudy.service.imp.MenuBizImpl;
import com.rudy.util.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class MenuController {
    @Autowired
    private MenuBizImpl menuBiz;

    @RequestMapping("/menu")
    public String toShowMenu(){
        return "menu/showMenu";
    }



    @RequestMapping("/showMenu")
    @ResponseBody
    public LayuitreeTable showMenu() {
        //开始查询
        List<MenuInfo> menuInfoList = menuBiz.selectAllMenu();
        LayuitreeTable layuitreeTable =new LayuitreeTable();
        layuitreeTable.setCount(menuBiz.selectMenuNum());
        layuitreeTable.setCode(MyConstants.successCode);
        layuitreeTable.setMsg("查询成功");
        layuitreeTable.setData(menuInfoList);
        return layuitreeTable;
    }

    @RequestMapping("/toMenunum")
    //@ResponseBody
    public int  toMenunum() { return menuBiz.selectMenuNum();}



    @RequestMapping("/toAddMenu")
    @ResponseBody
    public Object toAddMenu(MenuInfo menuInfo) {

        Integer i = menuBiz.insertSelective(menuInfo);
        Map map = new HashMap();
        if (i > 0) {
            map.put("code", MyConstants.successCode);
            map.put("message", MyConstants.saveSuccessMsg);
        } else {
            map.put("code", MyConstants.failCode);
            map.put("message", MyConstants.saveFailMsg);
        }

        return map;
    }

    @RequestMapping("/toEditMenu")
    @ResponseBody
    public Object toEditMenu(MenuInfo menuInfo) {

        Integer i = menuBiz.updateByPrimaryKeySelective(menuInfo);
        Map map = new HashMap();
        if (i > 0) {
            map.put("code", MyConstants.successCode);
            map.put("message", MyConstants.editSuccessMsg);
        } else {
            map.put("code", MyConstants.failCode);
            map.put("message", MyConstants.editFailMsg);
        }

        return map;
    }

    @RequestMapping("/toDelMenu")
    @ResponseBody
    public Object toDelMenu(MenuInfo menuInfo) {

        Integer i = menuBiz.deleteByPrimaryKey(menuInfo.getMenuId());
        Map map = new HashMap();
        if (i > 0) {
            map.put("code", MyConstants.successCode);
            map.put("message", MyConstants.delSuccessMsg);
        } else {
            map.put("code", MyConstants.failCode);
            map.put("message", MyConstants.delFailMsg);
        }

        return map;
    }


    @RequestMapping("/toDelMenus")
    @ResponseBody
    public Object toDelMenus( @RequestParam(value = "ids") String  ids){
        //将json字符串转换成list对象
        List<String> list= (List<String>) JSON.parse(ids);
        int i = menuBiz.delMenusByID(list);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code",MyConstants.successCode);
            map.put("message",MyConstants.delSuccessMsg);
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message",MyConstants.delFailMsg);
        }
        return map;
    }


}
