package com.xgn.cms.service.Impl;

import antlr.StringUtils;
import com.xgn.cms.domain.request.CreateProjectRequest;
import com.xgn.cms.domain.response.BaseResponse;
import com.xgn.cms.domain.response.CreateProjectResponse;
import com.xgn.cms.domain.response.ProjectItem;
import com.xgn.cms.domain.response.ProjectListResponse;
import com.xgn.cms.entity.Project;
import com.xgn.cms.repository.ProjectRepository;
import com.xgn.cms.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public BaseResponse createProject(CreateProjectRequest request) {
        String projectName = request.getProjectName();
        if (projectName == null) {
            return BaseResponse.error("project name is null");
        }

        /**
         * 创建项目
         */
        Project project = new Project();
        project.setProjectName(projectName);
        Project res = projectRepository.save(project);
        CreateProjectResponse response = CreateProjectResponse.builder()
                .projectId(res.getProjectId())
                .build();
        return BaseResponse.ok(res);


    }

    @Override
    public BaseResponse allProjects() {
        List<Project> projects = projectRepository.findAll();
        ProjectListResponse response = new ProjectListResponse();
        List<ProjectItem> projectItems = new ArrayList<>();

        for (Project project : projects) {
            projectItems.add(ProjectItem.builder()
                    .projectId(project.getProjectId())
                    .projectName(project.getProjectName())
                    .build());
        }
        response.setList(projectItems);
        return BaseResponse.ok(response);
    }
}
