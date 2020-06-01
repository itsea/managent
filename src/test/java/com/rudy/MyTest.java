package com.rudy;

import com.rudy.entity.RoleInfo;
import com.rudy.service.RoleService;
import com.rudy.service.imp.RoleServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyTest {
    RoleService roleService = new RoleServiceImp();

    @Test
    public void test(){
        List<RoleInfo> roles = roleService.selectAllRole();
        System.out.println(roles);
    }
}
