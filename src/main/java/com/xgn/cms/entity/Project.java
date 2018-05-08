package com.xgn.cms.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "project")
public class Project {

    public enum ProjectName {
        TU_BO_BO("兔波波", 1), JU_TONG("聚童", 2), GONG_YING_LINA("供应链", 3);
        private String name;
        private int platformId;

        private ProjectName(String name, int platformId) {
            this.name = name;
            this.platformId = platformId;

        }
    }

    @Id
    @GenericGenerator(name = "projectId", strategy = "com.xgn.cms.generator.CmsIdGenerator")
    @GeneratedValue(generator = "projectId")
    private String projectId;

    private String projectName;

    private Date createTime;

    private Integer platformId;


    @PrePersist
    protected void onCreate() {
        createTime = new Date(System.currentTimeMillis());
    }

}