package com.xgn.cms.service.Impl;

import com.xgn.cms.domain.request.CreatePageRequest;
import com.xgn.cms.domain.request.EditPageRequest;
import com.xgn.cms.domain.request.ModifyPageStatusRequest;
import com.xgn.cms.domain.response.BaseResponse;
import com.xgn.cms.repository.PageRepository;
import com.xgn.cms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;

public class PageServiceImpl implements PageService {

    @Autowired
    PageRepository pageRepository;


    @Override
    public BaseResponse modifyPageStatus(ModifyPageStatusRequest request) {
        
        return null;
    }

    @Override
    public BaseResponse createPage(CreatePageRequest request) {
        return null;
    }

    @Override
    public BaseResponse editPage(EditPageRequest request) {
        return null;
    }
}
