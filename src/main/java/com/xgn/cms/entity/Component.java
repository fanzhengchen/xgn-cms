package com.xgn.cms.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Component {
    private String componentId;

    private String componentName;

    private String type;

    private Date createTime;

    private String createBy;

    private String category;

    private String icon;

    private String label;

    private String title;

    private String placeholder;

    private String style;

}