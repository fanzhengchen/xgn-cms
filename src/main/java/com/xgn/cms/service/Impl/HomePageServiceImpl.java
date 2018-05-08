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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;


@Service
public class HomePageServiceImpl implements HomePageService {


    @Autowired
    PageRepository pageRepository;

    @Autowired
    WhiteListRepository whiteListRepository;

    @Override
    public BaseResponse normalRequest(NormalHomePageRequest request) {

        CmsPage cmsPage = pageRepository.findByProjectIdAndMinVersionAndType(
                request.getProjectId(),
                request.getAppVersion(),
                CmsPage.PageType.HOME.name());
        if (cmsPage == null) {
            return BaseResponse.error("no such page");
        }

        HomePageResponse response = HomePageResponse.builder()
                .dev(false)
                .minVersion(cmsPage.getMinVersion())
                .pageId(cmsPage.getPageId())
                .pageInfo(cmsPage.getPageInfo())
                .pageName(cmsPage.getPageName())
                .pageStatus(cmsPage.getStatus())
                .build();
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
        if (whiteCode != null) {
            cmsPageList = pageRepository.findByProjectIdAndTypeAndMaxVersion(
                    projectId, CmsPage.PageType.HOME.name());
        } else {
            cmsPageList = pageRepository.findByProjectIdAndTypeAndNoGreaterThanVersion(
                    projectId, CmsPage.PageType.HOME.name(), request.getAppVersion());
        }
        if (!ObjectUtils.isEmpty(cmsPageList)) {
            CmsPage cmsPage = cmsPageList.get(0);
            HomePageResponse response = HomePageResponse.builder()
                    .dev(false)
                    .minVersion(cmsPage.getMinVersion())
                    .pageId(cmsPage.getPageId())
                    .pageInfo(cmsPage.getPageInfo())
                    .pageName(cmsPage.getPageName())
                    .pageStatus(cmsPage.getStatus())
                    .build();
            return BaseResponse.ok(response);
        } else {
            return BaseResponse.error("no such page");
        }
    }
}
