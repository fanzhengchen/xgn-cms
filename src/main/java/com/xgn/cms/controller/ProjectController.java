package com.xgn.cms.controller;

import com.xgn.cms.domain.request.CreateProjectRequest;
import com.xgn.cms.domain.response.BaseResponse;
import com.xgn.cms.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {


    @Autowired
    ProjectService projectService;

    @PostMapping("/creation")
    public BaseResponse createNewProject(@RequestBody CreateProjectRequest createProjectRequest){

        return projectService.createProject(createProjectRequest);
    }

    @PostMapping("/all")
    public BaseResponse allProjects(){
        return projectService.allProjects();
    }
}