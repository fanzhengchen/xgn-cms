package com.xgn.cms.service.Impl;

import com.xgn.cms.domain.request.NormalHomePageRequest;
import com.xgn.cms.domain.request.WhitelistHomePageRequest;
import com.xgn.cms.domain.response.BaseResponse;
import com.xgn.cms.domain.response.HomePageResponse;
import com.xgn.cms.entity.CmsPage;
import com.xgn.cms.entity.WhiteCode;
import com.xgn.cms.repository.PageRepository;
import com.xgn.cms.repository.WhiteListRepository;
import com.xgn.cms.service.HomePageService;
import com.xinguangnet.tuchao.goodscenter.api.response.ResponseSpuDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class HomePageServiceImpl implements HomePageService {


    @Autowired
    PageRepository pageRepository;

    @Autowired
    WhiteListRepository whiteListRepository;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public BaseResponse normalRequest(NormalHomePageRequest request) {

        List<CmsPage> cmsPages = pageRepository.findByProjectIdAndMinVersionAndType(
                request.getProjectId(),
                request.getAppVersion(),
                CmsPage.PageType.HOME.name());
        if (ObjectUtils.isEmpty(cmsPages)) {
            return BaseResponse.error("no such page");
        }
        CmsPage cmsPage = cmsPages.get(0);

        ArrayList<ResponseSpuDetail> spuList = getFromRedis(cmsPage.getPageId());
        HomePageResponse response = convertFromCmsPage(false, cmsPage, spuList);
        return BaseResponse.ok(response);
    }

    /**
     * 首页白名单处理
     *
     * @param request
     * @return
     */
    @Override
    public BaseResponse whitelistRequest(WhitelistHomePageRequest request) {
        String projectId = request.getProjectId();
        WhiteCode whiteCode = whiteListRepository.findById(
                request.getWhiteCode());

        List<CmsPage> cmsPageList = null;
        boolean dev = false;
        if (whiteCode != null) {
            cmsPageList = pageRepository.findByProjectIdAndTypeAndMaxVersion(
                    projectId, CmsPage.PageType.HOME.name());
            dev = true;
        } else {
            cmsPageList = pageRepository.findByProjectIdAndTypeAndNoGreaterThanVersion(
                    projectId, CmsPage.PageType.HOME.name(), request.getAppVersion());
        }
        if (!ObjectUtils.isEmpty(cmsPageList)) {
            CmsPage cmsPage = cmsPageList.get(0);
            ArrayList<ResponseSpuDetail> spuList = getFromRedis(cmsPage.getPageId());
            HomePageResponse response = convertFromCmsPage(dev, cmsPage, spuList);
            return BaseResponse.ok(response);
        } else {
            return BaseResponse.error("no such page");
        }
    }

    private HomePageResponse convertFromCmsPage(boolean dev, CmsPage cmsPage,
                                                ArrayList<ResponseSpuDetail> spuList) {
        return HomePageResponse.builder()
                .dev(dev)
                .minVersion(cmsPage.getMinVersion())
                .pageId(cmsPage.getPageId())
                .pageInfo(cmsPage.getPageInfo())
                .pageName(cmsPage.getPageName())
                .pageStatus(cmsPage.getStatus())
                .spuList(spuList)
                .build();
    }


    private ArrayList<ResponseSpuDetail> getFromRedis(String pageId) {
        long size = redisTemplate.opsForList().size(pageId);
        ArrayList<ResponseSpuDetail> spuList = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            ResponseSpuDetail detail = (ResponseSpuDetail) redisTemplate.opsForList()
                    .index(pageId, i);
            spuList.add(detail);
        }
        return spuList;
    }
}
