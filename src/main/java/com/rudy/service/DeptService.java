package com.rudy.service;


import com.rudy.entity.DeptInfo;
import org.springframework.stereotype.Component;

import java.util.List;

public interface DeptService {
    List<DeptInfo> selectAllDept();
    DeptInfo selectDeptByName(String deptName);
    int delDeptByID(List<String> ids);

    int insertSelective(DeptInfo dept);

    int updateByPrimaryKeySelective(DeptInfo dept);
}
