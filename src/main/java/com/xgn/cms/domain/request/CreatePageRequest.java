package com.xgn.cms.domain.request;

import lombok.Data;

@Data
public class CreatePageRequest {
    private String platform;
    private String pageName;
    private Integer minVersion;
    private String copyFromPageId;
}
