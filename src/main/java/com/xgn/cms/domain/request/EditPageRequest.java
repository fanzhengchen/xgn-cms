package com.xgn.cms.domain.request;

import lombok.Data;

@Data
public class EditPageRequest {

    private String pageId;
    private String platform;
    private String pageInfo;
}
