package com.xgn.cms;

import com.xgn.cms.domain.response.BaseResponse;
import com.xgn.cms.domain.response.ProjectItem;
import com.xgn.cms.entity.Page;
import com.xgn.cms.entity.Project;
import com.xgn.cms.entity.User;
import com.xgn.cms.entity.WhiteList;
import com.xgn.cms.repository.PageRepository;
import com.xgn.cms.repository.ProjectRepository;
import com.xgn.cms.repository.UserRepository;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsApplicationTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    PageRepository pageRepository;

    @PersistenceContext
    EntityManager entityManager;


    @Test
    public void saveUser() {
        User user = new User();
        user.setPassword("admin123");
        user.setPriority(111);
        user.setUserName("admin");
        if (userRepository.findUserByUserName(user.getUserName()) == null) {
            userRepository.save(user);
        }

    }

    @Test
    public void deleteUser() {
        // userRepository.deleteById(4);
    }

    @Test
    public void saveNewProject() {

        Project project = new Project();
        project.setProjectName("TestProject3");

        projectRepository.save(project);
    }


    @Test
    @Rollback
    public void testInsert() {


    }

    @Test
    public void testSelectUserByUserName() {


    }

    @Test
    @Rollback
    public void testInsertWhiteCode() {

    }

    @Test
    public void testCriteriaTuple() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Project> root = cq.from(Project.class);
        cq.multiselect(root.get("projectId"), root.get("projectName"));
        TypedQuery<Tuple> typedQuery = entityManager.createQuery(cq);
        List<Tuple> tupleList = typedQuery.getResultList();
        System.out.println("====================");
        for (Tuple tuple : tupleList) {
            System.out.println(tuple.get(0) + " " + tuple.get(1));
        }
    }


    @Test
    public void testFetchProjects() {
        projectRepository.findAll();
    }

    @Test
    @Rollback
    public void testCreatePage() {
        Project project = new Project();
        project.setProjectName("project_test");
        projectRepository.save(project);

        Page page1 = Page.builder()
                .pageName("page1")
                .platform("APP")
                .version(100)
                .project(project)
                .createBy("mark")
                .editor("mark")
                .build();
        Page page2 = Page.builder()
                .pageName("page2")
                .platform("APP")
                .version(200)
                .project(project)
                .createBy("mark")
                .editor("mark")
                .build();

        pageRepository.save(page1);
        pageRepository.save(page2);


        pageRepository.updatePageStatus(page1.getPageId(), "DRAFT");

        //projectRepository.delete(project);
    }


    @Test
    public void testUpdatePageInfo() {
        List<Page> pages = pageRepository.findAll();
        int index = 0;
        for (Page page : pages) {
            pageRepository.updatePageInfo(page.getPageId(),
                    page.getPlatform(),
                    "{\"key\":\"value\"}");
            Page t = pageRepository.findById(page.getPageId()).get();
            System.out.println("aaa " + (index++) + " " + t);
        }
    }

    @Test
    public void parseToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUyNTg1OTcyNH0.Idw_MG1N28YQ-ZHauz7lognQ1hH2xu9mTbxdTbSuEUw";
        String username = TokenUtil.getUsername(token);
        System.out.println("username :" + username);
    }

    @Test
    public void testObjectMapper(){
        System.out.println(BaseResponse.ok().toString());
    }
}
