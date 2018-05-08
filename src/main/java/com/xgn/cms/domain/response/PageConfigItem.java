package com.xgn.cms.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageConfigItem {
    private String pageId;
    private String pageStatus;
    private String stamp;
    private int minVersion;
    private String editName;
    private String pageName;
}
