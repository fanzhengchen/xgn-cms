package com.xgn.cms;

import com.xgn.cms.entity.Project;
import com.xgn.cms.entity.User;
import com.xgn.cms.entity.WhiteList;
import com.xgn.cms.repository.ProjectRepository;
import com.xgn.cms.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.Table;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsApplicationTests {

//    @Resource
//    ProjectMapper projectMapper;
//    @Resource
//    UserMapper userMapper;
//    @Resource
//    WhiteListMapper whiteListMapper;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;

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
        userRepository.deleteById(4);
    }

    @Test
    public void saveNewProject(){

        Project project = new Project();
        project.setProjectName("TestProject");
        project.setCreateTime(new Date(System.currentTimeMillis()));
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

}
