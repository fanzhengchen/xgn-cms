package com.xgn.cms;

import com.xgn.cms.domain.Project;
import com.xgn.cms.mapper.ProjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsApplicationTests {

    @Autowired
    ProjectMapper projectMapper;

    @Test
    @Rollback
    public void testInsert() {

        Project project = new Project();

        project.setPrjectName("兔波波");
        projectMapper.insert(project);

        project.setPrjectName("四季严选");
        projectMapper.insert(project);

        project.setPrjectName("兔巢");
        projectMapper.insert(project);
    }

}
