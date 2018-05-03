package com.xgn.cms.repository;

import com.xgn.cms.domain.response.ProjectItem;
import com.xgn.cms.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {


}
