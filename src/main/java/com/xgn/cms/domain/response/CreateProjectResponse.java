package com.xgn.cms.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProjectResponse {
    private String projectId;
}
