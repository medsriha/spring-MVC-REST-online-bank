package com.team7.cfs.dao;

import com.team7.cfs.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User, String> {

    User getUserByUsernameAndPassword(String username, String password);
    User getUserByUsername(String username);
}
