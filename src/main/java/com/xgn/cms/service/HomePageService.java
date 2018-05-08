package com.xgn.cms.service;

import com.xgn.cms.domain.request.NormalHomePageRequest;
import com.xgn.cms.domain.request.WhitelistHomePageRequest;
import com.xgn.cms.domain.response.BaseResponse;

public interface HomePageService {

    BaseResponse normalRequest(NormalHomePageRequest request);

    BaseResponse whitelistRequest(WhitelistHomePageRequest request);
}
