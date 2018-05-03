package com.xgn.cms.service;

import com.xgn.cms.domain.request.CreateProjectRequest;
import com.xgn.cms.domain.response.BaseResponse;

public interface ProjectService {


    BaseResponse createProject(CreateProjectRequest request);

    BaseResponse allProjects();
}
