package com.xgn.cms.controller;


import com.xgn.cms.domain.request.NormalHomePageRequest;
import com.xgn.cms.domain.request.WhitelistHomePageRequest;
import com.xgn.cms.domain.response.BaseResponse;
import com.xgn.cms.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/homepage")
public class MainController {

    @Autowired
    HomePageService homePageService;

    @GetMapping("/test")
    public String test(){
        return "test 6666";
    }

    @PostMapping("/normal")
    public BaseResponse normalHomePage(@RequestBody  NormalHomePageRequest request){
        return homePageService.normalRequest(request);
    }

    @PostMapping("/whitelist")
    public BaseResponse whitelist(@RequestBody  WhitelistHomePageRequest request){
        return homePageService.whitelistRequest(request);
    }
}
