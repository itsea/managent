package com.rudy.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class MenuTree {
    private String title;
    private Integer id;
    private String field;
    private boolean checked;
    private List<MenuTree> children;
}
