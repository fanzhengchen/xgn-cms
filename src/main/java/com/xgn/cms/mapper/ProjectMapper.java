package com.xgn.cms.mapper;

import com.xgn.cms.domain.Project;
import java.util.List;

public interface ProjectMapper {
    int deleteByPrimaryKey(Integer projectId);

    int insert(Project record);

    Project selectByPrimaryKey(Integer projectId);

    List<Project> selectAll();

    int updateByPrimaryKey(Project record);
}