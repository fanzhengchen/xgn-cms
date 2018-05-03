package com.xgn.cms.entity;

public class WhiteList {
    private String whitecode;

    public String getWhitecode() {
        return whitecode;
    }

    public void setWhitecode(String whitecode) {
        this.whitecode = whitecode == null ? null : whitecode.trim();
    }
}