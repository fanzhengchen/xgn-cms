package com.xgn.cms.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@Table(name = "cms_page")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CmsPage {
    public static enum PageStatus {
        DRAFT("DRAFT"), OFFLINE("OFFLINE"), ONLINE("ONLINE");
        private String name;

        private PageStatus(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static enum PageType {
        ACTIVITY("ACTIVITY"), HOME("HOME");
        private String name;
        private PageType(String name) {
            this.name = name;
        }
    }

    @Id
    @GeneratedValue(generator = "pageId")
    @GenericGenerator(name = "pageId", strategy = "com.xgn.cms.generator.CmsIdGenerator")
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

    private String projectId;

    @PrePersist
    protected void onCreate() {
        createTime = new Date(System.currentTimeMillis());
    }
}