package com.xgn.cms.service.Impl;

import com.xgn.cms.TokenUtil;
import com.xgn.cms.domain.request.LoginRequest;
import com.xgn.cms.domain.response.BaseResponse;
import com.xgn.cms.domain.response.LoginData;
import com.xgn.cms.domain.response.UserData;
import com.xgn.cms.entity.User;

import com.xgn.cms.repository.UserRepository;
import com.xgn.cms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public BaseResponse login(@RequestBody LoginRequest request) {

        User user = userRepository.findUserByUserName(request.getUsername());
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
        UserData userData = UserData.builder()
                .username(user.getUserName())
                .projectId(user.getProject().getProjectId())
                .build();

        LoginData loginData = LoginData.builder()
                .token(token)
                .userData(userData)
                .build();

        return BaseResponse.ok(loginData);
    }
}
