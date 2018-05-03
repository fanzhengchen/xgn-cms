package com.xgn.cms.domain.response;

import lombok.Data;

@Data
public class LoginData {
    private UserData userData;
    private String token;
}
