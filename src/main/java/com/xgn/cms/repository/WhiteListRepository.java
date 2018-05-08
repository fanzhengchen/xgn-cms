package com.xgn.cms.repository;

import com.xgn.cms.entity.WhiteCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhiteListRepository extends JpaRepository<WhiteCode,String> {

    WhiteCode findById(String id);
}
