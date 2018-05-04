package com.xgn.cms.service.Impl;

import com.xgn.cms.TokenUtil;
import com.xgn.cms.domain.request.CreatePageRequest;
import com.xgn.cms.domain.request.EditPageRequest;
import com.xgn.cms.domain.request.ModifyPageStatusRequest;
import com.xgn.cms.domain.response.BaseResponse;
import com.xgn.cms.domain.response.PageCreateResponse;
import com.xgn.cms.entity.Page;
import com.xgn.cms.entity.Project;
import com.xgn.cms.repository.PageRepository;
import com.xgn.cms.repository.ProjectRepository;
import com.xgn.cms.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class PageServiceImpl implements PageService {

    @Autowired
    PageRepository pageRepository;

    @Autowired
    ProjectRepository projectRepository;


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
        Project project = projectRepository.findByProjectId(request.getProjectId());
        Page page = Page.builder()
                .platform(request.getPlatform())
                .project(project)
                .pageName(request.getPageName())
                .version(request.getVersion())
                .build();

        log.debug("create page project: {}", project);
        String fromPageId = request.getCopyFromPageId();
        String pageInfo = "{}";
        if (fromPageId != null) {
            Page pt = pageRepository.findByPageId(fromPageId);
            if (pt != null) {
                pageInfo = pt.getPageInfo();
            }
        }
        String username = TokenUtil.getUsername(req);
        page.setPageInfo(pageInfo);
        page.setEditor(username);
        page.setCreateBy(username);
        //page.setStatus("DRAFT");
        Page result = pageRepository.save(page);


        log.debug("page create by user: {}", username);
        PageCreateResponse response = PageCreateResponse.builder()
                .pageId(result.getPageId())
                .platform(result.getPlatform())
                .pageName(result.getPageName())
                .pageInfo(result.getPageInfo())
                .pageStatus(result.getStatus())
                .build();


        return BaseResponse.ok(response);
    }

    @Override
    public BaseResponse editPage(EditPageRequest request) {
        int result = pageRepository.updatePageInfo(request.getPageId(),
                request.getPlatform(),
                request.getPageInfo());
        log.debug("edit page result: {}", request);
        return BaseResponse.ok();
    }
}
