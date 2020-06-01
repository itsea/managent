package com.rudy.service.imp;

import com.rudy.dao.DeptInfoMapper;
import com.rudy.entity.DeptInfo;
import com.rudy.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImp implements DeptService {
    @Autowired
    DeptInfoMapper deptInfoMapper;
    @Override
    public List<DeptInfo> selectAllDept() {
        return deptInfoMapper.selectAllDept();
    }

    @Override
    public DeptInfo selectDeptByName(String deptName) {
        return deptInfoMapper.selectDeptByName(deptName);
    }


    @Override
    public int delDeptByID(List<String> ids) {
        return deptInfoMapper.delDeptByID(ids);
    }


    @Override
    public int insertSelective(DeptInfo dept) {
        return deptInfoMapper.insertSelective(dept);
    }

    @Override
    public int updateByPrimaryKeySelective(DeptInfo dept)
    {
        return deptInfoMapper.updateByPrimaryKeySelective(dept);
    }
}
