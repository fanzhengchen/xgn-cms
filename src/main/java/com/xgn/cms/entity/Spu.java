package com.xgn.cms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "spu")
@AllArgsConstructor
@NoArgsConstructor
public class Spu {
    @Id
    private Long spuId;
    private Long shopId;
    private String pageId;
}
