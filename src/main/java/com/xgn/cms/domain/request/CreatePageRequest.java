package com.xgn.cms.domain.request;

import lombok.Data;

@Data
public class CreatePageRequest {
    private String platform;
    private String projectId;
    private String pageName;
    private Integer versionCode;
    private String copyFromPageId;
}
