package com.xgn.cms.service;

import com.xgn.cms.TokenUtil;
import com.xgn.cms.domain.request.LoginRequest;
import com.xgn.cms.domain.response.BaseResponse;
import com.xgn.cms.domain.response.LoginData;
import com.xgn.cms.domain.response.UserData;
import com.xgn.cms.entity.User;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public BaseResponse login(@RequestBody LoginRequest request) {
        User user = null;//userMapper.selectByUserName(request.getUsername());
        if (user == null) {
            return BaseResponse.error("user not exits");
        }

        if (!ObjectUtils.nullSafeEquals(user.getPassword(),
                request.getPassword())) {
            return BaseResponse.error("password not correct");
        }
        /**
         * 登陆返回参数
         */

        String token = TokenUtil.createToken(request.getUsername());
        LoginData loginData = new LoginData();
        UserData userData = new UserData();
        userData.setUsername(user.getUserName());
        userData.setUserId(user.getUserId().toString());

        loginData.setToken(token);
        loginData.setUserData(userData);
        return BaseResponse.ok(loginData);
    }
}
