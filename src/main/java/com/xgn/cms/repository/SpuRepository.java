package com.xgn.cms.repository;

import com.xgn.cms.entity.Spu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Resource
public interface SpuRepository extends JpaRepository<Spu, Long> {

    @Query("select s from Spu s where s.pageId = :pageId")
    List<Spu> findAllByPageId(String pageId);

    @Modifying
    @Transactional
    @Query("delete from Spu s where s.pageId = :pageId")
    int deleteSpuByPageId(@Param("pageId") String pageId);
}
