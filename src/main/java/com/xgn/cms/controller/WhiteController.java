package com.xgn.cms.controller;

import com.xgn.cms.domain.response.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/whitecode")
public class WhiteController {


    @PostMapping("/removal")
    public BaseResponse remove(){
        return BaseResponse.ok();
    }

    @PostMapping("/all")
    public BaseResponse getAll(){
        return BaseResponse.ok();
    }

    @PostMapping("/addition")
    public BaseResponse add(){
        return BaseResponse.ok();
    }

}
