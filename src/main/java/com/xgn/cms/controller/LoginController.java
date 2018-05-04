package com.xgn.cms.controller;

import com.xgn.cms.domain.request.LoginRequest;
import com.xgn.cms.domain.response.BaseResponse;
import com.xgn.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/login")
    public BaseResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}
