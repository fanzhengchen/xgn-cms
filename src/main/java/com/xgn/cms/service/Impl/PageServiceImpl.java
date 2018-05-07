package com.xgn.cms.service.Impl;

import com.xgn.cms.TokenUtil;
import com.xgn.cms.domain.request.CreatePageRequest;
import com.xgn.cms.domain.request.EditPageRequest;
import com.xgn.cms.domain.request.ModifyPageStatusRequest;
import com.xgn.cms.domain.request.SpuItem;
import com.xgn.cms.domain.response.BaseResponse;
import com.xgn.cms.domain.response.PageCreateResponse;
import com.xgn.cms.entity.Page;
import com.xgn.cms.entity.Project;
import com.xgn.cms.entity.Spu;
import com.xgn.cms.entity.User;
import com.xgn.cms.repository.PageRepository;
import com.xgn.cms.repository.ProjectRepository;
import com.xgn.cms.repository.SpuRepository;
import com.xgn.cms.repository.UserRepository;
import com.xgn.cms.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PageServiceImpl implements PageService {

    @Autowired
    PageRepository pageRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SpuRepository spuRepository;


    @Override
    public BaseResponse modifyPageStatus(ModifyPageStatusRequest request) {
        int result = pageRepository.updatePageStatus(request.getPageId(), request.getPageStatus());
        log.debug("update page response {}:", result);
        return BaseResponse.ok();
    }

    /**
     * 页面创建
     *
     * @param request
     * @param req
     * @return
     */
    @Override
    public BaseResponse createPage(CreatePageRequest request, HttpServletRequest req) {

        String username = TokenUtil.getUsername(req);
        User user = userRepository.findUserByUserName(username);
        if (username == null || user == null) {
            return BaseResponse.error("user not found");
        }

        Project project = projectRepository.findByProjectId(user.getProjectId());

        Page page = Page.builder()
                .project(project)
                .platform(request.getPlatform())
                .pageName(request.getPageName())
                .minVersion(request.getVersion())
                .build();

        String fromPageId = request.getCopyFromPageId();
        String pageInfo = "{}";
        if (fromPageId != null) {
            Page pt = pageRepository.findByPageId(fromPageId);
            if (pt != null) {
                pageInfo = pt.getPageInfo();
            }
        }

        page.setPageInfo(pageInfo);
        page.setEditor(username);
        page.setCreateBy(username);
        page.setStatus(Page.PageStatus.DRAFT.name());

        Page result = pageRepository.save(page);

        log.debug("page create by user: {}", username);
        PageCreateResponse response = PageCreateResponse.builder()
                .pageId(result.getPageId())
                .platform(result.getPlatform())
                .pageName(result.getPageName())
                .pageInfo(result.getPageInfo())
                .pageStatus(result.getStatus())
                .minVersion(result.getMinVersion())
                .build();

        return BaseResponse.ok(response);
    }

    @Override
    public BaseResponse editPage(EditPageRequest request) {
        int result = pageRepository.updatePageInfo(request.getPageId(),
                request.getPageStatus(),
                request.getPageInfo());
        List<SpuItem> spuItems = request.getSpuList();

        /**
         * 删掉
         */
        log.debug("delete spu by pageId: {}",request.getPageId());
        spuRepository.deleteSpuByPageId(request.getPageId());

        log.debug("update page {}",request);
        /**
         * 更新
         */
        spuItems.forEach(spuItem -> {
            spuRepository.save(Spu.builder()
                    .spuId(spuItem.getSpuId())
                    .shopId(spuItem.getShopId())
                    .pageId(request.getPageId())
                    .build());
        });

        log.debug("edit page result: {}", request);
        return BaseResponse.ok();
    }
}
