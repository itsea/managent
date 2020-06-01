package com.rudy.dao;


import com.rudy.entity.DeptInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
@Repository
public interface DeptInfoMapper {
    List<DeptInfo> selectAllDept();
    DeptInfo selectDeptByName(String deptName);

    int deleteByPrimaryKey(Integer deptId);

    int insert(DeptInfo record);

    int insertSelective(DeptInfo record);

    DeptInfo selectByPrimaryKey(Integer deptId);

    int updateByPrimaryKeySelective(DeptInfo record);

    int updateByPrimaryKey(DeptInfo record);


    //int delDeptByID(Object list);
    int delDeptByID(@Param("ids") List<String> ids);
}
