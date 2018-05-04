package com.xgn.cms.controller;


import com.xgn.cms.domain.request.CreatePageRequest;
import com.xgn.cms.domain.request.EditPageRequest;
import com.xgn.cms.domain.request.ModifyPageStatusRequest;
import com.xgn.cms.domain.response.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/page")
public class PageController {


    @PostMapping("/creation")
    public BaseResponse createPage(@RequestBody CreatePageRequest request) {
        return BaseResponse.ok();
    }

    @PostMapping("/modification")
    public BaseResponse modifyPageStatus(@RequestBody ModifyPageStatusRequest request) {
        return BaseResponse.ok();
    }

    @PostMapping("/edition")
    public BaseResponse editPage(@RequestBody EditPageRequest request) {
        return BaseResponse.ok();
    }


}
