package com.xgn.cms;

import com.xgn.cms.domain.response.PageConfigItem;
import com.xgn.cms.entity.CmsPage;
import com.xgn.cms.entity.Project;
import com.xgn.cms.entity.User;
import com.xgn.cms.entity.WhiteCode;
import com.xgn.cms.repository.PageRepository;
import com.xgn.cms.repository.ProjectRepository;
import com.xgn.cms.repository.UserRepository;
import com.xinguangnet.tuchao.axe.AxeConstants;
import com.xinguangnet.tuchao.axe.biz.TbbRpcResponse;
import com.xinguangnet.tuchao.goodscenter.api.request.RequestSpuDetail;
import com.xinguangnet.tuchao.goodscenter.api.response.ResponseSpuDetail;
import com.xinguangnet.tuchao.goodscenter.api.service.SpuApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    RedisTemplate redisTemplate;

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


    }


    @Test
    public void testSpu() {
        RequestSpuDetail detail = new RequestSpuDetail();
        detail.setPlatform(AxeConstants.PlatformCodeEnum.getByValue(2));

        detail.setShopId(5l);
        detail.setSpuId(194100758172409856l);

        TbbRpcResponse<ResponseSpuDetail> detailTbbRpcResponse = spuApi.spuDetail(detail);
//        Assert.assertFalse(detailTbbRpcResponse.isSuc());
    }

    @Test
    public void testFindPageByIdAndType() throws Exception{


    }

    @Test
    public void findMaxVersion() {


    }

    @Test
    public void findPageByAppVersionLessThanOrEqual() {


    }

    @Test
    public void testRedis() {
        String key = "1";
        WhiteCode code1 = new WhiteCode("1");
        WhiteCode code2 = new WhiteCode("2");

        redisTemplate.opsForList()
                .rightPush(key, code1);
        redisTemplate.opsForList().rightPush(key, code2);

        redisTemplate.opsForList()
                .index(key,0);

    }

}



