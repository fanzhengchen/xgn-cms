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
import com.xinguangnet.tuchao.goodscenter.api.service.SpuApi;
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
    public void testSpu(){
        System.out.println("wwwwww");
    }


}



