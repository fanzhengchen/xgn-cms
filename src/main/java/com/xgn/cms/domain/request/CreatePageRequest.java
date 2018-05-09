package com.xgn.cms.domain.request;

import lombok.Data;

@Data
public class CreatePageRequest {
    private String platform;
    private String pageName;
    private String pageType;
    private Integer minVersion;
    private String copyFromPageId;
}
