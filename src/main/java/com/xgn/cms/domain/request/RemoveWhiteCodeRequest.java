package com.xgn.cms.domain.request;

import lombok.Data;

import java.util.List;

@Data
public class RemoveWhiteCodeRequest {
    private List<String> whiteList;
}
