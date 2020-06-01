package com.rudy.controller;

import com.github.pagehelper.PageInfo;
import com.rudy.entity.*;
import com.rudy.service.*;
import com.rudy.service.imp.DeptServiceImp;
import com.rudy.util.LayuiTableUtil;
import com.rudy.util.ShiroEncryption;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.sql.Types.NULL;

//用户控制器
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private DeptServiceImp deptService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private MenuBiz menuBiz;

    //获取用户表格信息的请求，返回的是分页的表格数据
    @RequestMapping("/userTableInfo")
    @ResponseBody
    public LayUITable showUserLayui(int page, int limit){
//        PageInfo<MyUserInfo> pageInfo = userService.selectUser(page, limit);
        PageInfo<TableInfo> pageInfo = userService.selectUserTableInfos(page,limit);
        LayuiTableUtil layuiTableUtil = new LayuiTableUtil(0, "返回消息", pageInfo.getTotal(), pageInfo.getList());
        return layuiTableUtil.getLayUITable();
    }

    //登录页面请求
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "user/login";
    }

    //主页请求
    @RequestMapping({"/","/toIndex"})
    public String toIndex(Model model){
        Subject subject = SecurityUtils.getSubject();
        MyUserInfo userInfo = (MyUserInfo) subject.getPrincipal();
        if (userInfo == null){
            return "index";
        }
        model.addAttribute("loginName",userInfo.getLoginName());

        return "index";
    }

    //登录请求，此处是登录认证的处理
    @RequestMapping("/login")
    public String login(String loginName, String password, Model model, String remenber){
        //获取当前用户对象
        Subject subject = SecurityUtils.getSubject();

        //对密码进行加密处理，方便校验
        String pwd = ShiroEncryption.encryption("MD5",password,loginName,10);

        //把用户名和密码封装成一个token令牌
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, pwd);

        //捕捉登录异常
        try {
            if (remenber != null){
                token.setRememberMe(true);
            }
            //传入token执行登录操作
            subject.login(token);
            //如果成功返回index页面
            model.addAttribute("msg","登录成功！");
            model.addAttribute("loginName",loginName);

            return "index";
        } catch (UnknownAccountException e) {//捕捉用户名不存在异常
            //如果捕捉到用户名不存在异常返回以下msg，并返回到login页面
            model.addAttribute("msg","用户名不存在!");
            return "user/login";
        }
        catch (IncorrectCredentialsException e){//捕捉密码错误异常
            //如果捕捉到密码错误异常返回以下msg，并返回到login页面
            model.addAttribute("msg","密码不正确!");
            return "user/login";
        }
        catch (LockedAccountException e){
            model.addAttribute("msg","该用户已被限制！");
            return "user/login";
        }
        catch (AuthenticationException ae){
            model.addAttribute("msg","该用户已失效！");
            return "user/login";
        }

    }

    //注销请求
    @RequestMapping("/logout")
    public String logout(){
        //获取当前用户对象
        Subject subject = SecurityUtils.getSubject();
        //执行注销操作
        subject.logout();
        //最后返回到login页面
        return "user/login";
    }

    //添加用户请求
    @RequestMapping("/insertUser")
    @ResponseBody
    public Object insert(String loginName, String userName, String password, String roleName, String deptName,String status, String sex, String phoneNumber, String email){
        Map map = new HashMap();
        MyUserInfo user = userService.selectAllUserByName(loginName);
        String msg;
        if (user != null){
            msg = "插入失败，该用户名已存在！";
            map.put("message",msg);
            map.put("code",0);
            return map;
        }
        char statusChar = '0';
        if (status.equalsIgnoreCase("off")){
            statusChar = '1';
        }
        char sexChar = sex.charAt(0);
        MyUserInfo realUser = new MyUserInfo();
        //根据角色名查询角色
        RoleInfo role = roleService.selectAllRoleByName(roleName);
        DeptInfo dept = deptService.selectDeptByName(deptName);
        String salt = loginName;
        //给用户属性赋值
        realUser.setLoginName(loginName);
        realUser.setUserName(userName);
        realUser.setPassword(password);
        realUser.setPhoneNumber(phoneNumber);
        realUser.setEmail(email);
        realUser.setSex(sexChar);
        realUser.setStatus(statusChar);
        realUser.setSalt(salt);
        realUser.setRoleId(role.getRoleId());
        realUser.setDeptId(dept.getDeptId());
        realUser.setDelFlag('0');
        //System.out.println(realUser);
        UserRoleInfo userRoleInfo = new UserRoleInfo();
        //System.out.println(realUser.getSalt());
        //插入用户信息进用户表
        userService.insertSelective(realUser);
        //接着查询该用户的userId
        int realUserId = userService.selectIdByName(loginName);
        //给userRoleInfo对象赋值
        userRoleInfo.setRoleId(role.getRoleId());
        userRoleInfo.setUserId(realUserId);
        userRoleService.insertUserRole(userRoleInfo);
        msg = "插入成功";
        map.put("message",msg);
        map.put("code",1);
        return map;
    }

    //修改用户请求
    @RequestMapping("/updateUser")
    @ResponseBody
    public Object updateUser(Integer userId, String loginName, String userName, String password, String roleName, String deptName,String status, String sex, String phoneNumber, String email){

        Map map = new HashMap();
        MyUserInfo userWithId = userService.selectUserById(userId);
        MyUserInfo userWithName = userService.selectAllUserByName(loginName);
        if (userWithId == null){
            map.put("message","修改失败，该用户ID已不存在！");
            map.put("code",0);
            return map;
        }
        if (userWithName != null && userWithName.getUserId() != userId){
            map.put("message","修改失败，该用户名已存在！");
            map.put("code",0);
            return map;
        }
        userWithId.setUserName(userName);
        userWithId.setLoginName(loginName);
        userWithId.setPhoneNumber(phoneNumber);
        userWithId.setEmail(email);
        char statusChar;
        if (status.equalsIgnoreCase("on")){
            statusChar = '0';
        }
        else {
            statusChar = '1';
        }
        char sexChar = sex.charAt(0);
        userWithId.setSex(sexChar);
        userWithId.setStatus(statusChar);
        RoleInfo role = roleService.selectAllRoleByName(roleName);
        if (role == null){
            Integer fakeRoleId = NULL;
            userWithId.setRoleId(fakeRoleId);
        }
        else {
            userWithId.setRoleId(role.getRoleId());
        }
        DeptInfo dept = deptService.selectDeptByName(deptName);
        if (dept == null){
            Integer fakeDeptId = NULL;
            userWithId.setRoleId(fakeDeptId);
        }
        else {
            userWithId.setDeptId(dept.getDeptId());
        }
        int a = userService.updateByPrimaryKeySelective(userWithId);
        UserRoleInfo userRoleInfo = new UserRoleInfo();
        userRoleInfo.setUserId(userWithId.getUserId());
        userRoleInfo.setRoleId(userWithId.getRoleId());
        int b =userRoleService.updateUserRole(userRoleInfo);
        if (a <= 0 || b <= 0){
            map.put("message","数据库异常，修改失败！");
            map.put("code",0);
            return map;
        }
        map.put("message","修改成功！");
        map.put("code",1);
        return map;
    }

    //用户表格行工具栏中删除按钮点击事件的请求
    @RequestMapping("/toolDelete")
    @ResponseBody
    public String toolDelete(Integer userId){
        MyUserInfo user = userService.selectUserById(userId);
        if (user == null){
            return "删除失败，该用户ID不存在！";
        }
        //userService.deleteById(id);
        userService.updateDelFlagById(userId);
        return "删除成功！";
    }

    //用户表头删除按钮点击事件的请求
    @RequestMapping("/toolbarDelete")
    @ResponseBody
    public String toolbarDelete(@RequestParam(value = "list")String list){
        List<String> ids = (List<String>) JSON.parse(list);
        //int i = userService.deleteByIds(ids);
        int i = userService.updateDelFlagByIds(ids);
        if (i <= 0){
            return "删除失败";
        }
        return "删除成功！";
    }

    //重置密码按钮点击事件请求
    @RequestMapping("/resetPassword")
    @ResponseBody
    public Object resetPassword(int userId, String password){
        Map map = new HashMap();
        MyUserInfo user = userService.selectUserById(userId);
        if(user == null){
            map.put("code",0);
            map.put("message","重置失败，该用户ID的用户已不存在！");
            return map;
        }
        String salt = user.getSalt();
        String newPassword = ShiroEncryption.encryption("MD5",password,salt,10);
        System.out.println(userId+","+newPassword);
        int i = userService.updateUserPasswordById(userId,newPassword);
        if (i > 0){
            map.put("code",1);
            map.put("message","重置成功！");
            return map;
        }
        else{
            map.put("code",0);
            map.put("message","重置失败，数据库异常！");
            return map;
        }
    }

    //后台页面请求
    @RequestMapping("/backStageIndex")
    public String backStageIndex(Model model){
        Subject subject = SecurityUtils.getSubject();
        MyUserInfo userInfo = (MyUserInfo) subject.getPrincipal();
        model.addAttribute("loginName",userInfo.getLoginName());
        List<LayUiTree> menus = menuBiz.selectAllMenuByName(userInfo.getLoginName());
        model.addAttribute("menus",menus);
        return "/backStageIndex";
    }

    //纯用户表格页面请求
    @RequestMapping("/userTable")
    public String userTablePage(){
        return "user/userTable";
    }

    //用户表格查询请求
    @RequestMapping("/userTableSearch")
    @ResponseBody
    public Object userTableSearch(int page, int limit, String status, String loginName, String phoneNumber){
        System.out.println(page+" "+limit+" "+status+" "+loginName+" "+phoneNumber);
        char statusChar;
        if (status != ""){
            statusChar = status.charAt(0);
        }
        else {
            statusChar = ' ';
        }
        PageInfo<TableInfo> pageInfo = userService.selectUserTableWithSearchInfos(page,limit,statusChar,loginName,phoneNumber,'0');
        LayuiTableUtil layuiTableUtil = new LayuiTableUtil(0, "返回消息", pageInfo.getTotal(), pageInfo.getList());
        return layuiTableUtil.getLayUITable();
    }

}
