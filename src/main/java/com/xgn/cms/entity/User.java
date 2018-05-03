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
    @Column(name = "userId")
    private String userId;

    private String userName;

    private Integer priority;

    private String password;


}