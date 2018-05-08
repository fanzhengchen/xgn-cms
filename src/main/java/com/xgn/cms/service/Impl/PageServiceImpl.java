package com.xgn.cms.service.Impl;

import com.xgn.cms.TokenUtil;
import com.xgn.cms.domain.request.*;
import com.xgn.cms.domain.response.*;
import com.xgn.cms.entity.CmsPage;
import com.xgn.cms.entity.Project;
import com.xgn.cms.entity.Spu;
import com.xgn.cms.entity.User;
import com.xgn.cms.repository.PageRepository;
import com.xgn.cms.repository.ProjectRepository;
import com.xgn.cms.repository.SpuRepository;
import com.xgn.cms.repository.UserRepository;
import com.xgn.cms.service.PageService;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import rx.Observable;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

        String fromPageId = request.getCopyFromPageId();
        String pageInfo = "{}";
        if (fromPageId != null) {
            CmsPage pt = pageRepository.findByPageId(fromPageId);
            if (pt != null) {
                pageInfo = pt.getPageInfo();
            }
        }

        CmsPage cmsPage = CmsPage.builder()
                .projectId(project.getProjectId())
                .platform(request.getPlatform())
                .pageName(request.getPageName())
                .minVersion(request.getMinVersion())
                .pageInfo(pageInfo)
                .editor(username)
                .createBy(username)
                .status(CmsPage.PageStatus.DRAFT.name())
                .type(CmsPage.PageType.HOME.name())
                .build();

        CmsPage result = pageRepository.save(cmsPage);

        log.debug("cmsPage create by user: {}", username);
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
        log.debug("delete spu by pageId: {}", request.getPageId());
        spuRepository.deleteSpuByPageId(request.getPageId());

        log.debug("update page {}", request);
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


    @Override
    public BaseResponse pageConfig(PageConfigRequest request) {
        if (request == null || request.getPageId() == null) {
            return BaseResponse.error("pageId not exits");
        }
        CmsPage cmsPage = pageRepository.findByPageId(request.getPageId());
        if (cmsPage == null) {
            return BaseResponse.error("pageId not exits");
        }
        PageConfigResponse response = PageConfigResponse.builder()
                .pageInfo(cmsPage.getPageInfo())
                .build();
        return BaseResponse.ok(response);
    }


    /**
     * 获取已经配置的页面列表啊
     * <p>
     * pageNo 默认从0开始
     *
     * @param request
     * @return
     */
    @Override
    public BaseResponse pageConfigList(PageConfigListRequest request) {

        if (ObjectUtils.isEmpty(request) ||
                ObjectUtils.isEmpty(request.getPageType()) ||
                ObjectUtils.isEmpty(request.getPlatform()) ||
                ObjectUtils.isEmpty(request.getPageNo()) ||
                ObjectUtils.isEmpty(request.getPageSize())) {
            return BaseResponse.error("illegal arguments");
        }

        int pageNo = request.getPageNo();
        int pageSize = request.getPageSize();
        PageRequest pageRequest = new PageRequest(pageNo, pageSize);
        List<CmsPage> pages = pageRepository.findAllByTypeAndPlatform(
                request.getPageType(),
                request.getPlatform(),
                pageRequest);
        /**
         * 转换成PageConfigItem
         */
        ArrayList<PageConfigItem> items = pages.stream()
                .map(cmsPage -> {
                    return PageConfigItem.builder()
                            .editName(cmsPage.getEditor())
                            .minVersion(cmsPage.getMinVersion())
                            .pageId(cmsPage.getPageId())
                            .pageName(cmsPage.getPageName())
                            .pageStatus(cmsPage.getStatus())
                            .stamp(cmsPage.getCreateTime().getTime() + "")
                            .build();
                }).collect(Collectors.toCollection(ArrayList::new));

        return BaseResponse.ok(PageConfigListResponse.builder()
                .totalSize(items.size())
                .list(items)
                .build());
    }
}
