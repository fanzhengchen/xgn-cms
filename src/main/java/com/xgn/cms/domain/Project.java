package com.xgn.cms.domain;

import java.util.Date;

public class Project {
    private Integer projectId;

    private String prjectName;

    private Date createTime;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getPrjectName() {
        return prjectName;
    }

    public void setPrjectName(String prjectName) {
        this.prjectName = prjectName == null ? null : prjectName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}