package com.xgn.cms.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserData {
    private String username;
    private String projectId;
}
