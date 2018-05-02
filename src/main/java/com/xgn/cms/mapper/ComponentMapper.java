package com.xgn.cms.mapper;

import com.xgn.cms.domain.Component;
import java.util.List;

public interface ComponentMapper {
    int deleteByPrimaryKey(Integer componentId);

    int insert(Component record);

    Component selectByPrimaryKey(Integer componentId);

    List<Component> selectAll();

    int updateByPrimaryKey(Component record);
}