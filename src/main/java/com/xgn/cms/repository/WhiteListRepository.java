package com.xgn.cms.repository;

import com.xgn.cms.entity.WhiteCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WhiteListRepository extends JpaRepository<WhiteCode,String> {
}
