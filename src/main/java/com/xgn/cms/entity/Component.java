package com.xgn.cms.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "component")
public class Component {

    @Id
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