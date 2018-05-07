package com.xgn.cms.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "whitelist")
public class WhiteList {

    @Id
    private String whiteCode;
}