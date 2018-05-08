package com.xgn.cms.domain.request;

import lombok.Data;

@Data
public class PageConfigListRequest {
    private String platform;
    private String pageType;
    private int pageSize;
    private int pageNo;
}
