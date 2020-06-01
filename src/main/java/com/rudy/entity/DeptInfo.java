package com.rudy.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

//部门实体类
@Component
@Data
public class DeptInfo {
    private Integer deptId;
    private String deptName;
    private Integer orderNum;
    private char status;
    private char delFlag;
}
