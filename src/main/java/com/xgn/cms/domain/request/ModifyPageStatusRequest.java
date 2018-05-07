package com.xgn.cms.domain.request;

import lombok.Data;

@Data
public class ModifyPageStatusRequest {
    private String pageId;
    private String pageStatus;
}
