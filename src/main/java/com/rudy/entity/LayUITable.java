package com.rudy.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//表格请求返回数据实体类
@Component
@Data
public class LayUITable {
    private int code;
    private String msg;
    private long count;
//    private List<MyUserInfo> data;
    private List<?> data;

}
