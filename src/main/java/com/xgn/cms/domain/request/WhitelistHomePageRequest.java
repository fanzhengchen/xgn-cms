package com.xgn.cms.domain.request;


import lombok.Data;

@Data
public class WhitelistHomePageRequest {

    private String whiteCode;
    private String projectId;
    private Integer appVersion;
    private String pageId;
}
