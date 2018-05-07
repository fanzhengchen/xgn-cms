package com.xgn.cms.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageCreateResponse {
    private String pageId;
    private String pageName;
    private String platform;
    private String pageStatus;
    private String pageInfo;
    private Integer minVersion;
}
