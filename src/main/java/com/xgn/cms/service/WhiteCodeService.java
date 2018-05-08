package com.xgn.cms.service;

import com.xgn.cms.domain.request.AddWhiteCodeRequest;
import com.xgn.cms.domain.request.RemoveWhiteCodeRequest;
import com.xgn.cms.domain.response.BaseResponse;

public interface WhiteCodeService {

    BaseResponse remove(RemoveWhiteCodeRequest request);

    BaseResponse all();

    BaseResponse add(AddWhiteCodeRequest request);
}
