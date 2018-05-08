package com.xgn.cms.domain.request;


import lombok.Data;

@Data
public class NormalHomePageRequest {
    private Integer appVersion;
    private String projectId;
    private String whiteCode;
}
