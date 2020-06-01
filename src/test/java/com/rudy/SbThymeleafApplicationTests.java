package com.rudy;

import com.rudy.dao.MyUserInfoMapper;
import com.rudy.entity.DeptInfo;
import com.rudy.entity.MyUserInfo;
import com.rudy.entity.RoleInfo;
import com.rudy.entity.TableInfo;
import com.rudy.service.DeptService;
import com.rudy.service.RoleService;
import com.rudy.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SbThymeleafApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    MyUserInfoMapper myUserInfoMapper;
    @Autowired
    RoleService roleService;
    @Autowired
    DeptService deptService;
    @Test
    void contextLoads() {
//        List<DeptInfo> deptInfos = deptService.selectAllDept();
//        System.out.println(deptInfos);
        DeptInfo deptInfo = deptService.selectDeptByName("研发部门");
        System.out.println(deptInfo);
    }

}
