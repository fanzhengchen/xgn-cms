package com.xgn.cms.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GenericGenerator(name = "userId",strategy = "com.xgn.cms.generator.CmsIdGenerator")
    @GeneratedValue(generator = "userId")
    private String userId;

    private String userName;

    private String password;

    private String projectId;


}