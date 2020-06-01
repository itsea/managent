package com.rudy.controller;


import com.alibaba.fastjson.JSON;
import com.rudy.entity.DeptInfo;
import com.rudy.entity.LayUITable;
import com.rudy.service.DeptService;
import com.rudy.util.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/system")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @RequestMapping("/dept")
    public String  toShowDept(){
        return "dept/showDept";
    }


    //动态加载部门下拉框数据的请求
    @RequestMapping("/deptSelect")
    @ResponseBody
    public Object selectAllDept(){
        return deptService.selectAllDept();
    }

    @RequestMapping("/showDept")
    @ResponseBody
    public Object showDept() {
        //开始查询
        List<DeptInfo> deptList = deptService.selectAllDept();
        LayUITable layUiTable = new LayUITable();
        layUiTable.setCode(0);
        layUiTable.setMsg("返回消息");
        layUiTable.setData(deptList);
        return layUiTable;
    }
/*        //开始查询
        List<Dept> deptList = deptService.selectAllDept();
        LayUiTable layUiTable = new LayUiTable();
        layUiTable.setCode(0);
        layUiTable.setMsg("返回消息");
        layUiTable.setData(deptList);
        return deptList;
    }*/

    @RequestMapping("/saveDept")
    @ResponseBody
    public Object saveDept(DeptInfo dept){
        int i = deptService.insertSelective(dept);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code", MyConstants.successCode);
            map.put("message", MyConstants.saveSuccessMsg);
        }else {
            map.put("code", MyConstants.failCode);
            map.put("message", MyConstants.saveFailMsg);
        }
        return map;
    }

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    @RequestMapping("/editDept")
    @ResponseBody
    public Object editDept(DeptInfo dept){
        int i = deptService.updateByPrimaryKeySelective(dept);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code", MyConstants.successCode);
            map.put("message", MyConstants.editSuccessMsg);
        }else {
            map.put("code", MyConstants.failCode);
            map.put("message", MyConstants.editFailMsg);
        }
        return map;
    }
    @RequestMapping("/delDept")
    @ResponseBody
    public Object delDept( @RequestParam(value = "ids") String  ids){
        //将json字符串转换成list对象
        List<String> list= (List<String>) JSON.parse(ids);
        int i = deptService.delDeptByID(list);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code", MyConstants.successCode);
            map.put("message", MyConstants.delSuccessMsg);
        }else {
            map.put("code", MyConstants.failCode);
            map.put("message", MyConstants.delFailMsg);
        }
        return map;
    }
}
