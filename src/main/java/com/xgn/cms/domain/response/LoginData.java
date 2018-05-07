package com.xgn.cms.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginData {
    private UserData userData;
    private String token;
}
