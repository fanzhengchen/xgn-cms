package com.xgn.cms.domain.response;

import com.xinguangnet.tuchao.goodscenter.api.response.ResponseSpuDetail;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class HomePageResponse {
    private boolean dev;
    private String pageInfo;
    private String pageId;
    private String pageName;
    private String pageStatus;
    private int minVersion;
    private ArrayList<ResponseSpuDetail> spuList;
}
