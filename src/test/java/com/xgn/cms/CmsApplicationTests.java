package com.xgn.cms;

import com.xgn.cms.entity.Project;
import com.xgn.cms.entity.User;
import com.xgn.cms.entity.WhiteList;
import com.xgn.cms.mapper.ProjectMapper;
import com.xgn.cms.mapper.UserMapper;
import com.xgn.cms.mapper.WhiteListMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsApplicationTests {

    @Resource
    ProjectMapper projectMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    WhiteListMapper whiteListMapper;


    @Test
    public void insertUser(){

    }

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

    @Test
    public void testSelectUserByUserName() {
       // User user = userMapper.selectByUserName("admin");
       // System.out.println(user.getUserId() + " " + user.getPassword());
       // Assert.assertEquals(user.getUserName(), "admin");

    }

    @Test
    @Rollback
    public void testInsertWhiteCode(){

    }

}
