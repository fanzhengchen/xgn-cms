package com.xgn.cms.mapper;

import com.xgn.cms.entity.WhiteList;
import java.util.List;

public interface WhiteListMapper {
    int deleteByPrimaryKey(String whitecode);

    int insert(WhiteList record);

    List<WhiteList> selectAll();
}