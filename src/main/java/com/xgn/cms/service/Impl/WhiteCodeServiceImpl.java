package com.xgn.cms.service.Impl;

import com.xgn.cms.domain.request.AddWhiteCodeRequest;
import com.xgn.cms.domain.request.RemoveWhiteCodeRequest;
import com.xgn.cms.domain.response.BaseResponse;
import com.xgn.cms.domain.response.WhiteCodeResponse;
import com.xgn.cms.entity.WhiteCode;
import com.xgn.cms.repository.WhiteListRepository;
import com.xgn.cms.service.WhiteCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WhiteCodeServiceImpl implements WhiteCodeService {
    @Autowired
    WhiteListRepository whiteListRepository;

    @Override
    public BaseResponse remove(RemoveWhiteCodeRequest request) {
        List<WhiteCode> whiteCode = request.getWhiteList()
                .stream()
                .map(code -> {
                    return new WhiteCode(code);
                }).collect(Collectors.toList());

        whiteListRepository.delete(whiteCode);
        return BaseResponse.ok();
    }

    @Override
    public BaseResponse all() {
        ArrayList<String> whiteList = whiteListRepository.findAll()
                .stream()
                .map(whiteCode -> whiteCode.getId())
                .collect(Collectors.toCollection(ArrayList::new));
        WhiteCodeResponse response = new WhiteCodeResponse();
        response.setWhiteList(whiteList);
        return BaseResponse.ok(response);
    }

    @Override
    public BaseResponse add(AddWhiteCodeRequest request) {
        List<WhiteCode> codes = request.getWhiteList()
                .stream()
                .map(code -> new WhiteCode(code))
                .collect(Collectors.toList());
        whiteListRepository.save(codes);
        return BaseResponse.ok();
    }
}
