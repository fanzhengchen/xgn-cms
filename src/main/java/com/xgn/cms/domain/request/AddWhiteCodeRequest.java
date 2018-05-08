package com.xgn.cms.domain.request;

import lombok.Data;

import java.util.List;

@Data
public class AddWhiteCodeRequest {
    private List<String> whiteList;
}
