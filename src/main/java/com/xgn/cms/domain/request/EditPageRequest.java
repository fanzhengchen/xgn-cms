package com.xgn.cms.domain.request;

import lombok.Data;

import java.util.List;

@Data
public class EditPageRequest {

    private String pageId;
    private String pageInfo;
    private String pageStatus;
    private List<SpuItem> spuList;
}
