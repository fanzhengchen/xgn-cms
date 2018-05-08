package com.xgn.cms.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageConfigListResponse {
    private int totalSize;
    private ArrayList<PageConfigItem> list;
}
