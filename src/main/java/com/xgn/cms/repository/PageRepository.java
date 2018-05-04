package com.xgn.cms.repository;

import com.xgn.cms.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PageRepository extends JpaRepository<Page, String>, JpaSpecificationExecutor<Page> {

    @Modifying
    @Query("update Page p set p.status = :status where p.pageId = :pageId")
    @Transactional
    int updatePageStatus(@Param("pageId") String pageId, @Param("status") String status);


    @Modifying
    @Transactional
    @Query("update Page p set p.platform = :platform, p.pageInfo = :pageInfo where p.pageId = :pageId")
    int updatePageInfo(@Param("pageId") String pageId,
                       @Param("platform") String platform,
                       @Param("pageInfo") String pageInfo);


    //@Query("select Page from Page where pageId= :pageId")
    Page findByPageId(String pageId);
}
