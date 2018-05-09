package com.xgn.cms.repository;

import com.xgn.cms.entity.CmsPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<CmsPage, String>, JpaSpecificationExecutor<CmsPage>
        , PagingAndSortingRepository<CmsPage, String> {

    @Modifying
    @Query("update CmsPage p set p.status = :status where p.pageId = :pageId")
    @Transactional
    int updatePageStatus(@Param("pageId") String pageId, @Param("status") String status);


    @Modifying
    @Transactional
    @Query("update CmsPage p set p.status = :status, p.pageInfo = :pageInfo where p.pageId = :pageId")
    int updatePageInfo(@Param("pageId") String pageId,
                       @Param("status") String status,
                       @Param("pageInfo") String pageInfo);

    CmsPage findByPageName(String pageName);

    //@Query("select CmsPage from CmsPage where pageId= :pageId")
    CmsPage findByPageId(String pageId);

    @Override
    Page<CmsPage> findAll(Pageable pageable);

    @Query("select s from CmsPage s where s.platform = :platform and s.type = :pageType")
    List<CmsPage> findAllByTypeAndPlatform(@Param("pageType") String pageType,
                                           @Param("platform") String platform, Pageable pageable);


    @Query("select s from CmsPage s where s.projectId = :projectId " +
            "and s.type = :pageType " +
            "and s.minVersion = " +
            "(select max(p.minVersion) from CmsPage p where p.minVersion <= :version)")
    List<CmsPage> findByProjectIdAndMinVersionAndType(@Param("projectId") String projectId,
                                                      @Param("version") int version,
                                                      @Param("pageType") String pageType);

    @Query("select s from CmsPage s where s.projectId = :projectId " +
            "and s.type = :pageType " +
            "and s.minVersion = (select max(p.minVersion)  from CmsPage p)")
    List<CmsPage> findByProjectIdAndTypeAndMaxVersion(
            @Param("projectId") String projectId,
            @Param("pageType") String pageType);

    @Query("select max(p.minVersion) from CmsPage p where p.projectId=?1")
    int findMaxVersion(String projectId);


    @Query("select s from CmsPage s where s.projectId = :projectId " +
            "and s.type = :pageType and s.minVersion = " +
            "(select max(p.minVersion ) from CmsPage p where p.minVersion <= :appVersion)")
    List<CmsPage> findByProjectIdAndTypeAndNoGreaterThanVersion(
            @Param("projectId") String projectId,
            @Param("pageType") String pageType,
            @Param("appVersion") Integer appVersion);
}
