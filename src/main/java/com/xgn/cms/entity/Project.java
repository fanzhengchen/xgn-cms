package com.xgn.cms.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GenericGenerator(name = "projectId",strategy = "com.xgn.cms.generator.CmsIdGenerator")
    @GeneratedValue(generator = "projectId")
    private String projectId;

    private String projectName;

    private Date createTime;


    @PrePersist
    protected void onCreate(){
        createTime = new Date(System.currentTimeMillis());
    }

}