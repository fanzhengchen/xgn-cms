package com.xgn.cms.service;

import com.xgn.cms.domain.request.CreatePageRequest;
import com.xgn.cms.domain.request.EditPageRequest;
import com.xgn.cms.domain.request.ModifyPageStatusRequest;
import com.xgn.cms.domain.response.BaseResponse;

public interface PageService {


    public BaseResponse modifyPageStatus(ModifyPageStatusRequest request);

    public BaseResponse createPage(CreatePageRequest request);

    public BaseResponse editPage(EditPageRequest request);
}
