package com.xgn.cms.interceptor;

import com.xgn.cms.TokenUtil;
import com.xgn.cms.domain.response.BaseResponse;
import com.xgn.cms.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    private String KEY = "Authorization";
    private String loginUri = "/user/login";

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (loginUri.endsWith(request.getRequestURI())) {
            return true;
        }
        log.debug("request : {}", request.getRequestURI());
        String token = request.getHeader(KEY);
        if (token == null) {
            return false;
        }

        /**
         * 处理token失效的情况
         */
        try {
            String username = TokenUtil.getUsername(token);
            if (userRepository.findUserByUserName(username) == null) {
                handleTokenInvalid(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            handleTokenInvalid(response);
            return false;
        }

        /**
         * token无效
         */

        //log.debug("username on session :" + username);
        return true;
    }

    protected void handleTokenInvalid(HttpServletResponse response) {
        try {
            response.getWriter()
                    .print(BaseResponse.error("token not valid").toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
