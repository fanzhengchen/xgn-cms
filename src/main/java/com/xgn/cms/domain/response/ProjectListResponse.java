package com.xgn.cms.domain.response;

import com.xgn.cms.entity.Project;
import lombok.Data;

import java.util.List;

@Data
public class ProjectListResponse {
    private List<ProjectItem> list;
}
