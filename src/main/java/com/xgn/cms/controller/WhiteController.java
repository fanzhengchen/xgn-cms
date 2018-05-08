package com.xgn.cms.controller;

import com.xgn.cms.domain.request.AddWhiteCodeRequest;
import com.xgn.cms.domain.request.RemoveWhiteCodeRequest;
import com.xgn.cms.domain.response.BaseResponse;
import com.xgn.cms.service.WhiteCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/whitecode")
public class WhiteController {

    @Autowired
    WhiteCodeService service;


    @PostMapping("/removal")
    public BaseResponse remove(@RequestBody  RemoveWhiteCodeRequest request) {
        return service.remove(request);
    }

    @PostMapping("/all")
    public BaseResponse getAll() {
        return service.all();
    }

    @PostMapping("/addition")
    public BaseResponse add(@RequestBody AddWhiteCodeRequest request) {
        return service.add(request);
    }

}
