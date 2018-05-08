package com.xgn.cms.service;

import com.xgn.cms.domain.request.*;
import com.xgn.cms.domain.response.BaseResponse;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

public interface PageService {

    public BaseResponse modifyPageStatus(ModifyPageStatusRequest request);

    public BaseResponse createPage(CreatePageRequest request, HttpServletRequest req);

    public BaseResponse editPage(EditPageRequest request);

    public BaseResponse pageConfig(PageConfigRequest request);

    public BaseResponse pageConfigList(PageConfigListRequest request);
}
