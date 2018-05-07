package com.xgn.cms.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@Table(name = "page")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Page {

    @Id
    @GeneratedValue(generator = "pageId")
    @GenericGenerator(name = "pageId",strategy = "com.xgn.cms.generator.CmsIdGenerator")
    private String pageId;

    private String pageName;

    private String status;

    private Date createTime;

    private Integer minVersion;

    private String type;

    private String platform;

    private String editor;

    private String createBy;

    private String pageInfo;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

    @PrePersist
    protected void onCreate(){
        createTime = new Date(System.currentTimeMillis());
    }
}