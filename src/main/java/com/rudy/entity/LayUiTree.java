package com.rudy.entity;

import lombok.Data;

import java.util.List;

@Data
public class LayUiTree {

    private String title;
    private int id;
    private String field;
    private  boolean checked;
    private  boolean spread;
    private String url;
    private List<LayUiTree> children;

}
