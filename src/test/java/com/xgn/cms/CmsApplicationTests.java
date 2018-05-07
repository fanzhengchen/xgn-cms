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
import com.xinguangnet.tuchao.axe.AxeConstants;
import com.xinguangnet.tuchao.axe.biz.TbbRpcResponse;
import com.xinguangnet.tuchao.goodscenter.api.request.RequestSpuDetail;
import com.xinguangnet.tuchao.goodscenter.api.response.ResponseSpuDetail;
import com.xinguangnet.tuchao.goodscenter.api.service.SpuApi;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CmsApplicationTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    PageRepository pageRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Resource
    SpuApi spuApi;

    private String projectId = "aaaa-bbbbbbb-ccccccccc";

    @Test
    public void fetchUser() {
        System.out.println("=============================");
        List<User> users = userRepository.findAll();
        Assert.assertEquals(users.get(0).getUserName(), "admin");
    }

    @Test
    public void fetchProject() {
        List<Project> projects = projectRepository.findAll();
        Assert.assertEquals(projects.get(0).getProjectId(), "aaaa-bbbbbbb-ccccccccc");
    }

    @Test
    public void testInsertUser() {

        List<Project> projects = projectRepository.findAll();
        Assert.assertNotEquals(projects, null);
        Assert.assertTrue(projects.size() > 0);

        String username = "mark";
        String password = "mark123";
        User user = userRepository.findUserByUserName(username);
        Assert.assertEquals(user, null);
        user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setProjectId(projects.get(0).getProjectId());

        userRepository.save(user);

        user = userRepository.findUserByUserName(username);

        userRepository.delete(user);

        user = userRepository.findUserByUserName(username);
        Assert.assertTrue(user == null);
    }


    @Test
    public void testSpu() {
        RequestSpuDetail detail = new RequestSpuDetail();
        detail.setPlatform(AxeConstants.PlatformCodeEnum.getByValue(1));

        detail.setSpuId(1l);
        detail.setShopId(1l);

        TbbRpcResponse<ResponseSpuDetail> detailTbbRpcResponse = spuApi.spuDetail(detail);
        Assert.assertFalse(detailTbbRpcResponse.isSuc());
    }


}



