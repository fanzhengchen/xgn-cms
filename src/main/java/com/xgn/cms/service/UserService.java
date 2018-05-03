package com.xgn.cms.service;


import com.xgn.cms.domain.request.LoginRequest;
import com.xgn.cms.domain.response.BaseResponse;

public interface UserService {

    BaseResponse login(LoginRequest request);
}
