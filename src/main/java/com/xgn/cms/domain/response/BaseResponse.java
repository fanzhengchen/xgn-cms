package com.xgn.cms.domain.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;


@Data
public class BaseResponse {
    private String resultCode;
    private String resultDesc;
    private Object resultData;


    public static BaseResponse ok() {
        BaseResponse response = new BaseResponse();
        response.setResultCode("0");
        response.setResultDesc("success");
        return response;
    }

    public static BaseResponse ok(Object data) {
        BaseResponse response = ok();
        response.setResultData(data);
        return response;
    }

    public static BaseResponse error() {
        BaseResponse response = new BaseResponse();
        response.setResultCode("1");
        response.setResultDesc("error");
        return response;
    }

    public static BaseResponse error(String desc) {
        BaseResponse response = new BaseResponse();
        response.setResultCode("1");
        response.setResultDesc(desc);
        return response;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }

    }
}
