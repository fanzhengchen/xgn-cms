package com.xgn.cms.controller;

import com.xgn.cms.domain.request.LoginRequest;
import com.xgn.cms.domain.response.BaseResponse;
import com.xgn.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public BaseResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}
