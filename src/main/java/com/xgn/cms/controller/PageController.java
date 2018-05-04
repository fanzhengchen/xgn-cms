package com.xgn.cms.controller;


import com.xgn.cms.domain.request.CreatePageRequest;
import com.xgn.cms.domain.request.EditPageRequest;
import com.xgn.cms.domain.request.ModifyPageStatusRequest;
import com.xgn.cms.domain.response.BaseResponse;
import com.xgn.cms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/page")
public class PageController {

    @Resource
    PageService pageService;

    @PostMapping("/creation")
    public BaseResponse createPage(@RequestBody CreatePageRequest request, HttpServletRequest req) {
        return pageService.createPage(request, req);
    }

    @PostMapping("/modification")
    public BaseResponse modifyPageStatus(@RequestBody ModifyPageStatusRequest request) {
        return pageService.modifyPageStatus(request);
    }

    @PostMapping("/edition")
    public BaseResponse editPage(@RequestBody EditPageRequest request) {
        return pageService.editPage(request);
    }


}
