package com.xgn.cms.mapper;

import com.xgn.cms.entity.Page;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PageMapper {
    int deleteByPrimaryKey(@Param("pageId") Integer pageId, @Param("version") Integer version, @Param("pageName") String pageName);

    int insert(Page record);

    Page selectByPrimaryKey(@Param("pageId") Integer pageId, @Param("version") Integer version, @Param("pageName") String pageName);

    List<Page> selectAll();

    int updateByPrimaryKey(Page record);
}