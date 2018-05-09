package com.xgn.cms.interceptor;

import com.xgn.cms.TokenUtil;
import com.xgn.cms.domain.response.BaseResponse;
import com.xgn.cms.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    private String KEY = "Authorization";
    private String loginUri = "/user/login";
    private HashSet<String> noCheck = new HashSet<String>() {{
        add("/user/login");
        add("/homepage/normal");
        add("/homepage/whitelist");
    }};

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (noCheck.contains(request.getRequestURI())) {
            return true;
        }

        String token = request.getHeader(KEY);
        log.debug("request : {} token; {}", request, token);
        if (token == null) {
            return false;
        }

        /**
         * 处理token失效的情况
         */
        try {
            String username = TokenUtil.getUsername(token);
        } catch (Exception e) {
            e.printStackTrace();
            handleTokenInvalid(response);
            return false;
        }

        /**
         * token无效
         */
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

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
