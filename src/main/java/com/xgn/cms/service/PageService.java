package com.xgn.cms.service;

import com.xgn.cms.domain.request.CreatePageRequest;
import com.xgn.cms.domain.request.EditPageRequest;
import com.xgn.cms.domain.request.ModifyPageStatusRequest;
import com.xgn.cms.domain.response.BaseResponse;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

public interface PageService {

    public BaseResponse modifyPageStatus(ModifyPageStatusRequest request);

    public BaseResponse createPage(CreatePageRequest request, HttpServletRequest req);

    public BaseResponse editPage(EditPageRequest request);
}
