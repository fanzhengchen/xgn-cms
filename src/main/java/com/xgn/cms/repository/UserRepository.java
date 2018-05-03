package com.xgn.cms.repository;

import com.xgn.cms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


    @Override
    <S extends User> S save(S s);

    @Override
    void deleteById(String id);

    User findUserByUserId(String id);

    User findUserByUserName(String username);
}
